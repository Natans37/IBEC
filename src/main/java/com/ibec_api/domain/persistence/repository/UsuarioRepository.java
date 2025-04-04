package com.ibec_api.domain.persistence.repository;

import com.ibec_api.domain.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
}
