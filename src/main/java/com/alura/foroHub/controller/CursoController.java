package com.alura.foroHub.controller;

import com.alura.foroHub.domain.curso.*;
import com.alura.foroHub.domain.service.CursoService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.Principal;

@RestController
@RequestMapping("/cursos")
@SecurityRequirement(name = "bearer-key")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity registrarCurso(
            @RequestBody @Valid DatosRegistroCurso datos,
            Principal principal,
            UriComponentsBuilder uriComponentsBuilder
    ){
        var curso = cursoService.registrar(datos, principal.getName());
        var uri = uriComponentsBuilder.path("/cursos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleCurso(curso));
    }

    @GetMapping
    public ResponseEntity<?> listarCursos(
            @RequestParam(required = false) CategoriaCurso categoria,
            @PageableDefault(size= 10, sort = {"categoria"})
            Pageable paginacion
    ){
        if(categoria != null){
            return ResponseEntity.ok(
                    repository.findByCategoria(categoria, paginacion)
                            .map(DatosListaCurso::new)
            );
        }
        return ResponseEntity.ok(repository.findAll(paginacion)
                .map(DatosListaCurso::new)
        );
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity actualizarCurso(@RequestBody @Valid DatosActualizarCurso datos, @PathVariable Long id){
        var curso = repository.findById(id);
        if(curso.isPresent()){
            var cursoActualizado = curso.get();
            cursoActualizado.actualizarInformacion(datos);
            repository.save(cursoActualizado);
            return ResponseEntity.ok(new DatosListaCurso(cursoActualizado));
        }
        return ResponseEntity.notFound().build();
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarCurso(@PathVariable Long id){
        var curso = repository.findById(id);
        if(curso.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.noContent().build();
    }
}
