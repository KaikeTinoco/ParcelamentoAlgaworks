package com.algaworks.parcelamento.api.exception;

import com.algaworks.parcelamento.api.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

//uso em todos exceptions handlers

@ControllerAdvice
public class DadosMalInformadosHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    @ExceptionHandler(DadosMalInformadosException.class)
    public ErrorDTO handler(DadosMalInformadosException exception){
        return new ErrorDTO(exception.getMessage(), exception.getLancamento());
    }
}
