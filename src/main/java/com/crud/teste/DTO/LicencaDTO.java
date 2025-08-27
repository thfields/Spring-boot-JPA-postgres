package com.crud.teste.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LicencaDTO {

    private String identificador;

    private LocalDateTime dataDeExpiracao;

    private String observacaoLicenca;

    private UUID usuarioId;
}
