package com.keycloud.keycloud.service;


import com.keycloud.keycloud.model.Contrasenas;
import com.keycloud.keycloud.repository.ContrasenasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContrasenasService {

    @Autowired
    private ContrasenasRepository contrasenasRepository;

    public Contrasenas getContrasenas(Contrasenas c) {
        return contrasenasRepository.save(c);

    }



    public List<Contrasenas> getContrasenasByIdUsuario(long id) {
        return contrasenasRepository.findContrasenasByIdusuario(id);
    }
}
