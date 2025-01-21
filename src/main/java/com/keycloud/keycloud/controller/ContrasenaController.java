package com.keycloud.keycloud.controller;

import com.keycloud.keycloud.CONSTANTS.AuditoriaUtils;
import com.keycloud.keycloud.ENUMS.AccionAuditoria;
import com.keycloud.keycloud.model.Contrasenas;
import com.keycloud.keycloud.model.EventoAuditoria;
import com.keycloud.keycloud.repository.EventoAuditoriaRepository;
import com.keycloud.keycloud.service.AuditoriaService;
import com.keycloud.keycloud.service.ContrasenasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/contrasenas")
@CrossOrigin(origins = "http://localhost:4200")
public class ContrasenaController {
    @Autowired
    private ContrasenasService contrasenasService;
    @Autowired
    private AuditoriaService auditoriaService;


    @GetMapping("/{idusuario}")
    public List<Contrasenas> getContrasenasById(@PathVariable long idusuario) {

        auditoriaService.registrarEvento(idusuario, AccionAuditoria.LISTAR, AuditoriaUtils.getDescripcion(AccionAuditoria.LISTAR));
          return contrasenasService.getContrasenasByIdUsuario(idusuario);
    }


     @PostMapping("/modificar")
    public Contrasenas modificarPassword(@RequestBody Contrasenas contrasenas){
         auditoriaService.registrarEvento(contrasenas.getidusuario(), AccionAuditoria.ACTUALIZAR, AuditoriaUtils.getDescripcion(AccionAuditoria.ACTUALIZAR) + " de la contraseña id " + contrasenas.getId());
        return contrasenasService.actualizarContrasena(contrasenas);
    }


    @PostMapping("/crear")
    public Contrasenas crearPassword(@RequestBody Contrasenas contrasenas){
        auditoriaService.registrarEvento(contrasenas.getidusuario(), AccionAuditoria.CREAR, AuditoriaUtils.getDescripcion(AccionAuditoria.CONTRASENACREAR));
        return contrasenasService.guardarContrasena(contrasenas);
    }

    @GetMapping("/eliminar/{id}")
    public Contrasenas BorrarContrasenasById(@PathVariable long id) {
        auditoriaService.registrarEvento(id, AccionAuditoria.ELIMINAR, AuditoriaUtils.getDescripcion(AccionAuditoria.BORRARCONTRASENA));
        return contrasenasService.eliminarContrasenapPorId(id);
    }
}
