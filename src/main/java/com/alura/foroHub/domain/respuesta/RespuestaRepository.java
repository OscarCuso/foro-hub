package com.alura.foroHub.domain.respuesta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface RespuestaRepository extends JpaRepository<Respuesta, Long> {

    Page<Respuesta> findAll(Pageable pageable);

    @Query("""
            select r from Respuesta r
            where r.fechaCreacion between :inicio and :fin
            """)
    Page<Respuesta> findByAnio(@Param("inicio")LocalDateTime inicio, @Param("fin")LocalDateTime fin, Pageable paginacion);
}
