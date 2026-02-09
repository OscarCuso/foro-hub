package com.alura.foroHub.domain.curso;

import jakarta.validation.constraints.NotBlank;

public record DatosRegistroCurso(
        @NotBlank String nombre,
        @NotBlank String categoria
) {
}
