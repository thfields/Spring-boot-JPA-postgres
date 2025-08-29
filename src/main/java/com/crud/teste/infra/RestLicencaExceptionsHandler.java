package com.crud.teste.infra;

import com.crud.teste.exceptions.BuscarLicencaException;
import com.crud.teste.exceptions.UsuarioAtivoException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestLicencaExceptionsHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BuscarLicencaException.class)
    private ResponseEntity<RestLicencaErroMessage> buscarLicencaExceptionHandler(BuscarLicencaException exception){
        RestLicencaErroMessage resposta = new RestLicencaErroMessage(HttpStatus.NOT_FOUND,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(resposta);
    }

    @ExceptionHandler(UsuarioAtivoException.class)
    private ResponseEntity<RestLicencaErroMessage> verificarUsuarioAtivoHandler(UsuarioAtivoException exception){
        RestLicencaErroMessage resposta = new RestLicencaErroMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    private ResponseEntity<RestLicencaErroMessage> verificarUUID(IllegalArgumentException exception){
        RestLicencaErroMessage resposta = new RestLicencaErroMessage(HttpStatus.BAD_REQUEST,exception.getMessage());
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resposta);
    }
}
