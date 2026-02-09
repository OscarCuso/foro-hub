package com.alura.foroHub.domain.respuesta;

import com.alura.foroHub.domain.topico.DatosDetalleTopico;

import java.time.LocalDateTime;

public record DatosListaRespuesta(
        Long id,
        String mensaje,
        DatosDetalleTopico topico,
        LocalDateTime fechaCreacion,
        String autor,
        Boolean solucion
) {
    public DatosListaRespuesta(Respuesta respuesta){
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                new DatosDetalleTopico(respuesta.getTopico()),
                respuesta.getFechaCreacion(),
                respuesta.getAutor().getNombre(),
                respuesta.getSolucion()
        );
    }
}
