package com.keycloud.keycloud.service;


import com.keycloud.keycloud.model.Contrasenas;
import com.keycloud.keycloud.repository.ContrasenasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class ContrasenasService {

    @Autowired
    private ContrasenasRepository contrasenasRepository;


    public Contrasenas getContrasenas(Contrasenas c) {
        return contrasenasRepository.save(c);

    }



    public Contrasenas eliminarContrasenapPorId(Long id) {
        Optional<Contrasenas> contrasena = contrasenasRepository.findById(id);
        if (contrasena.isPresent()) {
            contrasenasRepository.deleteById(id);
            return contrasena.get();
        }
        return null;
    }



    public List<Contrasenas> getContrasenasByIdUsuario(Long id) {
        if (id == null) {
            return Collections.emptyList(); // Devuelve una lista vacía si el id no está presente
        }


        return contrasenasRepository.findContrasenasByIdusuario(id);
    }

    public Contrasenas actualizarContrasena(Contrasenas c) {
        // Verifica si la contraseña existe en la base de datos
        Contrasenas contrasenaExistente = contrasenasRepository.findById(c.getId())
                .orElseThrow(() -> new RuntimeException("Contraseña no encontrada"));


        if (c.getTitulo() != null) {
            contrasenaExistente.setTitulo(c.getTitulo());
        }
        if (c.getNombre_usuario() != null) {
            contrasenaExistente.setNombre_usuario(c.getNombre_usuario());
        }
        if (c.getContrasena() != null) {
            contrasenaExistente.setContrasena(c.getContrasena());
        }
        if (c.getUrl() != null) {
            contrasenaExistente.setUrl(c.getUrl());
        }
        if (c.getNotas() != null) {
            contrasenaExistente.setNotas(c.getNotas());
        }
        if (c.getFecha_caducidad() != null) {
            contrasenaExistente.setFecha_caducidad(c.getFecha_caducidad());
        }

        // Guarda y devuelve la entidad actualizada
        return contrasenasRepository.save(contrasenaExistente);
    }


    public Contrasenas guardarContrasena(Contrasenas c) {
        return contrasenasRepository.save(c);
    }


}
