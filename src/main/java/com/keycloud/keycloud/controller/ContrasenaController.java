package com.keycloud.keycloud.controller;

import com.keycloud.keycloud.model.Contrasenas;
import com.keycloud.keycloud.service.ContrasenasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contrasenas")
@CrossOrigin(origins = "http://localhost:4200")
public class ContrasenaController {

    @Autowired
    private ContrasenasService contrasenasService;



    //https://chatgpt.com/c/6775a5ee-395c-800a-b86d-b969ccc4d877
    @GetMapping("/{idusuario}")
    public List<Contrasenas> getContrasenasById(@PathVariable long idusuario) {
        return contrasenasService.getContrasenasByIdUsuario(idusuario);
    }

    /*@PostMapping("/")
    public List<Contrasenas> getContrasenasById(@RequestBody Map<String, Long> body) {
        long idusuario = body.get("idusuario");
        return contrasenasService.getContrasenasByIdUsuario(idusuario);
    }*/



}
