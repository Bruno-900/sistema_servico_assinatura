package com.antivirus.servicoassinatura.model;

import com.antivirus.servicoassinatura.util.StatusPagamento;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Pagamento {
    private int idPagamento;
    private LocalDate dataPagamento;
    private BigDecimal valor;
    private StatusPagamento status;

    public Pagamento(int idPagamento, LocalDate dataPagamento, BigDecimal valor, StatusPagamento status){
        this.idPagamento = idPagamento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.status = status;

    }

    public int getIdPagamento() {
        return idPagamento;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public StatusPagamento getStatus() {
        return status;
    }

    public void setIdPagamento(int idPagamento) {
        this.idPagamento = idPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public void setStatus(StatusPagamento status) {
        this.status = status;
    }
}
