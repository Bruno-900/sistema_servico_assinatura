package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Plano;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlanoDAO {

    public void cadastrarPlano(Plano novoPlano){
        String sql = "INSERT INTO plano (nome, descricao, preco) VALUES (?,?,?)";
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

    public void atualizarPlano(Plano p) {
        String sql = "UPDATE plano SET nome = ?, descricao = ?, preco = ? WHERE id_plano = ?";

        try (Connection c = ConexaoBanco.getConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, p.getNome());
            ps.setString(2, p.getDescricao());
            ps.setBigDecimal(3, p.getPreco());
            ps.setInt(4, p.getIdPlano());

            ps.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o Plano: " + e.getMessage());
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

    public void excluirPlano(int id) {
        String verifica = "SELECT COUNT(*) FROM assinante WHERE id_plano = ?";
        String sql = "DELETE FROM plano WHERE id_plano = ?";

        try (Connection c = ConexaoBanco.getConexao()) {
            // Verifica se alguém está usando o plano
            PreparedStatement ps1 = c.prepareStatement(verifica);
            ps1.setInt(1, id);
            ResultSet rs = ps1.executeQuery();
            rs.next();
            int quantidade = rs.getInt(1);

            if (quantidade > 0) {
                JOptionPane.showMessageDialog(null,
                        "Exclusão bloqueada!\n\nEste plano possui " + quantidade +
                                " assinante(s) ativo(s) e não pode ser excluído.\n\n",
                        "Plano em uso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Só chega aqui se ninguém estiver usando
            PreparedStatement ps2 = c.prepareStatement(sql);
            ps2.setInt(1, id);
            ps2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Plano excluído com sucesso!",
                    "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o plano: " + e.getMessage());
        }
    }


    public Plano buscarPorId(int id) {
        String sql = "SELECT * FROM plano WHERE id_plano = ?";
        try (Connection conn = ConexaoBanco.getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {

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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

