package com.algaworks.parcelamento.api.dto;

import com.algaworks.parcelamento.api.domain.model.Cliente;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteResponseDTo {
    private Long id;
    private String nome;
    private String email;
    private String numeroTelefone;
}
