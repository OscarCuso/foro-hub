package com.alura.foroHub.domain.service;

import com.alura.foroHub.domain.curso.Curso;
import com.alura.foroHub.domain.curso.CursoRepository;
import com.alura.foroHub.domain.curso.DatosRegistroCurso;
import com.alura.foroHub.domain.usuario.Usuario;
import com.alura.foroHub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Curso registrar(DatosRegistroCurso datos, String usuarioLogueado){

        Usuario autor = usuarioRepository.findUsuarioByNombre(usuarioLogueado);

        if(autor == null){
            throw new ValidationException("Usuario no encontrado");
        }

        Curso curso = new Curso(datos, autor);
        return cursoRepository.save(curso);
    }
}
