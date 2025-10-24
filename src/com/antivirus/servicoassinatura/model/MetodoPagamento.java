package com.antivirus.servicoassinatura.model;

import com.antivirus.servicoassinatura.util.StatusPagamento;
import java.math.BigDecimal;

public class MetodoPagamento {
    private int idMetodoPagamento;
    private StatusPagamento metodo;
    private BigDecimal taxa; // Percentual ou valor fixo de taxa
    private boolean ativo; // Se o metodo está disponível
    private int prazoCompensacao; // Dias até confirmar o pagamento
    private Assinante idAssinante;

    public MetodoPagamento(){
        /*
         * Construtor vazio necessário para o DAO.
         * O construtor completo (com todos os parâmetros) é usado
         * quando se deseja criar um novo metodo de pagamento no código
         * antes de salvar no banco.
         * No listarMetodoPagamento(), o DAO precisa deste construtor vazio.
         */
    }

    public MetodoPagamento(StatusPagamento detalhes, BigDecimal taxa, boolean ativo, int prazoCompensacao, Assinante idAssinante){
        this.metodo = detalhes;
        this.taxa = taxa;
        this.ativo = ativo;
        this.prazoCompensacao = prazoCompensacao;
        this.idAssinante = idAssinante;
    }

    /*
        Se o ID é gerado automaticamente no banco de dados (por AUTO_INCREMENT, IDENTITY, etc.),
        então o ideal é não incluir o idMetodoPagamento no construtor, porque ele será definido depois (pelo banco ou pelo DAO).
        pode ter dois construtores — um completo (com ID) e um sem ID, pra facilitar o uso no DAO:
     */

    public MetodoPagamento(int idMetadoPagamento, StatusPagamento detalhes, BigDecimal taxa, boolean ativo, int prazoCompensacao, Assinante idAssinante){
        this.idMetodoPagamento = idMetadoPagamento;
        this.metodo = detalhes;
        this.taxa = taxa;
        this.ativo = ativo;
        this.prazoCompensacao = prazoCompensacao;
        this.idAssinante = idAssinante;
    }

    public int getIdMetodoPagamento() {
        return idMetodoPagamento;
    }

    public StatusPagamento getMetodo() {
        return metodo;
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

    public Assinante getIdAssinante() {
        return idAssinante;
    }

    public void setIdMetodoPagamento(int idMetadoPagamento) {
        this.idMetodoPagamento = idMetadoPagamento;
    }

    public void setMetodo(StatusPagamento metodo) {
        this.metodo = metodo;
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

     public void setIdAssinante(Assinante idAssinante){
        this.idAssinante = idAssinante;
     }
}
