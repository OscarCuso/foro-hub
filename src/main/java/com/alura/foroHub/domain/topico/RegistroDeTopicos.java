//package com.alura.foroHub.domain.topico;
//
//import com.alura.foroHub.domain.usuario.UsuarioRepository;
//import jakarta.validation.ValidationException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class RegistroDeTopicos {
//
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private TopicoRepository topicoRepository;
//
//    public DatosDetalleTopico registrar(DatosRegistroTopico datos){
//        if (!usuarioRepository.existsById(datos.autor())){
//            throw new ValidationException("No existe un autor con el id informado");
//        }
//        var usuario = usuarioRepository.findById(datos.autor()).get();
//        var topico = new Topico(null, datos.titulo(), datos.mensaje(), usuario, datos.nombreCurso());
//        topicoRepository.save(topico);
//        return new DatosDetalleTopico(topico);
//    }
//}
