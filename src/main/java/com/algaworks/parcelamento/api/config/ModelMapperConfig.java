package com.algaworks.parcelamento.api.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper(){
        //injeta na classe
        return new ModelMapper();
    }
}
