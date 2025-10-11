package com.antivirus.servicoassinatura.model;

public class Avaliacao {
    private int idAvaliacao;
    private int nota;
    private String comentario;

    public Avaliacao(int idAvaliacao, int nota, String comentario){
        this.idAvaliacao = idAvaliacao;
        this.nota = nota;
        this.comentario = comentario;
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

    public void setIdAvaliacao(int idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
