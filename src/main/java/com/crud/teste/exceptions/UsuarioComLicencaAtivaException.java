package com.crud.teste.exceptions;

import com.crud.teste.models.Usuario;

public class UsuarioComLicencaAtivaException extends RuntimeException {

    public UsuarioComLicencaAtivaException(String message) {
        super(message);
    }
}
