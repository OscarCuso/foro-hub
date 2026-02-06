package com.alura.foroHub.domain.topico;

import java.time.LocalDateTime;

public record DatosDetalleTopico(
        Long id,
        String titulo,
        String mensaje,
        Long autor,
        LocalDateTime fechaCreacion
) {

    public DatosDetalleTopico(Topico topico){
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor().getId(),
                topico.getFechaCreacion()
        );
    }
}
