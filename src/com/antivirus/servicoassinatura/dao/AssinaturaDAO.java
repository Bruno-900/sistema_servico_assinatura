package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Assinante;
import com.antivirus.servicoassinatura.model.Assinatura;
import com.antivirus.servicoassinatura.model.Plano;
import com.antivirus.servicoassinatura.util.StatusAssinatura;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AssinaturaDAO {

    public void cadastrarAssinatura(Assinatura novaAssinatura){
        String sql = "INSERT INTO assinatura (status, data_inicio, data_Fim, fk_assinatura_assinante, fk_assinatura_plano) VALUES (?, ?, ?, ?, ?))";
        try(Connection conexao = ConexaoBanco.getConexao();
            PreparedStatement declarando = conexao.prepareStatement(sql)){

            declarando.setString(1, novaAssinatura.getStatusAssinatura().name());
            declarando.setObject(2, novaAssinatura.getDataInicio());
            declarando.setObject(3, novaAssinatura.getDataFim());
            declarando.setInt(4, novaAssinatura.getIdAssinante().getIdAssinante());
            declarando.setInt(5, novaAssinatura.getIdPlano().getIdPlano());

            declarando.executeUpdate();
            System.out.println("Assinatura cadastrada !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao cadastrar assinante: " + erro.getMessage());
        }
    }

    public void atualizarAssinatura(Assinatura atualizarAssinatura){
        String sql = "UPDATE assinatura SET status = ? data_inicio = ?, data_fim = ?, fk_assinatura_assinante = ?, fk_assinatura_plano = ? WHERE id_assinatura = ?";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setString(1, atualizarAssinatura.getStatusAssinatura().name());
            declarando.setObject(2, atualizarAssinatura.getDataInicio());
            declarando.setObject(3, atualizarAssinatura.getDataFim());
            declarando.setInt(4, atualizarAssinatura.getIdAssinante().getIdAssinante());
            declarando.setInt(5, atualizarAssinatura.getIdPlano().getIdPlano());
            declarando.setInt(6, atualizarAssinatura.getIdAssinatura());

            declarando.executeUpdate();
            System.out.println("Assinatura atualizada !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao atualizar a assinatura: " + erro.getMessage());
        }
    }

    public List<Assinatura> listarASsinatura() throws SQLException{
        List<Assinatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM assinatura";
        try (Connection conexao = ConexaoBanco.getConexao();
             Statement declarando = conexao.createStatement();
             ResultSet resultado = declarando.executeQuery(sql)) {

            while (resultado.next()){
                Assinatura listarAssinatura = new Assinatura();

                listarAssinatura.setIdAssinatura(resultado.getInt("id_assinatura"));

                // Atributo ENUM (Conversão String -> Enum)
                String statusString = resultado.getString("status");
                listarAssinatura.setStatus(StatusAssinatura.valueOf(statusString));

                // O metodo getObject lida com valores NULL automaticamente.
                listarAssinatura.setDataInicio(resultado.getObject("data_inicio", LocalDate.class));
                listarAssinatura.setDataFim(resultado.getObject("data_fim", LocalDate.class));

                // Criando objeto Assinante apenas com o ID
                Assinante assinante = new Assinante();
                assinante.setIdAssinante(resultado.getInt("fk_assinatura_assinante"));
                listarAssinatura.setIdAssinante(assinante);

                // Criando objeto Plano apenas com o ID
                Plano plano = new Plano();
                plano.setIdPlano(resultado.getInt("fk_assinatura_plano"));
                listarAssinatura.setIdPlano(plano);


                lista.add(listarAssinatura);
            }
        }
        return lista;
    }

    public void excluirAssinatura(int id){
        String sql = "DELETE FROM assinatura WHERE id_assinatura";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setInt(1, id);
            declarando.executeUpdate();
            System.out.println("Assinantura excluida com sucesso !");

        } catch (SQLException erro) {
            System.out.println("❌ Erro ao excluir a assinatura: " + erro.getMessage());
        }
    }
}