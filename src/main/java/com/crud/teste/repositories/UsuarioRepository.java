package com.crud.teste.repositories;

import com.crud.teste.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, UUID> {
    UUID id(UUID id);
}
