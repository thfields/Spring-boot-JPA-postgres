package com.crud.teste.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BuscarUsuarioException extends RuntimeException{

    public BuscarUsuarioException() {
        super("usuário não encontrado!");
    }

    public BuscarUsuarioException(String message) {
        super(message);
    }
}
