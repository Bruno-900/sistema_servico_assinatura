package com.antivirus.servicoassinatura.model;

public class MetodoPagamento {
    private int idMetadoPagamento;
    private String detalhes;

    public Pagamento(int idMetadoPagamento, String detalhes){
        this.idMetadoPagamento = idMetadoPagamento;
        this.detalhes = detalhes;
    }

    public int getIdMetadoPagamento() {
        return idMetadoPagamento;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setIdMetadoPagamento(int idMetadoPagamento) {
        this.idMetadoPagamento = idMetadoPagamento;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }
}
