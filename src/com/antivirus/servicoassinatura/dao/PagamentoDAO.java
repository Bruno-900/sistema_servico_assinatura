package com.antivirus.servicoassinatura.dao;

import com.antivirus.servicoassinatura.model.Pagamento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PagamentoDAO {

    public void cadastrarPagamento(Pagamento novoPagamento) {
        String sql = "INSERT INTO pagamento (valor, status, data_pagamento, fk_id_metodo_pagamento, fk_id_assinatura) VALUES (?, ?, ?, ?, ?";
        try (Connection conexao = ConexaoBanco.getConexao();
             PreparedStatement declarando = conexao.prepareStatement(sql)) {

            declarando.setBigDecimal(1, novoPagamento.getValor());
            declarando.setString(2, novoPagamento.getStatus());
            declarando.setString(3, novoPagamento.getStatus());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
