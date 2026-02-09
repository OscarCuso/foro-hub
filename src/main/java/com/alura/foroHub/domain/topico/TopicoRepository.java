package com.alura.foroHub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    Page<Topico> findAll(Pageable pageable);



    Page<Topico> findByCursoNombre(String curso, Pageable pageable);

    @Query("""
            select t from Topico t
            where t.fechaCreacion between :inicio and :fin
            """)
    Page<Topico> findByAnio(@Param("inicio")LocalDateTime inicio, @Param("fin")LocalDateTime fin, Pageable pageable);


//    @Query("""
//            select t from Topico t
//            where t.nombreCurso = :curso
//            and t.fechaCreacion between :inicio and :fin
//            """)
//    Page<Topico> findByNombreCursoAndAnio(@Param("curso") String curso, @Param("inicio")LocalDateTime inicio, @Param("fin")LocalDateTime fin, Pageable pageable);

    Optional<Topico> findById(Long id);
}
