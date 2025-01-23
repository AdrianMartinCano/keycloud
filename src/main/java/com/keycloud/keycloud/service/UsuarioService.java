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
import java.util.Optional;



@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EventoAuditoriaRepository eventoAuditoriaRepository;

    @Autowired
    private AuditoriaService auditoriaService;


    public Usuario modificarUsuario(Usuario usuario){

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(usuario.getId());
        usuario.setFechaCreacion(usuarioBuscado.get().getFechaCreacion());
        Usuario usuarioModificado = usuarioRepository.save(usuario);
        auditoriaService.registrarEvento(usuario.getId(), AccionAuditoria.ACTUALIZAR, AuditoriaUtils.getDescripcion(AccionAuditoria.ACTUALIZAR));
        return usuarioModificado;
    }

    public Usuario crearUsuario(Usuario usuario) {
        usuario.setFechaCreacion(LocalDateTime.now());  // Para obtener fecha y hora
        Usuario usuarioGuardado = usuarioRepository.save(usuario);
        auditoriaService.registrarEvento(usuario.getId(), AccionAuditoria.CREAR, AuditoriaUtils.getDescripcion(AccionAuditoria.CREAR));
        return usuarioGuardado;

    }

    public Usuario getUsuario(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElse(null);
    }
    public LoginResponse login(String nombreUsuario, String contraseña) {
        LoginResponse response = new LoginResponse();

        // Buscar el usuario por su nombre
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);

        // Si no existe el usuario, retorna un error
        if (usuarioOpt.isEmpty()) {
            ErrorResponse error = new ErrorResponse(404, "Usuario no encontrado: " + nombreUsuario);
            response.setError(error);
            auditoriaService. registrarEvento((long) -1, AccionAuditoria.LOGIN, error.getCodigo() + " " + error.getDescripcion());
            return response;
        }

        // Comparar la contraseña (sin cifrado en este caso)
        Usuario usuario = usuarioOpt.get();
        if (!usuario.getPasswd().equals(contraseña)) {
            ErrorResponse error = new ErrorResponse(401, "Contraseña incorrecta para: " + nombreUsuario);
            response.setError(error);
            auditoriaService.registrarEvento(usuario.getId(), AccionAuditoria.LOGIN, error.getCodigo() + " " + error.getDescripcion());
            return response;
        }

        // Si el login fue exitoso, llenar los datos del usuario
        //response.setLogin(new LoginData(usuario.getId(), usuario.getNombreUsuario(), usuario.getPasswd()));

        response.setLogin(new LoginData(usuarioOpt.get()));

        auditoriaService.registrarEvento(usuario.getId(), AccionAuditoria.LOGIN, AuditoriaUtils.getDescripcion(AccionAuditoria.LOGIN));
        return response;
    }









}