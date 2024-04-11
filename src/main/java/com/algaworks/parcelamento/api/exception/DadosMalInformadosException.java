package com.algaworks.parcelamento.api.exception;

public class DadosMalInformadosException extends RuntimeException{
    private String lancamento;

    public DadosMalInformadosException(String message, String lancamento) {
        super(message);
        this.lancamento = lancamento;
    }

    public String getLancamento() {
        return lancamento;
    }
}
