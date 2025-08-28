package com.crud.teste.DTO;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank(message = "identificador é obrigatório")
    private String identificador;

    @Future(message = "data da expiração da licença já está vencida!")
    private LocalDateTime dataDeExpiracao;

    private String observacaoLicenca;

    private UUID usuarioId;
}
