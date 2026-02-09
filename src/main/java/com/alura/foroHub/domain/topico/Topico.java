package com.alura.foroHub.domain.topico;

import com.alura.foroHub.domain.curso.Curso;
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

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusTopico status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "curso_id")
    private Curso curso;

//    public Topico(DatosRegistroTopico datos){
//        this.id = null;
//        this.titulo = datos.titulo();
//        this.mensaje = datos.mensaje();
//        this.fechaCreacion = LocalDateTime.now();
//        this.status = true;
//        this.autor = datos.autor();
//        this.nombreCurso = datos.nombreCurso();
//    }

    public Topico(Long id, String titulo, String mensaje, Usuario autor, Curso curso){
        this.id = null;
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.status = StatusTopico.ABIERTO;
        this.autor = autor;
        this.curso = curso;
    }

    public Topico(DatosRegistroTopico datos, Usuario autor, Curso curso) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.autor = autor;
        this.curso = curso;
        this.status = StatusTopico.ABIERTO;
        this.fechaCreacion = LocalDateTime.now();
    }

    public void actualizarInformacion(@Valid DatosActualizarTopico datos) {
        if(datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
    }

    public void setStatus(StatusTopico status) {
        this.status = status;
    }
}