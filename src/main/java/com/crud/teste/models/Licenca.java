package com.crud.teste.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Licenca {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String identificador;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDateTime dataDeExpiracao;

    private String observacaoLicenca;

    public boolean isValida(){
        return this.dataDeExpiracao.isAfter(LocalDateTime.now());
    }
}
