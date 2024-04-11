package com.algaworks.parcelamento.api.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Parcelamento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Long id;
    @ManyToOne
    private Cliente cliente;
    private String descricao;
    private BigDecimal valorTotal;
    private int quantidadeParcelas;
    private LocalDateTime dataCriacao;

    private Parcelamento(Cliente cliente, String descricao, BigDecimal valorTotal, int quantidadeParcelas){
        this.cliente = cliente;
        this.descricao = descricao;
        this.valorTotal = valorTotal;
        this.quantidadeParcelas = quantidadeParcelas;
        this.dataCriacao = LocalDateTime.now();
    }
}
