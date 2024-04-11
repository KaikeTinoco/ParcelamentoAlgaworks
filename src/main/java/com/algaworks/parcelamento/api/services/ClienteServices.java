package com.algaworks.parcelamento.api.services;

import com.algaworks.parcelamento.api.domain.model.Cliente;
import com.algaworks.parcelamento.api.dto.ClienteMapper;
import com.algaworks.parcelamento.api.dto.ClienteResponseDTo;
import com.algaworks.parcelamento.api.repositories.ClienteRepository;
import com.algaworks.parcelamento.api.specification.ClienteSpecification;
import com.algaworks.parcelamento.api.specification.criteria.SearchCriteria;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteServices {
    private final ClienteRepository clienteRepository;
    private ClienteMapper clienteMapper;

    public ResponseEntity<?> salvarCLiente(Cliente cliente){
     boolean clienteExiste;
     if (filtroDeBusca(cliente.getNome(), cliente.getEmail(), cliente.getNumeroTelefone()).isEmpty()){
         clienteExiste = false;
     } else {
         clienteExiste = true;
     }
     if(clienteExiste){
         return ResponseEntity.badRequest().body("Já existe um cliente com esses dados!");
     } else {
         clienteRepository.save(cliente);
         return ResponseEntity.status(201).body(cliente);
     }
    }

    private List<Cliente> filtroDeBusca(String nome, String email, String numeroTelefone){
        if(parametrosValidos(nome, email, numeroTelefone)){
            ClienteSpecification nomeSpecification = null, emailSpecification = null, numeroTelefoneSpecification = null;

            if(nome != null){
                nomeSpecification = getClienteSpecificationNome(nome);
            }
            if(email != null){
                emailSpecification = getClienteSpecificationEmail(email);
            }
            if(numeroTelefone != null){
                numeroTelefoneSpecification = getClienteSpecificationNumeroTelefone(numeroTelefone);
            }

            Specification<Cliente> clienteSpecification = Specification.where(nomeSpecification).and(emailSpecification)
                    .and(numeroTelefoneSpecification);

            return clienteRepository.findAll(clienteSpecification);
        }
        return null;
    }

    private ClienteSpecification getClienteSpecificationNome(String nome){
        ClienteSpecification nomeSpecification;
        nomeSpecification=ClienteSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("nome")
                        .value(nome)
                        .operation("%")
                        .build())
                .build();
        return nomeSpecification;
    }

    private ClienteSpecification getClienteSpecificationEmail(String email){
        ClienteSpecification emailSpecification;
        emailSpecification = ClienteSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("email")
                        .value(email)
                        .operation("%")
                        .build())
                .build();
        return emailSpecification;
    }

    private ClienteSpecification getClienteSpecificationNumeroTelefone(String numeroTelefone){
        ClienteSpecification numeroTelefoneSpecification;
        numeroTelefoneSpecification = ClienteSpecification.builder()
                .criteria(SearchCriteria.builder()
                        .key("numeroTelefone")
                        .value(numeroTelefone)
                        .operation("%")
                        .build())
                .build();
        return numeroTelefoneSpecification;
    }

    private boolean parametrosValidos(String nome, String email, String numeroTelefone){
        return !(nome == null && email == null && numeroTelefone == null);
    }

    public ResponseEntity<?> atualizarCliente(Long clienteId, String novoNumeroTelefone, String novoEmail) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (clienteId == null || cliente.isEmpty()){
            return ResponseEntity.status(400).
                    body("por favor, informe um Id válido!");
        }
        if(novoNumeroTelefone == null && novoEmail == null){
            return ResponseEntity.status(400).
                    body("por favor, informe ao menos um dos parâmetros: novoTelefone / novoEmail");
        }
        if(novoNumeroTelefone != null){
           cliente.get().setNumeroTelefone(novoNumeroTelefone);
           clienteRepository.save(cliente.get());
        }
        if(novoEmail != null){
            cliente.get().setEmail(novoEmail);
            clienteRepository.save(cliente.get());
        }
        return ResponseEntity.ok().body(clienteMapper.toClienteResponseDTo(cliente.get()));
    }

    public ResponseEntity<?> deletarCliente(Long clienteId) {
        Optional<Cliente> cliente = clienteRepository.findById(clienteId);
        if (clienteId == null || cliente.isEmpty()){
            return ResponseEntity.status(400).
                    body("por favor, informe um Id válido!");
        } else {
            clienteRepository.delete(cliente.get());
            return ResponseEntity.ok("removido com sucesso!");
        }

    }
}
