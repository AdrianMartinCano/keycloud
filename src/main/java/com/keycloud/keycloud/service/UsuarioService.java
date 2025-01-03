package com.keycloud.keycloud.service;


import com.keycloud.keycloud.CONSTANTS.AuditoriaUtils;
import com.keycloud.keycloud.ENUMS.AccionAuditoria;
import com.keycloud.keycloud.dto.ErrorResponse;
import com.keycloud.keycloud.dto.LoginData;
import com.keycloud.keycloud.dto.LoginResponse;
import com.keycloud.keycloud.model.EventoAuditoria;
import com.keycloud.keycloud.model.Usuario;
import com.keycloud.keycloud.repository.EventoAuditoriaRepository;
import com.keycloud.keycloud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;



@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoAuditoriaRepository eventoAuditoriaRepository;



    public Usuario crearUsuario(Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());  // Para obtener fecha y hora
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        registrarEvento(usuario.getId(), AccionAuditoria.CREAR, AuditoriaUtils.getDescripcion(AccionAuditoria.CREAR));


        return usuarioGuardado;

    }

    public LoginResponse login(String nombreUsuario, String contraseña) {
        LoginResponse response = new LoginResponse();

        // Buscar el usuario por su nombre
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);

        // Si no existe el usuario, retorna un error
        if (usuarioOpt.isEmpty()) {
            ErrorResponse error = new ErrorResponse(404, "Usuario no encontrado");
            response.setError(error);
            registrarEvento((long) -1, AccionAuditoria.LOGIN, error.getCodigo() + " " + error.getDescripcion());
            return response;
        }

        // Comparar la contraseña (sin cifrado en este caso)
        Usuario usuario = usuarioOpt.get();
        if (!usuario.getPasswd().equals(contraseña)) {
            ErrorResponse error = new ErrorResponse(401, "Contraseña incorrecta");
            response.setError(error);
            registrarEvento(usuario.getId(), AccionAuditoria.LOGIN, error.getCodigo() + " " + error.getDescripcion());
            return response; // Salir sin configurar login
        }

        // Si el login fue exitoso, llenar los datos del usuario
        response.setLogin(new LoginData(usuario.getId(), usuario.getNombreUsuario()));
        registrarEvento(usuario.getId(), AccionAuditoria.LOGIN, AuditoriaUtils.getDescripcion(AccionAuditoria.LOGIN));
        return response;
    }






    private void registrarEvento(Long idUsuario, AccionAuditoria accion, String descripcion) {
        EventoAuditoria evento = new EventoAuditoria();
        evento.setIdUsuario(idUsuario);
        evento.setAccion(accion.name());
        evento.setDescripcion(descripcion != null ? descripcion : accion.getDescripcion());
        evento.setFechaEvento(LocalDateTime.now());
        eventoAuditoriaRepository.save(evento);
    }


}