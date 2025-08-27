package com.crud.teste.repositories;

import com.crud.teste.models.Licenca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface LicencaRepository extends JpaRepository<Licenca, UUID> {
}
