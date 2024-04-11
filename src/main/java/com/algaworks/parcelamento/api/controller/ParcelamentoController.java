package com.algaworks.parcelamento.api.controller;

import com.algaworks.parcelamento.api.dto.ParcelamentoCreateDTO;
import com.algaworks.parcelamento.api.services.ParcelamentoServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping(path = "/parcelamentos")
@AllArgsConstructor
public class ParcelamentoController {
    @Autowired
    private ParcelamentoServices parcelamentoServices;

    @GetMapping(path = "/busca/geral")
    public ResponseEntity<?> buscarTodos(){
        return parcelamentoServices.buscarTodos();
    }

    @GetMapping(path = "/busca/{parcelamentoId}")
    public ResponseEntity<?> buscaEspecifica(@PathVariable Long id){
        return parcelamentoServices.buscaEspecifica(id);
    }

    @PostMapping(path = "/cadastro")
    public ResponseEntity<?> cadastroParcelamento(@Valid @RequestBody ParcelamentoCreateDTO parcelamentoCreateDTO){
        return parcelamentoServices.salvarParcelamento(parcelamentoCreateDTO);
    }

    @PutMapping(path = "/atualizar")
    public ResponseEntity<?> autalizarParcelamento( @RequestBody String descricao,
                                                   BigDecimal valorTotal,
                                                   Integer quantidadeParcelas,
                                                   Long parcelamentoId){
        return parcelamentoServices.atualizarCadastro(descricao, valorTotal, quantidadeParcelas, parcelamentoId);

    }

    @DeleteMapping(path = "/deletar/{parcelamentoId}")
    public ResponseEntity<?> deletarParcelamento(@Valid @PathVariable Long parcelamentoId){
        return parcelamentoServices.deletarParcelamento(parcelamentoId);
    }
}
