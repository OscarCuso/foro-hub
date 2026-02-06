package com.alura.foroHub.domain.respuesta;

import com.alura.foroHub.domain.topico.TopicoRepository;
import com.alura.foroHub.domain.usuario.UsuarioRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistroDeRespuesta {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private RespuestaRepository respuestaRepository;

    public DatosDetalleRespuesta registrar(DatosRegistroRespuesta datos){
        if (!usuarioRepository.existsById(datos.autor())){
            throw new ValidationException("No existe un autor con el id informado");
        }
        if (!topicoRepository.existsById(datos.topico())){
            throw new ValidationException("No existe un topico con el id informado");
        }
        var usuario = usuarioRepository.findById(datos.autor()).get();
        var topico = topicoRepository.findById(datos.topico()).get();
        var respuesta = new Respuesta(null, datos.mensaje(), topico, usuario);
        respuestaRepository.save(respuesta);
        return new DatosDetalleRespuesta(respuesta);
    }
}
