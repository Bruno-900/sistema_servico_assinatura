package com.antivirus.servicoassinatura.model;

import com.antivirus.servicoassinatura.util.StatusAssinatura;
import com.antivirus.servicoassinatura.util.StatusPagamento;

import java.time.LocalDate;

public class Assinatura {
    private int idAssinatura;
    private StatusAssinatura status;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private Assinante idAssinante;
    private Plano idPlano;

    public Assinatura(){
        
    }

    public Assinatura(StatusAssinatura status, LocalDate dataInicio, LocalDate dataFim, Assinante idAssinante, Plano idPlano){
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.idAssinante = idAssinante;
        this.idPlano = idPlano;
    }

    public Assinatura(int idAssinatura, StatusAssinatura status, LocalDate dataInicio, LocalDate dataFim, Assinante idAssinante, Plano idPlano){
        this.idAssinatura = idAssinatura;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.idAssinante = idAssinante;
        this.idPlano = idPlano;
    }

    public int getIdAssinatura() {
        return idAssinatura;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public LocalDate getDataFim(){
        return dataFim;
    }

    public StatusAssinatura getStatusAssinatura(){
        return status;
    }

    public Assinante getIdAssinante(){
        return idAssinante;
    }

    public Plano getIdPlano() {
        return idPlano;
    }

    public void setIdAssinatura(int idAssinatura) {
        this.idAssinatura = idAssinatura;
    }

    public void setDataInicio(LocalDate dataInicio){
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDate dataFim){
        this.dataFim = dataFim;
    }

    public void setStatus(StatusAssinatura status){
        this.status = status;
    }

    public void setIdAssinante(Assinante idAssinante) {
        this.idAssinante = idAssinante;
    }

    public void setIdPlano(Plano idPlano) {
        this.idPlano = idPlano;
    }
}
