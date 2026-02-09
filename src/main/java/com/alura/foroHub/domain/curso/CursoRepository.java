package com.alura.foroHub.domain.curso;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface CursoRepository extends JpaRepository<Curso, Long> {


    Page<Curso> findByCategoria(CategoriaCurso categoria, Pageable paginacion);

    Optional<Curso> findById(Long id);
}
