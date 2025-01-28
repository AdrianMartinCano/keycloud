package com.keycloud.keycloud.controller;



import com.keycloud.keycloud.dto.LoginRequest;
import com.keycloud.keycloud.dto.LoginResponse;
import com.keycloud.keycloud.dto.RegisterResponse;
import com.keycloud.keycloud.dto.UsuarioDTO;
import com.keycloud.keycloud.model.Usuario;
import com.keycloud.keycloud.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping("/registrar")
    public RegisterResponse crearUsuario(@RequestBody Usuario usuario) {
        return usuarioService.crearUsuario(usuario);
    }


    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return usuarioService.login(loginRequest.getNombreUsuario(), loginRequest.getContraseña());
    }

    @GetMapping("/{id}")
    public UsuarioDTO getUsuario(@PathVariable long id) {
        return usuarioService.getUsuario(id);
    }

    @PutMapping("/modificar")
    public Usuario modificarUsuario(@RequestBody Usuario usuario) {
        return usuarioService.modificarUsuario(usuario);
    }

    @GetMapping("/email/{email}")
    public UsuarioDTO getUsuarioByEmail(@PathVariable String email) {
        return usuarioService.usuarioPorEmail(email);
    }


}
