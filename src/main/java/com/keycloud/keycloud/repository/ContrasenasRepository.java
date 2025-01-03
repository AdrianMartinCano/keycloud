package com.keycloud.keycloud.repository;

import com.keycloud.keycloud.model.Contrasenas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ContrasenasRepository extends JpaRepository<Contrasenas, Long> {
    List<Contrasenas> findContrasenasByIdusuario(long idusuario);


}
