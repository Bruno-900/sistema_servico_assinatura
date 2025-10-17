package com.antivirus.servicoassinatura.model;

import java.time.LocalDate;

public class Avaliacao {
    private int idAvaliacao;
    private int nota;
    private String comentario;
    private LocalDate data;

    public Avaliacao(){
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
    }

    public int getIdAvaliacao() {
        return idAvaliacao;
    }

    public int getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDate getData() {
        return data;
    }

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}
