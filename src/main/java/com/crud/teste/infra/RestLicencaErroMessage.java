package com.crud.teste.infra;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class RestLicencaErroMessage {

    private HttpStatus status;
    private String message;
}
