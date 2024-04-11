package com.algaworks.parcelamento.api.dto;

import com.algaworks.parcelamento.api.domain.model.Cliente;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ParcelamentoResponseDTO {
    private Cliente cliente;
    private String descricao;
    private BigDecimal valorTotal;
    private int quantidadeParcelas;
    private LocalDateTime dataCriacao;
}
