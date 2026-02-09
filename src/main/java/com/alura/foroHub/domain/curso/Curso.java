package com.alura.foroHub.domain.curso;

import com.alura.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;

    @Column(name = "categoria")
    @Enumerated(EnumType.STRING)
    private CategoriaCurso categoria;


    public Curso(DatosRegistroCurso datos, Usuario autor) {
        this.nombre = datos.nombre();
        this.categoria = CategoriaCurso.valueOf(datos.categoria());
    }

    public void actualizarInformacion(@Valid DatosActualizarCurso datos) {
        if(datos.nombre() != null){
            this.nombre = datos.nombre();
        }
        if(datos.categoria() != null){
            this.categoria = datos.categoria();
        }
    }
}
