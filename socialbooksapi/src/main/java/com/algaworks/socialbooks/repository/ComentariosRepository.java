package com.algaworks.socialbooks.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algaworks.socialbooks.domain.comentario;

public interface ComentariosRepository extends JpaRepository<comentario, Long> {

}
