package com.alura.foroHub.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroRespuesta(
        @NotBlank String mensaje,
        @NotNull Long topico,
        @NotNull Long autor
) {
}
