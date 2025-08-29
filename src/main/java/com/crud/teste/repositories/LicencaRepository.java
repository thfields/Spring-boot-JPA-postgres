package com.crud.teste.repositories;

import com.crud.teste.models.Licenca;
import com.crud.teste.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface LicencaRepository extends JpaRepository<Licenca, UUID> {
    @Query("SELECT u FROM  Licenca u WHERE u.ativo = true ")
    List<Licenca> findAllAtivos();

    boolean existsByUsuarioAndAtivoTrueAndDataDeExpiracaoAfter(Usuario usuario, LocalDateTime agora);
}
