package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Avaliacao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AvaliacaoDAO {

    public void cadastrarAvaliacao(Avaliacao novaAvaliacao){
        String sql = "INSERT INTO avaliacao (nota, comentario, data) VALUES (?,?,?)";
        try(Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)){

                declarando.setInt(1, novaAvaliacao.getNota());
                declarando.setString(2, novaAvaliacao.getComentario());

                LocalDate dataAvaliacao = novaAvaliacao.getData();
                Timestamp timestamp = Timestamp.valueOf(dataAvaliacao.atStartOfDay());

                declarando.setTimestamp(3, timestamp);

                declarando.executeUpdate();
            System.out.println("Avalicao cadastrada com sucesso !");


        }catch (SQLException erro){
            System.out.println("❌ Erro ao cadastrar avaliacaor: " + erro.getMessage());
        }
    }

    public void atualizarAvaliacao(Avaliacao atualizarAvalicao){
        String sql = "UPDATE avaliacao SET nota = ?, comentario = ?, data = ? WHERE id_avaliacao = ?";
        try (Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)){

            declarando.setInt(1, atualizarAvalicao.getNota());
            declarando.setString(2, atualizarAvalicao.getComentario());

            LocalDate dataAvaliacao = atualizarAvalicao.getData();
            Timestamp timestamp = Timestamp.valueOf(dataAvaliacao.atStartOfDay());
            declarando.setTimestamp(3, timestamp);

            declarando.setInt(4, atualizarAvalicao.getIdAvaliacao());


            declarando.executeUpdate();
            System.out.println("Avaliacao atualizada !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao atualizar o avaliacao: " + erro.getMessage());
        }
    }

    public List<Avaliacao> listarAvaliacao() throws SQLException{
        List<Avaliacao> lista = new ArrayList<>();
        String sql = "SELECT * FROM avaliacao WHERE id_avaliacao = ?";
        try(Connection conexao = ConexaoBanco.getConexao();
            Statement declarando = conexao.createStatement();
            ResultSet resultado = declarando.executeQuery(sql)){

            while (resultado.next()){

                Avaliacao listarAvaliacao = new Avaliacao();

                listarAvaliacao.setIdAvaliacao(resultado.getInt("id_avaliacao"));
                listarAvaliacao.setNota(resultado.getInt("nota"));
                listarAvaliacao.setComentario(resultado.getString("comentario"));

                Timestamp ts = resultado.getTimestamp("data");
                if (ts != null) {
                    listarAvaliacao.setData(ts.toLocalDateTime().toLocalDate());
                }

                lista.add(listarAvaliacao);

            }

        }
        return lista;
    }

    public void excluirAvaliacao(int id){
        String sql = "DELETE FROM avaliacao WHERE id_avaliacao";
        try(Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)){

            declarando.setInt(1, id);
            declarando.executeUpdate();
            System.out.println("Assinante atualizado com sucesso !");


        }catch (SQLException erro){
            System.out.println("❌ Erro ao excluir o assinante: " + erro.getMessage());
        }

    }
}

