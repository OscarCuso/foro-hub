package com.alura.foroHub.domain.topico;

import jakarta.validation.constraints.NotNull;

public record DatosActualizarTopico(
        String titulo,
        String mensaje,
        Boolean status,
        String autor,
        String nombreCurso
) {
}
