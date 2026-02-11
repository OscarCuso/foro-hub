package com.alura.foroHub.controller;

import com.alura.foroHub.domain.usuario.DatosDetalleUsuario;
import com.alura.foroHub.domain.usuario.DatosRegistroUsuario;
import com.alura.foroHub.domain.usuario.Usuario;
import com.alura.foroHub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/usuarios")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @Transactional
    @PostMapping
    public ResponseEntity registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos, UriComponentsBuilder uriComponentsBuilder){
        var usuario = new Usuario(datos);
        repository.save(usuario);

        var uri = uriComponentsBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(uri).body(new DatosDetalleUsuario(usuario));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        var usuario = repository.findById(id);
        if(usuario.isPresent()){
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
