package com.alura.foroHub.domain.topico;

import com.alura.foroHub.domain.usuario.Usuario;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private String nombreCurso;

//    public Topico(DatosRegistroTopico datos){
//        this.id = null;
//        this.titulo = datos.titulo();
//        this.mensaje = datos.mensaje();
//        this.fechaCreacion = LocalDateTime.now();
//        this.status = true;
//        this.autor = datos.autor();
//        this.nombreCurso = datos.nombreCurso();
//    }

    public Topico(Long id, String titulo, String mensaje, Usuario autor, String nombreCurso){
        this.id = null;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.autor = autor;
        this.nombreCurso = nombreCurso;
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
        if(datos.nombreCurso() != null){
            this.nombreCurso = datos.nombreCurso();
        }
    }
}