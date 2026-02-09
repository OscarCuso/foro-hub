package com.alura.foroHub.domain.service;

import com.alura.foroHub.domain.curso.Curso;
import com.alura.foroHub.domain.curso.CursoRepository;
import com.alura.foroHub.domain.topico.DatosRegistroTopico;
import com.alura.foroHub.domain.topico.StatusTopico;
import com.alura.foroHub.domain.topico.Topico;
import com.alura.foroHub.domain.topico.TopicoRepository;
import com.alura.foroHub.domain.usuario.Usuario;
import com.alura.foroHub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    public Topico registrar(DatosRegistroTopico datos, String usuarioLogueado){

        Usuario autor = usuarioRepository.findUsuarioByNombre(usuarioLogueado);

        Optional<Curso> optionalCurso = cursoRepository.findById(datos.curso());

        if(autor == null){
            throw new ValidationException("Usuario no encontrado");
        }
        if(!optionalCurso.isPresent()){
            throw new ValidationException("El curso no existe");
        }

        Curso curso = optionalCurso.get();

        Topico topico = new Topico(datos, autor, curso);
        return topicoRepository.save(topico);
    }

    @Transactional
    public void cerrar(Long id, String usuarioLogueado){

        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if(!optionalTopico.isPresent()){
            throw new ValidationException("El topico no existe");
        }
        Topico topico = optionalTopico.get();

        if(!topico.getAutor().getNombre().equals(usuarioLogueado)){
            throw new ValidationException("Usuario no autorizado");
        }

        if(topico.getStatus() == StatusTopico.CERRADO){
            throw new ValidationException("El topico ya esta cerrado");
        }
        topico.setStatus(StatusTopico.CERRADO);
    }
}
