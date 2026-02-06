package com.alura.foroHub.controller;

import com.alura.foroHub.domain.service.SolucionService;
import com.alura.foroHub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.security.Principal;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private RegistroDeTopicos registro;

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private SolucionService solucionService;

    @PostMapping
    @Transactional
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos){
        var detalleTopico = registro.registrar(datos);
        return ResponseEntity.ok(detalleTopico);
    }

//    @Transactional
//    @PostMapping
//    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
//        var topico = new Topico(datos);
//        repository.save(topico);
//
//        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
//        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
//    }

    @GetMapping
    public ResponseEntity<?> listarTopicos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size= 10, sort ={"fechaCreacion"})
            Pageable paginacion){
        if(curso != null){
            return ResponseEntity.ok(
                    repository.findByNombreCurso(curso, paginacion)
                            .map(DatosListaTopico::new)
            );
        }
        if(anio != null){
            var inicio = LocalDateTime.of(anio, 1, 1, 0, 0);
            var fin = LocalDateTime.of(anio, 12, 31, 23, 59, 59);
            return  ResponseEntity.ok(
                    repository.findByAnio(inicio, fin, paginacion)
                            .map(DatosListaTopico::new)
            );
        }
        return ResponseEntity.ok(repository.findAll(paginacion)
                .map(DatosListaTopico::new)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity detallarTopico(@PathVariable Long id){
        var topico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DatosListaTopico(topico));
    }

//    @Transactional
//    @PutMapping("/{id}")
//    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos, @PathVariable Long id){
//        var topico = repository.getReferenceById(id);
//        topico.actualizarInformacion(datos);
//
//        return  ResponseEntity.ok(new DatosListaTopico(topico));
//    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datos, @PathVariable Long id){
        var topico = repository.findById(id);
        if(topico.isPresent()){
            var topicoActualizado = topico.get();
            topicoActualizado.actualizarInformacion(datos);
            repository.save(topicoActualizado);
            return ResponseEntity.ok(new DatosListaTopico(topicoActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
        var topico = repository.findById(id);
        if(topico.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{topicoId}/respuestas/{respuestaId}/solucion")
    public ResponseEntity<Void> marcarSolucion(
            @PathVariable Long topicoId,
            @PathVariable Long respuestaId,
            Principal principal
    ) throws AccessDeniedException {
        String usuarioLogueado = principal.getName();

        solucionService.marcarSolucion(
                topicoId,
                respuestaId,
                usuarioLogueado
        );
        return ResponseEntity.noContent().build();
    }
}
