package com.algaworks.parcelamento.api.dto;

import com.algaworks.parcelamento.api.domain.model.Cliente;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ParcelamentoCreateDTO {
    @NotNull
    private Long clienteId;
    @NotBlank
    private String descricao;
    @NotNull
    private BigDecimal valorTotal;
    @NotNull
    private int quantidadeParcelas;
}
