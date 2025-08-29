package com.crud.teste.exceptions;

public class BuscarUsuarioException extends RuntimeException{

    public BuscarUsuarioException() {
        super("usuário não encontrado!");
    }

    public BuscarUsuarioException(String message) {
        super(message);
    }
}
