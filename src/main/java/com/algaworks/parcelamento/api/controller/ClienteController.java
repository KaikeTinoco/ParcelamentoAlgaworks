package com.algaworks.parcelamento.api.controller;

import com.algaworks.parcelamento.api.domain.model.Cliente;
import com.algaworks.parcelamento.api.dto.ClienteCreateDTO;
import com.algaworks.parcelamento.api.dto.ClienteMapper;
import com.algaworks.parcelamento.api.repositories.ClienteRepository;
import com.algaworks.parcelamento.api.services.ClienteServices;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/clientes")
public class ClienteController {

    @Autowired
    private final ClienteRepository clienteRepository;
    private ClienteServices clienteServices;
    private ClienteMapper clienteMapper;


    @GetMapping(path = "/busca/geral")
    public List<Cliente> buscaGeral(){
        return clienteRepository.findAll();
    }

    @PostMapping(path = "/cadastro")
    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteCreateDTO cliente){
        Cliente clienteCadastrado = clienteMapper.toCliente(cliente);
        return clienteServices.salvarCLiente(clienteCadastrado);
    }

    @PutMapping(path = "/atualizar/{clienteId}/{novoEmail}/{novoNumeroTelefone}")
    public ResponseEntity<?> atualizarCliente(@Valid @PathVariable Long clienteId,
                                              @PathVariable (required = false) String novoEmail,
                                              @PathVariable (required = false) String novoNumeroTelefone){
        return clienteServices.atualizarCliente(clienteId, novoNumeroTelefone, novoEmail);
    }

    @DeleteMapping(path = "/deletar/{clienteId}")
    public ResponseEntity<?> deletarCliente(@Valid @PathVariable Long clienteId){
        return clienteServices.deletarCliente(clienteId);
    }


}
