package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Assinante;
import com.antivirus.servicoassinatura.model.MetodoPagamento;
import com.antivirus.servicoassinatura.util.StatusPagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagamentoDAO {

    public void cadastrarMetodoPagamento(MetodoPagamento novoMetodoPagamento){
        String sql = "INSERT INTO metodo_pagamento (detalhes, taxa, ativo, prazo_compensacao) VALUES (?,?,?,?) ";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setObject(1, novoMetodoPagamento.getMetodo());
            declarando.setBigDecimal(2, novoMetodoPagamento.getTaxa());
            declarando.setBoolean(3, novoMetodoPagamento.getAtivo());
            declarando.setInt(4,novoMetodoPagamento.getPrazoCompensacao());
            declarando.setInt(5, novoMetodoPagamento.getIdAssinante().getIdAssinante());

            declarando.executeUpdate();
            System.out.println("Metodo de pagamento cadastrado com sucesso: ");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao cadastrar metodo de pagamento: " + erro.getMessage()); // Boa prática: Imprimir o erro
        }
    }

    public void atualizarMetodoPagamento (MetodoPagamento atualizarMetodoPagamento){
        // Erro 3: SQL corrigido para atualizar a tabela 'metodo_pagamento'
        String sql = "UPDATE metodo_pagamento SET detalhes = ?, taxa = ?, ativo = ?, prazo_compensacao = ?, fk_metodo_pagamento_assinante = ? WHERE id_metodo_pagamento = ? ";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setObject(1, atualizarMetodoPagamento.getMetodo());
            declarando.setBigDecimal(2, atualizarMetodoPagamento.getTaxa());
            declarando.setBoolean(3, atualizarMetodoPagamento.getAtivo());
            declarando.setInt(4, atualizarMetodoPagamento.getPrazoCompensacao());
            declarando.setInt(5, atualizarMetodoPagamento.getIdAssinante().getIdAssinante());

            declarando.setInt(6, atualizarMetodoPagamento.getIdMetodoPagamento());

            declarando.executeUpdate();
            System.out.println("Metodo de pagamento atualizado com sucesso: ");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao atualizar metodo de pagamento: " + erro.getMessage()); // Boa prática: Imprimir o erro
        }
    }


    public List<MetodoPagamento> listarMetodoPagamento () throws SQLException {
        List<MetodoPagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM metodo_pagamento";
        try (Connection conexao = ConexaoBanco.getConexao();
             Statement declarando = conexao.createStatement();
             ResultSet resultado = declarando.executeQuery(sql)) {

            while (resultado.next()) {

                MetodoPagamento listarMetodoPagamento = new MetodoPagamento();

                listarMetodoPagamento.setIdMetodoPagamento(resultado.getInt("id_metodo_pagamento"));
                listarMetodoPagamento.setMetodo((StatusPagamento) resultado.getObject("detalhes"));
                listarMetodoPagamento.setTaxa(resultado.getBigDecimal("taxa"));
                listarMetodoPagamento.setAtivo(resultado.getBoolean("ativo"));
                listarMetodoPagamento.setPrazoCompensacao(resultado.getInt("prazo_compensacao"));

                /* criando um objeto para a chave extrangeira!
                    Explicação:
                    O metodo_pagamento tem uma chave estrangeira (fk_metodo_pagamento_assinante) que aponta para a tabela assinante.
                    Quando você faz o SELECT * FROM metodo_pagamento, o banco só retorna o ID do assinante, não os dados completos do assinante.
                    Então, cria um novo objeto Assinante e atribui somente o ID. Por fim, associa esse objeto ao MetodoPagamento.
                    Resultado:
                    O MetodoPagamento agora tem um atributo idAssinante que representa o assinante vinculado, mesmo que ainda não tenha os outros dados dele.
                    Por que isso é feito assim:
                    Esse padrão é comum em DAOs para evitar buscas desnecessárias.
                    Ou seja, você não precisa buscar todos os dados do assinante se só quer saber a qual ID ele pertence.
                 */

                Assinante chave = new Assinante();
                chave.setIdAssinante(resultado.getInt("fk_metodo_pagamento_assinante"));// id da fk.

                listarMetodoPagamento.setIdAssinante(chave);

                lista.add(listarMetodoPagamento);
            }
        }
        return lista;
    }

    public void excluirMetodoPagamento (int id){
        String sql = "DELETE FROM metodo_pagamento WHERE id_metodo_pagamento = ?";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setInt(1, id);
            declarando.executeUpdate();
            System.out.println("Metodo de pagamento excluido com sucesso: ");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao excluir o metodo de pagamento: " + erro.getMessage());
        }
    }
}
