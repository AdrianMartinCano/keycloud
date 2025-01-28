package com.keycloud.keycloud.service;


import com.keycloud.keycloud.CONSTANTS.AuditoriaUtils;
import com.keycloud.keycloud.ENUMS.AccionAuditoria;
import com.keycloud.keycloud.dto.*;
import com.keycloud.keycloud.model.ResetToken;
import com.keycloud.keycloud.model.Usuario;

import com.keycloud.keycloud.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AuditoriaService auditoriaService;
    @Autowired
    private ResetTokenService resetTokenService;


    public UsuarioDTO usuarioPorEmail(String email) {
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(email);
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        // Si el usuario es encontrado
        if (usuarioBuscado.isPresent()) {
            Usuario usuario = usuarioBuscado.get();

            // Crear un nuevo ResetToken
            ResetToken token = new ResetToken();
            token.setUsuario(usuario);  // Asociar el usuario al token
            token.setFechaCreacion(LocalDateTime.now());
            token.setFechaExpiracion(LocalDateTime.now().plusMinutes(30));

            // Crear y guardar el token utilizando el servicio
            resetTokenService.crearToken(token);

            // Registrar el evento de auditoría
            auditoriaService.registrarEvento(usuario.getId(),
                    AccionAuditoria.CREAR,
                    AuditoriaUtils.getDescripcion(AccionAuditoria.CREARCODIGORESTAURARPASSWORD));

            // Mapear el usuario a UsuarioDTO

            usuarioDTO.setId(usuario.getId());
            usuarioDTO.setEmail(usuario.getEmail());
            usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
            usuarioDTO.setPasswd(usuario.getPasswd());

            // Crear el ResetTokenDTO solo con el token recién creado
            ResetTokenDTO tokenDTO = new ResetTokenDTO();
            tokenDTO.setId(token.getId());
            tokenDTO.setToken(token.getToken());
            tokenDTO.setFechaCreacion(token.getFechaCreacion());
            tokenDTO.setFechaExpiracion(token.getFechaExpiracion());


            usuarioDTO.setResetTokens(tokenDTO);


            // Retornar el DTO del usuario con el último token
            //usuarioDTO.setErrorResponse(new ErrorResponse());
            return usuarioDTO;
        }
            usuarioDTO = new UsuarioDTO();
            usuarioDTO.setErrorResponse(new ErrorResponse("404", "Usuario no encontrado"));
            // Si no se encuentra el usuario, puedes lanzar una excepción o devolver un mensaje adecuado
            return usuarioDTO;

    }




    public Usuario modificarUsuario(Usuario usuario){

        Optional<Usuario> usuarioBuscado = usuarioRepository.findById(usuario.getId());
        usuario.setFechaCreacion(usuarioBuscado.get().getFechaCreacion());
        Usuario usuarioModificado = usuarioRepository.save(usuario);
        auditoriaService.registrarEvento(usuario.getId(), AccionAuditoria.ACTUALIZAR, AuditoriaUtils.getDescripcion(AccionAuditoria.ACTUALIZAR));
        return usuarioModificado;
    }

    public RegisterResponse crearUsuario(Usuario usuario) {
        RegisterResponse registerResponse = new RegisterResponse();
        try{
            usuario.setFechaCreacion(LocalDateTime.now());  // Para obtener fecha y hora
            Usuario usuarioGuardado = usuarioRepository.save(usuario);
            auditoriaService.registrarEvento(usuario.getId(), AccionAuditoria.CREAR, AuditoriaUtils.getDescripcion(AccionAuditoria.CREAR));

            Usuario user = new Usuario();
            user.setNombreUsuario(usuarioGuardado.getNombreUsuario());
            user.setEmail(usuarioGuardado.getEmail());
            user.setPasswd(usuarioGuardado.getPasswd());
            user.setFechaCreacion(usuarioGuardado.getFechaCreacion());
            user.setResetTokens(new ArrayList<>());
            registerResponse.setUsuario(user);
            registerResponse.setError(new ErrorResponse());
            return registerResponse;
        }catch (Exception e){


            registerResponse.setUsuario(new Usuario());
            if(e.getMessage().contains("usuarios.UKkfsp0s1tflm1cwlj8idhqsad0")){
                registerResponse.setError(new ErrorResponse("500","El email ya está registrado"));
            }
            if(e.getMessage().contains("usuarios.UKof5vabgukahdwmgxk4kjrbu98")){
                registerResponse.setError(new ErrorResponse("500","El nombre de usuario ya está registrado"));
            }

            return registerResponse;
        }








    }

    public UsuarioDTO getUsuario(Long id){
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if(usuario.isPresent()){
            UsuarioDTO usuarioDTO = new UsuarioDTO();
            usuarioDTO.setId(usuario.get().getId());
            usuarioDTO.setEmail(usuario.get().getEmail());
            usuarioDTO.setNombreUsuario(usuario.get().getNombreUsuario());
            usuarioDTO.setPasswd(usuario.get().getPasswd());
            return usuarioDTO;
        }
        return null;
    }
    public LoginResponse login(String nombreUsuario, String contraseña) {
        LoginResponse response = new LoginResponse();

        // Buscar el usuario por su nombre
        Optional<Usuario> usuarioOpt = usuarioRepository.findByNombreUsuario(nombreUsuario);

        // Si no existe el usuario, retorna un error
        if (usuarioOpt.isEmpty()) {
            ErrorResponse error = new ErrorResponse("404", "Usuario no encontrado: " + nombreUsuario);
            response.setError(error);
            auditoriaService. registrarEvento((long) -1, AccionAuditoria.LOGIN, error.getCodigo() + " " + error.getDescripcion());
            return response;
        }

        // Comparar la contraseña (sin cifrado en este caso)
        Usuario usuario = usuarioOpt.get();
        if (!usuario.getPasswd().equals(contraseña)) {
            ErrorResponse error = new ErrorResponse("401", "Contraseña incorrecta para: " + nombreUsuario);
            response.setError(error);
            auditoriaService.registrarEvento(usuario.getId(), AccionAuditoria.LOGIN, error.getCodigo() + " " + error.getDescripcion());
            return response;
        }


        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setNombreUsuario(usuario.getNombreUsuario());
        usuarioDTO.setEmail(usuario.getEmail());

            LoginData lg;
        lg = new LoginData(new Usuario(usuario.getId(),usuario.getNombreUsuario(), usuario.getEmail(), usuario.getPasswd(), usuario.getFechaCreacion()));


        response.setLogin(lg);

        auditoriaService.registrarEvento(usuario.getId(), AccionAuditoria.LOGIN, AuditoriaUtils.getDescripcion(AccionAuditoria.LOGIN));
        return response;
    }









}