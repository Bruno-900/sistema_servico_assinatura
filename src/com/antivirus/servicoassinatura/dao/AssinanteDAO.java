package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Assinante;

import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//CRUD Basico

public class AssinanteDAO {

    public void cadastrarAssinante(Assinante novoAssinante) {
        // 1. Adicionei ", id_plano" e mais um "?"
        String sql = "INSERT INTO assinante (nome, cpf, email, senha, id_plano) VALUES(?,?,?,?,?)";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setString(1, novoAssinante.getNome());
            declarando.setString(2, novoAssinante.getCpf());
            declarando.setString(3, novoAssinante.getEmail());
            declarando.setString(4, novoAssinante.getSenha());

            // 2. Lógica para salvar o plano corretamente
            // Se o ID for maior que 0, salva o número. Se for 0 (Sem plano), salva NULL no banco.
            if (novoAssinante.getIdPlano() > 0) {
                declarando.setInt(5, novoAssinante.getIdPlano());
            } else {
                declarando.setNull(5, java.sql.Types.INTEGER);
            }

            declarando.executeUpdate();
            System.out.println("Assinante cadastrado com plano ID: " + novoAssinante.getIdPlano());

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao cadastrar assinante: " + erro.getMessage());
            erro.printStackTrace(); // Importante para ver detalhes se der erro de FK
        }
    }

    public void atualizarAssinante(Assinante atualizarAssinante) {
        String sql = "UPDATE assinante SET nome = ?, cpf = ?, email = ?, senha = ?, id_plano = ? WHERE id_assinante = ?";

        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement ps = conexao.prepareStatement(sql)) {

            ps.setString(1, atualizarAssinante.getNome());
            ps.setString(2, atualizarAssinante.getCpf());
            ps.setString(3, atualizarAssinante.getEmail());
            ps.setString(4, atualizarAssinante.getSenha());

            // Aqui estava faltando!
            if (atualizarAssinante.getIdPlano() > 0) {
                ps.setInt(5, atualizarAssinante.getIdPlano());
            } else {
                ps.setNull(5, java.sql.Types.INTEGER); // permite NULL no banco
            }

            ps.setInt(6, atualizarAssinante.getIdAssinante());

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Assinante atualizado com sucesso!");
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar o assinante: " + erro.getMessage());
            erro.printStackTrace();
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
                listarAssinante.setIdPlano(resultado.getInt("id_plano"));
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

    public Assinante buscarPorId(int id) {
        String sql = "SELECT * FROM assinante WHERE id_assinante = ?";
        try (Connection c = ConexaoBanco.getConexao();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Assinante a = new Assinante();
                a.setIdAssinante(rs.getInt("id_assinante"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getString("cpf"));
                a.setEmail(rs.getString("email"));
                a.setSenha(rs.getString("senha"));
                a.setIdPlano(rs.getInt("id_plano"));  // pode vir 0 se for NULL
                return a;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar assinante: " + e.getMessage());
        }
        return null;
    }


}

//depos adicionar pesquisas especificas(por id, por nome etc).