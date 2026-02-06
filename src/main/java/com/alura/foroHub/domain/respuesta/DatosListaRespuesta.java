package com.alura.foroHub.domain.respuesta;

import java.time.LocalDateTime;

public record DatosListaRespuesta(
        Long id,
        String mensaje,
        Long topico,
        LocalDateTime fechaCreacion,
        Long autor,
        Boolean solucion
) {
    public DatosListaRespuesta(Respuesta respuesta){
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
