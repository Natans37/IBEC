package com.ibec_api.repository;

import com.ibec_api.domain.entity.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AutorRepository extends JpaRepository<Autor, UUID> {
    Optional<Autor> findByNomeAndSobrenome(String nome, String sobrenome);
}
