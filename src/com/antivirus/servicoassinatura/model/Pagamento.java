package com.antivirus.servicoassinatura.model;

import com.antivirus.servicoassinatura.util.StatusPagamento;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Pagamento {
    private int idPagamento;
    private BigDecimal valor;
    private LocalDate dataPagamento;
    private StatusPagamento status;
    private MetodoPagamento metodo;

    public Pagamento(int idPagamento, LocalDate dataPagamento, BigDecimal valor, StatusPagamento status, MetodoPagamento metodo){
        this.idPagamento = idPagamento;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
        this.status = status;
        this.metodo = metodo;

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

    public String getStatus() {
        return status;
    }

    public MetodoPagamento getMetodo() {
        return metodo;
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

    public void setMetodo(MetodoPagamento metodo) {
        this.metodo = metodo;
    }
}
