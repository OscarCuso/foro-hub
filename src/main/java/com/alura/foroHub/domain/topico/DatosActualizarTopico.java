package com.alura.foroHub.domain.topico;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Long autor,
        Long curso
) {
}
