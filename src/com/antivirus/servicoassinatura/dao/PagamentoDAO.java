package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Assinatura;
import com.antivirus.servicoassinatura.model.MetodoPagamento;
import com.antivirus.servicoassinatura.model.Pagamento;
import com.antivirus.servicoassinatura.util.StatusPagamento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PagamentoDAO {

    public void cadastrarPagamento(Pagamento novoPagamento) {
        String sql = "INSERT INTO pagamento (valor, status_pagamento, data_pagamento, fk_id_metodo_pagamento, fk_id_assinatura) VALUES (?, ?, ?, ?, ?";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setBigDecimal(1, novoPagamento.getValor());
            declarando.setObject(2, novoPagamento.getDataPagamento());
            declarando.setString(3, novoPagamento.getMetodo().toString()); // salva o enum
            declarando.setInt(4, novoPagamento.getIdMetodoPagamento().getIdMetodoPagamento()); // FK para MetodoPagamento
            declarando.setInt(5, novoPagamento.getAssinatura().getIdAssinatura()); // FK para Assinatura

            declarando.executeUpdate();
            System.out.println("Pagamento cadastrado !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao cadastrar pagamento: " + erro.getMessage());
        }

    }

    public void atualizarPagamento(Pagamento atualizarPagamento){
        String sql = "UPDATE INTO pagamento (valor = ? status_pagamento = ? data_pagamento = ? fk_id_metodo_pagamento = ? fk_id_assinatura = ? WHERE id_pagamento = ?";
        try(Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)){

            declarando.setBigDecimal(1, atualizarPagamento.getValor());
            declarando.setObject(2, atualizarPagamento.getDataPagamento());
            declarando.setString(3, atualizarPagamento.getMetodo().toString());
            declarando.setInt(4, atualizarPagamento.getIdMetodoPagamento().getIdMetodoPagamento());
            declarando.setInt(5, atualizarPagamento.getAssinatura().getIdAssinatura());
            declarando.setInt(6, atualizarPagamento.getIdPagamento());

            declarando.executeUpdate();
            System.out.println("Pagamento atualizado !");

        }catch (SQLException erro){
            System.out.println("❌ Erro au atualizar o pagamento");
        }

    }

    public List<Pagamento> listarPagamento () throws SQLException{
        List<Pagamento> lista = new ArrayList<>();
        String sql = "SELECT * FROM pagamento";
        try (Connection conexao = ConexaoBanco.getConexao();
             Statement declarando = conexao.createStatement();
             ResultSet resultado = declarando.executeQuery(sql)) {

            while (resultado.next()) {
                Pagamento listarPagamento = new Pagamento();

                listarPagamento.setIdPagamento(resultado.getInt("id_pagamento"));
                listarPagamento.setValor(resultado.getBigDecimal("valor"));
                listarPagamento.setStatusPagamento(StatusPagamento.valueOf(resultado.getString("status_pagamento")));
                listarPagamento.setDataPagamento(resultado.getDate("data_pagamento").toLocalDate());

                // FK → apenas o ID do metodo de pagamento
                MetodoPagamento metodo = new MetodoPagamento();
                metodo.setIdMetodoPagamento(resultado.getInt("fk_metodo_pagamento"));
                listarPagamento.setMetodo(metodo);

                // FK → apenas o ID da assinatura
                Assinatura assinatura = new Assinatura();
                assinatura.setIdAssinatura(resultado.getInt("fk_assinatura"));
                listarPagamento.setIdAssinatura(assinatura);

            }

        }
        return lista;
    }

    public void excluirAssinatura(int id){
        String sql = "DELETE FROM pagamento WHERE id_pagamento";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setInt(1, id);
            declarando.executeUpdate();
            System.out.println("Pagamento excluido com sucesso !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao excluir a assinatura: " + erro.getMessage());
        }
    }


}
