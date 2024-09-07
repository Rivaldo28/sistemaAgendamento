package com.rivaldo.agendamento_tranferencia_financeiro.exception;


public class TaxaNaoAplicavelException extends RuntimeException {
    public TaxaNaoAplicavelException(String mensagem) {
        super(mensagem);
    }
}

