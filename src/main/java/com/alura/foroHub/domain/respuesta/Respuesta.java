package com.alura.foroHub.domain.respuesta;

import com.alura.foroHub.domain.topico.Topico;
import com.alura.foroHub.domain.usuario.Usuario;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name= "respuestas")
@Entity(name= "Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private LocalDateTime fechaCreacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private Boolean solucion;

    public Respuesta(Long id, String mensaje, Topico topico, Usuario autor){
        this.id = null;
        this.mensaje = mensaje;
        this.topico = topico;
        this.fechaCreacion = LocalDateTime.now();
        this.autor = autor;
        this.solucion = false;
    }

    public void actualizarInformacion(@Valid DatosActualizarRespuesta datos) {
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
    }

    public void setSolucion(Boolean solucion) {
        this.solucion = solucion;
    }
}
