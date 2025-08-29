package com.crud.teste.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Usuario extends Entidade{

    private String nome;

    private LocalDate dataNascimento;

    private String endereco;

}
