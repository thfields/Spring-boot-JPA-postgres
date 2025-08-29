package com.crud.teste.infra;

import com.crud.teste.exceptions.BuscarUsuarioException;
import com.crud.teste.exceptions.UsuarioComLicencaAtivaException;
import com.crud.teste.models.Usuario;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestUsuarioExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BuscarUsuarioException.class)
    private ResponseEntity<RestUsuarioErroMessage> usuarioNotFoundHandler(BuscarUsuarioException exception){
        RestUsuarioErroMessage resposta = new RestUsuarioErroMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    };

    @ExceptionHandler(UsuarioComLicencaAtivaException.class)
    private ResponseEntity<RestUsuarioErroMessage> usuarioComLicencaAtiva(UsuarioComLicencaAtivaException exception){
        RestUsuarioErroMessage resposta = new RestUsuarioErroMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}

