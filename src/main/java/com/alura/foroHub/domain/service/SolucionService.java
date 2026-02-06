package com.alura.foroHub.domain.service;

import com.alura.foroHub.domain.respuesta.Respuesta;
import com.alura.foroHub.domain.respuesta.RespuestaRepository;
import com.alura.foroHub.domain.topico.Topico;
import com.alura.foroHub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SolucionService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Transactional
    public void marcarSolucion(
            Long topicoId,
            Long respuestaId,
            String usuarioLogueado
    ){
        Optional<Topico> optionalTopico = topicoRepository.findById(topicoId);

        if(!optionalTopico.isPresent()){
            throw new ValidationException("El topico no existe");
        }
        Topico topico = optionalTopico.get();

        if(!topico.getAutor().getNombre().equals(usuarioLogueado)){
            throw new ValidationException("No autorizado");
        }

        Optional<Respuesta> optionalRespuesta = respuestaRepository.findById(respuestaId);

        if(!optionalRespuesta.isPresent()){
            throw new ValidationException("La respuesta no existe");
        }

        Respuesta respuesta = optionalRespuesta.get();

        respuesta.setSolucion(true);
    }
}
