package com.alura.foroHub.domain.service;

import com.alura.foroHub.domain.respuesta.DatosRegistroRespuesta;
import com.alura.foroHub.domain.respuesta.Respuesta;
import com.alura.foroHub.domain.respuesta.RespuestaRepository;
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
public class RespuestaService {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Respuesta registrarRespuesta(
            Long topicoId,
            DatosRegistroRespuesta datos,
            String usuarioLogueado
    ){
        Optional<Topico> optionalTopico = topicoRepository.findById(topicoId);

        if(!optionalTopico.isPresent()){
            throw new ValidationException("El topico no existe");
        }
        Topico topico = optionalTopico.get();
        if(topico.getStatus() == StatusTopico.CERRADO){
            throw new ValidationException("El topico esta cerrado");
        }

        Usuario autor = usuarioRepository.findUsuarioByNombre(usuarioLogueado);

        Respuesta respuesta = new Respuesta(
                null,
                datos.mensaje(),
                topico,
                autor
        );
        return respuestaRepository.save(respuesta);
    }
}
