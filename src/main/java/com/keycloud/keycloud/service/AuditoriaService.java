package com.keycloud.keycloud.service;

import com.keycloud.keycloud.ENUMS.AccionAuditoria;
import com.keycloud.keycloud.model.EventoAuditoria;
import com.keycloud.keycloud.repository.EventoAuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuditoriaService {


    @Autowired
    private EventoAuditoriaRepository eventoAuditoriaRepository;



    public void registrarEvento(Long idUsuario, AccionAuditoria accion, String descripcion) {
        EventoAuditoria evento = new EventoAuditoria();
        evento.setIdUsuario(idUsuario);
        evento.setAccion(accion.name());
        evento.setDescripcion(descripcion != null ? descripcion : accion.getDescripcion());
        evento.setFechaEvento(LocalDateTime.now());
        eventoAuditoriaRepository.save(evento);
    }
}
