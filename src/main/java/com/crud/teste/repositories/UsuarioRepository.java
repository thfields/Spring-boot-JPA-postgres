package com.crud.teste.repositories;

import com.crud.teste.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UsuarioRepository  extends JpaRepository<Usuario, UUID> {
    @Query("SELECT u FROM  Usuario u WHERE u.ativo = true ")
    List<Usuario> findAllAtivos();
}
