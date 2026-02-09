package com.alura.foroHub.domain.topico;

import com.alura.foroHub.domain.curso.DatosDetalleCurso;

import java.time.LocalDateTime;

public record DatosListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        StatusTopico status,
        String autor,
        DatosDetalleCurso curso
) {
    public DatosListaTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                topico.getAutor().getNombre(),
                new DatosDetalleCurso(topico.getCurso())
        );
    }
}
