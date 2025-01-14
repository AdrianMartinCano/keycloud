package com.keycloud.keycloud.controller;



import com.keycloud.keycloud.dto.LoginRequest;
import com.keycloud.keycloud.dto.LoginResponse;
import com.keycloud.keycloud.model.Usuario;
import com.keycloud.keycloud.service.AuditoriaService;
import com.keycloud.keycloud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;


    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.login(loginRequest.getNombreUsuario(), loginRequest.getContraseña());
    }

}
