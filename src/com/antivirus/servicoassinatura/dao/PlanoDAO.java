package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Plano;

import javax.swing.*;
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

    public void atualizarPlano(Plano atualizarPlano){
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

    public List<Plano> listarPlano() {
        List<Plano> lista = new ArrayList<>();
        String sql = "SELECT id_plano, nome, descricao, preco FROM plano ORDER BY nome";

        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Plano p = new Plano();
                p.setIdPlano(rs.getInt("id_plano"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getBigDecimal("preco"));
                lista.add(p);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao listar planos: " + e.getMessage());
            e.printStackTrace(); // <--- isso é importante pro console
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

    public Plano buscarPorId(int id) {
        String sql = "SELECT * FROM plano WHERE id_plano = ?";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Plano p = new Plano();
                p.setIdPlano(rs.getInt("id_plano"));
                p.setNome(rs.getString("nome"));
                p.setDescricao(rs.getString("descricao"));
                p.setPreco(rs.getBigDecimal("preco"));
                return p;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar plano: " + e.getMessage());
        }
        return null; // não encontrou
    }


}

