package com.antivirus.servicoassinatura.model;
import java.math.BigDecimal;

public class Plano {
    private int idPlano;
    private String nome;
    private String descricao;
    private BigDecimal preco;

    public Plano(int idPlano, String nome, String descricao, BigDecimal preco){
        this.idPlano = idPlano;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public int getIdPlano(){
        return idPlano;
    }

    public String getNome(){
        return nome;
    }

    public String getDescricao(){
        return descricao;
    }

    public BigDecimal getPreco(){
        return preco;
    }

    public void setIdPlano(int idPlano){
        this.idPlano = idPlano;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
