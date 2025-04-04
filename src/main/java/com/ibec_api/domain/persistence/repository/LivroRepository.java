package com.ibec_api.domain.persistence.repository;

import com.ibec_api.domain.persistence.entity.Livro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LivroRepository extends JpaRepository<Livro, UUID> {
    Page<Livro> findByEditora(Pageable paginacao, String editora);

}
