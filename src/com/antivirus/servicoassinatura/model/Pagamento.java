package com.antivirus.servicoassinatura.model;

import com.antivirus.servicoassinatura.util.StatusPagamento;

import java.time.LocalDate;
import java.math.BigDecimal;

public class Pagamento {
    private int idPagamento;
    private BigDecimal valor;
    private StatusPagamento statusPagamento;
    private LocalDate dataPagamento;
    private MetodoPagamento metodo;
    private MetodoPagamento idMetodoPagamento;
    private Assinatura idAssinatura;

    public Pagamento(){

    }

    public Pagamento(BigDecimal valor, StatusPagamento statusPagamento, LocalDate dataPagamento, MetodoPagamento metodo, MetodoPagamento idMetodoPagamento, Assinatura idAssinatura){
        this.valor = valor;
        this.statusPagamento = statusPagamento;
        this.dataPagamento = dataPagamento;
        this.metodo = metodo;
        this.idMetodoPagamento = idMetodoPagamento;
        this.idAssinatura = idAssinatura;
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

    public MetodoPagamento getMetodo() {
        return metodo;
    }

    public MetodoPagamento getIdMetodoPagamento() {
        return idMetodoPagamento;
    }

    public Assinatura getAssinatura(){
        return idAssinatura;
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

    public void setMetodo(MetodoPagamento metodo) {
        this.metodo = metodo;
    }

    public void setIdMetodoPagamento(MetodoPagamento idMetodoPagamento) {
        this.idMetodoPagamento = idMetodoPagamento;
    }

    public void setIdAssinatura(Assinatura idAssinatura) {
        this.idAssinatura = idAssinatura;
    }

    public void setStatusPagamento(StatusPagamento statusPagamento) {
        this.statusPagamento = this.statusPagamento;
    }
}
