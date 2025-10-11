package com.antivirus.servicoassinatura.model;

import com.antivirus.servicoassinatura.util.Criptografia;

public class Administrador {
    private String nome;
    private String senha;

    public Administrador(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

    public String getNome(){
        return nome;
    }

    public String getSenha(){
        return senha;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
