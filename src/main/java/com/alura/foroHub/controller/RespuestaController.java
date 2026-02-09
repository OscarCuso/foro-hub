package com.alura.foroHub.controller;

import com.alura.foroHub.domain.respuesta.*;
import com.alura.foroHub.domain.service.RespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @Autowired
    private RespuestaRepository repository;

//    @PostMapping
//    @Transactional
//    public ResponseEntity registrarRespuesta(@RequestBody @Valid DatosRegistroRespuesta datos){
//        var detalleRespuesta = registro.registrar(datos);
//        return ResponseEntity.ok(detalleRespuesta);
//    }

    @PostMapping("/{id}")
    public ResponseEntity<DatosDetalleRespuesta> registrarRespuesta(
            @PathVariable Long id,
            @RequestBody @Valid DatosRegistroRespuesta datos,
            Principal principal
    ){
        Respuesta respuesta = respuestaService.registrarRespuesta(
                id,
                datos,
                principal.getName()
                );
        return ResponseEntity.ok(new DatosDetalleRespuesta(respuesta));
    }

    @GetMapping
    public ResponseEntity<?> listarRespuesta(
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size = 10, sort ={"fechaCreacion"})
            Pageable paginacion){
        if(anio != null){
            var inicio = LocalDateTime.of(anio, 1, 1, 0, 0);
            var fin = LocalDateTime.of(anio, 12, 31, 23, 59, 59);
            return ResponseEntity.ok(
                    repository.findByAnio(inicio, fin, paginacion)
                            .map(DatosListaRespuesta::new)
            );
        }
        return ResponseEntity.ok(repository.findAll(paginacion)
                .map(DatosListaRespuesta::new)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarRespuesta(@PathVariable Long id){
        var respuesta = repository.getReferenceById(id);
        return  ResponseEntity.ok(new DatosListaRespuesta(respuesta));
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datos, @PathVariable Long id){
        var respuesta = repository.findById(id);
        if(respuesta.isPresent()){
            var respuestaActualizada = respuesta.get();
            respuestaActualizada.actualizarInformacion(datos);
            repository.save(respuestaActualizada);
            return ResponseEntity.ok(new DatosListaRespuesta(respuestaActualizada));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        var respuesta = repository.findById(id);
        if(respuesta.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
