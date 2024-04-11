package com.algaworks.parcelamento.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDTO {
    private String mensagem;
    private String lancamento;
}
