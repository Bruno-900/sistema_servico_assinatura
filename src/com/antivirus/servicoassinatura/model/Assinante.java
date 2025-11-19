package com.antivirus.servicoassinatura.model;

public class Assinante {
    private int idAssinante;
    private String nome;
    private String cpf;
    private String email;
    private String senha;
    private int idPlano;

    /* Metodo contrutor */

    public Assinante(){
        this.idAssinante = idAssinante;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    // Getters (para obter os valores)

    public int getIdAssinante() {
        return idAssinante;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }

    public int getIdPlano() {
        return idPlano;
    }

    // Setters (para modificar os valores)

    public void setIdAssinante(int idAssinante) {
        this.idAssinante = idAssinante;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

}