package com.antivirus.servicoassinatura.model;

import java.time.LocalDate;

public class Assinatura {
    private int idAssinatura;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusAssinatura status;

    public Assinatura(int idAssinatura, LocalDate dataInicio, LocalDate dataFim, StatusAssinatura status){
        this.idAssinatura = idAssinatura;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
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

}
