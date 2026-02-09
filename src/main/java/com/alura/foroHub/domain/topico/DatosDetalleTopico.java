package com.alura.foroHub.domain.topico;

import com.alura.foroHub.domain.curso.Curso;
import com.alura.foroHub.domain.curso.DatosDetalleCurso;
import com.alura.foroHub.domain.usuario.Usuario;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        String autor,
        StatusTopico status,
        DatosDetalleCurso curso,
        LocalDateTime fechaCreacion
) {

    public DatosDetalleTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getNombre(),
                topico.getStatus(),
                new DatosDetalleCurso(topico.getCurso()),
                topico.getFechaCreacion()
        );
    }
}
