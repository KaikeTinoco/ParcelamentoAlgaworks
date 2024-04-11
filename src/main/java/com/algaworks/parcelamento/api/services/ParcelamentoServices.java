package com.algaworks.parcelamento.api.services;

import com.algaworks.parcelamento.api.domain.model.Cliente;
import com.algaworks.parcelamento.api.domain.model.Parcelamento;
import com.algaworks.parcelamento.api.dto.ParcelamentoCreateDTO;
import com.algaworks.parcelamento.api.dto.ParcelamentoMapper;
import com.algaworks.parcelamento.api.repositories.ClienteRepository;
import com.algaworks.parcelamento.api.repositories.ParcelamentoRepository;
import com.algaworks.parcelamento.api.specification.ParcelamentoSpecification;
import com.algaworks.parcelamento.api.specification.criteria.SearchCriteria;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@NoArgsConstructor
@Service
public class ParcelamentoServices {
    @Autowired
    private ParcelamentoRepository parcelamentoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private ParcelamentoMapper mapper;

    private ClienteServices clienteServices;

    public ResponseEntity<?> buscarTodos() {
        return ResponseEntity.ok(parcelamentoRepository.findAll());
    }

    public ResponseEntity<?> buscaEspecifica(Long id) {
        Optional<Parcelamento> parcelamento = parcelamentoRepository.findById(id);
        if (parcelamento.isPresent()) {
            return ResponseEntity.ok(parcelamento.get());
        } else {
            return ResponseEntity.badRequest().body("por favor, informe um Id válido de parcelamento!");
        }
    }

    public ResponseEntity<?> salvarParcelamento(ParcelamentoCreateDTO parcelamentoCreateDTO) {
        Cliente cliente = clienteRepository.findById(parcelamentoCreateDTO.getClienteId())
                .orElseThrow(() -> new IllegalStateException("por favor informe um id válido"));
        Parcelamento novoParcelamento = mapper.toParcelamento(parcelamentoCreateDTO);
        if (filtroDeBusca(novoParcelamento.getDescricao(),
                novoParcelamento.getValorTotal(), novoParcelamento.getQuantidadeParcelas()).isEmpty()){

            novoParcelamento.setDataCriacao(LocalDateTime.now());
            novoParcelamento.setCliente(cliente);
            parcelamentoRepository.save(novoParcelamento);
            return ResponseEntity.ok(mapper.toDTO(novoParcelamento));

        } else {
          return ResponseEntity.badRequest().body("Já existe um parcelamento com esses dados");
        }

    }

    public List<Parcelamento> filtroDeBusca(String descricao, BigDecimal valorTotal, int quantidadeParcelas){
        if(parametrosValidos(descricao, valorTotal, quantidadeParcelas)){
            ParcelamentoSpecification descricaoSpecification = null, valorTotalSpecification = null,
                    quantidadeParcelasSpecification = null;

            if(descricao != null){
                descricaoSpecification = getParcelamentoSpecifcationDescricao(descricao);
            }
            if(valorTotal != null){
                valorTotalSpecification = getParcelamentoSpecificationValorTotal(valorTotal);
            }
            if(quantidadeParcelas != 0){
                quantidadeParcelasSpecification = getParcelamentoSpecificationQuantidadeParcelas(quantidadeParcelas);
            }

            Specification<Parcelamento> parcelamentoSpecification = Specification.where(descricaoSpecification).
                    and(valorTotalSpecification).and(quantidadeParcelasSpecification);

            return parcelamentoRepository.findAll(parcelamentoSpecification);
        } else {
            return parcelamentoRepository.findAll();
        }
    }

    public ParcelamentoSpecification getParcelamentoSpecificationValorTotal(BigDecimal valorTotal){
        ParcelamentoSpecification valorTotalSpecification;
        valorTotalSpecification=ParcelamentoSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("valorTotal")
                        .value(valorTotal)
                        .operation("=")
                        .build())
                .build();
        return valorTotalSpecification;
    }

    public ParcelamentoSpecification getParcelamentoSpecificationQuantidadeParcelas(int quantidadeParcelas){
        ParcelamentoSpecification quantidadeTotalSpecification;
        quantidadeTotalSpecification = ParcelamentoSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("quantidadeParcelas")
                        .value(quantidadeParcelas)
                        .operation("=")
                        .build())
                .build();
        return quantidadeTotalSpecification;
    }

    public ParcelamentoSpecification getParcelamentoSpecifcationDescricao(String descricao){
        ParcelamentoSpecification descricaoSpecification;
        descricaoSpecification = ParcelamentoSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("descricao")
                        .value(descricao)
                        .operation("%")
                        .build())
                .build();
        return descricaoSpecification;
    }

    private boolean parametrosValidos(String descricao, BigDecimal valorTotal, int quantidadeParcelas){
        return !(descricao == null && valorTotal == null && quantidadeParcelas == 0);
    }

    public ResponseEntity<?> atualizarCadastro(String descricao, 
                                               BigDecimal valorTotal, 
                                               Integer quantidadeParcelas,
                                               Long parcelamentoId) {
        Parcelamento parcelamento = parcelamentoRepository.findById(parcelamentoId)
                .orElseThrow(() -> new IllegalStateException("por favor informe um id válido!"));
        
        if (descricao != null){
            parcelamento.setDescricao(descricao);
        }
        if(valorTotal != null){
            parcelamento.setValorTotal(valorTotal);
        }
        if(quantidadeParcelas != 0){
            parcelamento.setQuantidadeParcelas(quantidadeParcelas);
        }
        parcelamentoRepository.save(parcelamento);
        return ResponseEntity.ok(mapper.toDTO(parcelamento));
    }

    public ResponseEntity<?> deletarParcelamento(Long parcelamentoId) {
        Parcelamento parcelamento = parcelamentoRepository.findById(parcelamentoId)
                .orElseThrow(() -> new IllegalStateException("por favor informe um id válido!"));

        parcelamentoRepository.delete(parcelamento);
        return ResponseEntity.ok("deletado com sucesso!");
    }
}
