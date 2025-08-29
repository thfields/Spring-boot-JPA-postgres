package com.crud.teste.exceptions;

public class UsuarioAtivoException extends RuntimeException {
    public UsuarioAtivoException() {
        super("Usuário selecionado está inativo! Por favor selecione outro usuário.");
    }
    public UsuarioAtivoException(String message) {
        super(message);
    }
}
