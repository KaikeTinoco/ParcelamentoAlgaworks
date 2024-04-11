package com.algaworks.parcelamento.api.dto;

import com.algaworks.parcelamento.api.domain.model.Parcelamento;
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
public class ParcelamentoMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Parcelamento toParcelamento(ParcelamentoCreateDTO parcelamentoCreateDTO){
        Parcelamento parcelamento = modelMapper.map(parcelamentoCreateDTO, Parcelamento.class);
        return parcelamento;
    }

    public ParcelamentoResponseDTO toDTO(Parcelamento parcelamento){
        ParcelamentoResponseDTO dto = modelMapper.map(parcelamento, ParcelamentoResponseDTO.class);
        return dto;
    }

    public List<ParcelamentoResponseDTO> toDTOList(List<Parcelamento> parcelamentos){
        return parcelamentos.stream().map(parcelamento -> toDTO(parcelamento)).collect(Collectors.toList());
    }
}
