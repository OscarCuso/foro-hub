package com.alura.foroHub.domain.respuesta;

import com.alura.foroHub.domain.topico.Topico;

import java.time.LocalDateTime;

public record DatosDetalleRespuesta(
        Long id,
        String mensaje,
        Long topico,
        LocalDateTime fechaCreacion,
        Long autor,
        Boolean solucion
) {

    public DatosDetalleRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getTopico().getId(),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getId(),
                respuesta.getSolucion()
        );
    }
}
