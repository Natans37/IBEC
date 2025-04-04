package com.ibec_api.domain.persistence.repository;

import com.ibec_api.domain.persistence.entity.Editora;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EditoraRepository extends JpaRepository<Editora, Long> {
    Optional<Editora> findByNome(String nome);
}
