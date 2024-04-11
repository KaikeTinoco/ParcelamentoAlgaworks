package com.algaworks.parcelamento.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteCreateDTO {
    private String nome;
    private String email;
    private String numeroTelefone;
}
