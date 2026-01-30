package com.alura.foroHub.domain.topico;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name= "topicos")
@Entity(name= "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String autor;
    private String nombreCurso;

    public Topico(DatosRegistroTopico datos){
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.autor = datos.autor();
        this.nombreCurso = datos.nombreCurso();
    }

    public void actualizarInformacion(@Valid DatosActualizarTopico datos) {
        if(datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
        if(datos.status() != null){
            this.status = datos.status();
        }
        if(datos.autor() != null){
            this.autor = datos.autor();
        }
        if(datos.nombreCurso() != null){
            this.nombreCurso = datos.nombreCurso();
        }
    }
}