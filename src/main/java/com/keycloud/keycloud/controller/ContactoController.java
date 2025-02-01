package com.keycloud.keycloud.controller;


import com.keycloud.keycloud.model.Contacto;
import com.keycloud.keycloud.service.ContactoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contacto")
@CrossOrigin(origins = "http://localhost:4200")
public class ContactoController {

    @Autowired
    private ContactoService contactoService;



    @PostMapping("/guardar")
    public Contacto guardarContacto(@RequestBody Contacto contacto) {
        return contactoService.guardarMensajeContacto(contacto);
    }
}
