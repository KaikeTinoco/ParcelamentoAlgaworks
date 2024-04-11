package com.algaworks.parcelamento.api.dto;

import com.algaworks.parcelamento.api.domain.model.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
@Component
public class ClienteMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Cliente toCliente (ClienteCreateDTO clienteCreateDTO){
        Cliente clienteNovo = modelMapper.map(clienteCreateDTO, Cliente.class);
        return clienteNovo;
    }

    public ClienteResponseDTo toClienteResponseDTo (Cliente cliente){
        ClienteResponseDTo clienteResponseDTo = modelMapper.map(cliente, ClienteResponseDTo.class);
        return clienteResponseDTo;
    }

    public List<ClienteResponseDTo> toClienteResponseDTOList (List<Cliente> clientes){
        return clientes.stream().map(cliente -> toClienteResponseDTo(cliente)).collect(Collectors.toList());
    }
}
