package com.antivirus.servicoassinatura.model;

import java.time.LocalDate;

public class Avaliacao {
    private int idAvaliacao;
    private int nota;
    private String comentario;
    private LocalDate data;
    private Assinante idAssinante;

    public Avaliacao(){

    }

    public Avaliacao(int nota, String comentario, LocalDate data, Assinante idAssinante){
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
        this.idAssinante =  idAssinante;
    }


    public Avaliacao(int idAvaliacao, int nota, String comentario, LocalDate data, Assinante idAssinante){
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
        this.data = data;
        this.idAssinante =  idAssinante;
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

    public Assinante getIdAssinante() {
        return idAssinante;
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

    public void setIdAssinante(Assinante idAssinante) {
        this.idAssinante = idAssinante;
    }
}
