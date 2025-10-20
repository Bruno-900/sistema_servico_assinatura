package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Assinante;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CRUD Basico

public class AssinanteDAO {

    public void cadastrarAssinante(Assinante novoAssinante) {
        String sql = "INSERT INTO assinante (nome, cpf, email, senha) VALUES(?,?,?,?)";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setString(1, novoAssinante.getNome());
            declarando.setString(2, novoAssinante.getCpf());
            declarando.setString(3, novoAssinante.getEmail());
            declarando.setString(4, novoAssinante.getSenha());

            declarando.executeUpdate();
            System.out.println("Assinante cadastrado!");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao cadastrar assinante: " + erro.getMessage());
        }
    }

    public void atualizarAssinante(Assinante atualizarAssinante) {
        String sql = "UPDATE assinante SET nome = ?, cpf = ?, email = ?, senha = ? WHERE id_assinante = ? ";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {


            declarando.setString(1, atualizarAssinante.getNome());
            declarando.setString(2, atualizarAssinante.getCpf());
            declarando.setString(3, atualizarAssinante.getEmail());
            declarando.setString(4, atualizarAssinante.getSenha());

            declarando.executeUpdate();
            System.out.println("Assinante atualizado !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao atualizar o assinante: " + erro.getMessage());
        }
    }

    public List<Assinante> listarAssinante() throws SQLException {
        List<Assinante> lista = new ArrayList<>();
        String sql = "SELECT * FROM assinante";
        try (Connection conexao = ConexaoBanco.getConexao();
             Statement declarando = conexao.createStatement();
             ResultSet resultado = declarando.executeQuery(sql)) {

            while (resultado.next()) {

                Assinante listarAssinante = new Assinante();

                listarAssinante.setIdAssinante(resultado.getInt("id_assinante"));
                listarAssinante.setNome(resultado.getString("nome"));
                listarAssinante.setCpf(resultado.getString("cpf"));
                listarAssinante.setEmail(resultado.getString("email"));
                listarAssinante.setSenha(resultado.getString("senha"));

                lista.add(listarAssinante);
            }
        }
        return lista;
    }

    public void excluirAssinante(int id) {
        String sql = "DELETE FROM assinante WHERE id_assinante = ?";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setInt(1, id);
            declarando.executeUpdate();
            System.out.println("Assinante excluido com sucesso !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao excluir o assinante: " + erro.getMessage());
        }
    }
}


//depos adicionar pesquisas especificas(por id, por nome etc).