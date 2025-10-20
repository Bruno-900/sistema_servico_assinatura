package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Administrador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministradorDAO {

    public void cadastrarAdministrador(Administrador novoAdministrador){
        String sql = "INSERT INTO administrador (nome, email, senha) VALUES(?,?,?)";
        try(Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)){

                declarando.setString(1, novoAdministrador.getNome());
                declarando.setString(2, novoAdministrador.getEmail());
                declarando.setString(3, novoAdministrador.getSenha());

                declarando.executeUpdate();
                System.out.println("Administrador cadastrado com sucesso !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao cadastrar administrador: " + erro.getMessage());

        }
    }

    public void atualizarAdministrador(Administrador atualizarAdministrador){
        String sql = "UPDATE administrador SET nome = ?, email = ?, senha = ? WHERE id_administrador = ?";
        try(Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)){

            declarando.setString(1, atualizarAdministrador.getNome());
            declarando.setString(2, atualizarAdministrador.getEmail());
            declarando.setString(3, atualizarAdministrador.getSenha());
            declarando.setInt(4, atualizarAdministrador.getIdAdministrador());

            declarando.executeUpdate();
            System.out.println("Administrador atualizado !");

        }catch (SQLException erro){
            System.out.println("❌ Erro ao atualizar o administrador: " + erro.getMessage());
        }
    }

    public List<Administrador> listarAdministrador() throws SQLException{
        List<Administrador> lista = new ArrayList<>();
        String sql = "SELECT * FROM administrador";
        try(Connection conexao = ConexaoBanco.getConexao();
            Statement declarando = conexao.createStatement();
            ResultSet resultado = declarando.executeQuery(sql)){

            while (resultado.next()){

                Administrador listarAdministrador = new Administrador();

                listarAdministrador.setIdAdministrador(resultado.getInt("id_administrador"));
                listarAdministrador.setNome(resultado.getString("nome"));
                listarAdministrador.setEmail(resultado.getString("email"));
                listarAdministrador.setSenha(resultado.getString("senha"));
                lista.add(listarAdministrador);
            }

        }
        return  lista;
    }

    public void excluirAdministrador(int id){
        String sql = "DELETE FROM administrador WHERE id_administrador = ?";
        try(Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)){

            declarando.setInt(1, id);
            declarando.executeUpdate();
            System.out.println("Assinante atualizado com sucesso !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao excluir o assinante: " + erro.getMessage());
        }
    }

}
