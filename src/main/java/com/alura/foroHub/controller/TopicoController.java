package com.alura.foroHub.controller;

import com.alura.foroHub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/topicos")
//@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity registrarTopico(@RequestBody @Valid DatosRegistroTopico datos, UriComponentsBuilder uriComponentsBuilder){
        var topico = new Topico(datos);
        repository.save(topico);

        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleTopico(topico));
    }

    @GetMapping
    public ResponseEntity<?> listarTopicos(
            @RequestParam(required = false) String curso,
            @RequestParam(required = false) Integer anio,
            @PageableDefault(size= 10, sort ={"fechaCreacion"})
            Pageable paginacion){
        if(curso != null){
            var topicoBuscadoCurso = repository.findByNombreCurso(curso, paginacion);
            return ResponseEntity.ok(topicoBuscadoCurso.getContent());
        }
        if(anio != null){
            var inicio = LocalDateTime.of(anio, 1, 1, 0, 0);
            var fin = LocalDateTime.of(anio, 12, 31, 23, 59, 59);
            var topicoBuscadoAnio = repository.findByAnio(inicio, fin, paginacion);
            return  ResponseEntity.ok(topicoBuscadoAnio.getContent());
        }
        return ResponseEntity.ok(repository.findAll(paginacion));
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
}
