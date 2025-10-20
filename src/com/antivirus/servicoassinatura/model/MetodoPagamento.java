package com.antivirus.servicoassinatura.model;

import java.math.BigDecimal;

public class MetodoPagamento {
    private int idMetadoPagamento;
    private String detalhes;
    private BigDecimal taxa; // Percentual ou valor fixo de taxa
    private boolean ativo; // Se o metodo está disponível
    private int prazoCompensacao; // Dias até confirmar o pagamento

    public MetodoPagamento(){
        this.idMetadoPagamento = idMetadoPagamento;
        this.detalhes = detalhes;
        this.taxa = taxa;
        this.ativo = ativo;
        this.prazoCompensacao = prazoCompensacao;
    }

    public int getIdMetodoPagamento() {
        return idMetadoPagamento;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public int getPrazoCompensacao() {
        return prazoCompensacao;
    }

    public void setIdMetodoPagamento(int idMetadoPagamento) {
        this.idMetadoPagamento = idMetadoPagamento;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public void setTaxa(BigDecimal taxa) {
        this.taxa = taxa;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public void setPrazoCompensacao(int prazoCompensacao) {
        this.prazoCompensacao = prazoCompensacao;
    }
}
