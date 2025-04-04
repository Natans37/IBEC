package com.ibec_api.domain.persistence.repository;

import com.ibec_api.domain.persistence.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    Optional<Categoria> findByNome(@NotBlank String nome);
}
