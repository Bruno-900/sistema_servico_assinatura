package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Assinante;
import com.antivirus.servicoassinatura.model.Plano;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public void cadastrarPlano(Plano novoPlano){
        String sql = "INSER INTO plano (nome, descricao, preco) VALUES (?,?,?)";
        try(Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setString(1,novoPlano.getNome());
            declarando.setString(2,novoPlano.getDescricao());
            declarando.setBigDecimal(3,novoPlano.getPreco());

            declarando.executeUpdate();
            System.out.println("Plano cadastrado !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao cadastrar plano: " + erro.getMessage());
        }
    }

    public void autualizarPlano(Plano atualizarPlano){
        String sql = "UPDATE plano SET nome = ?, descricao = ?, preco = ? WHERE id_plano = ? ";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {


            declarando.setString(1, atualizarPlano.getNome());
            declarando.setString(2, atualizarPlano.getDescricao());
            declarando.setBigDecimal(3, atualizarPlano.getPreco());

            declarando.executeUpdate();
            System.out.println("Plano atualizado !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao atualizar o Plano: " + erro.getMessage());
        }

    }

    public List<Plano> listarPlano () throws SQLException {
        List<Plano> lista = new ArrayList<>();
        String sql = "SELECT * FROM plano";
        try (Connection conexao = ConexaoBanco.getConexao();
             Statement declarando = conexao.createStatement();
             ResultSet resultado = declarando.executeQuery(sql)) {

            while (resultado.next()) {

                Plano plano = new Plano();

                plano.setIdPlano(resultado.getInt("id_plano"));
                plano.setNome(resultado.getString("nome"));
                plano.setDescricao(resultado.getString("descreicao"));
                plano.setPreco(resultado.getBigDecimal("preco"));

                lista.add(plano);
            }
        }
        return lista;
    }

    public void excluirPlano(int id){
        String sql = "DELETE FROM plano WHERE id_plano = ?";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setInt(1, id);
            declarando.executeUpdate();
            System.out.println("Plano excluido com sucesso !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao excluir o plano: " + erro.getMessage());
        }
    }
}

