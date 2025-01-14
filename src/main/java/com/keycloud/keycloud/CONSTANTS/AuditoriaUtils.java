package com.keycloud.keycloud.CONSTANTS;

import com.keycloud.keycloud.ENUMS.AccionAuditoria;

import java.util.EnumMap;
import java.util.Map;

public class AuditoriaUtils {
    private static final Map<AccionAuditoria, String> DESCRIPCIONES = new EnumMap<>(AccionAuditoria.class);

    static {
        DESCRIPCIONES.put(AccionAuditoria.CREAR, "Usuario creado con éxito");
        DESCRIPCIONES.put(AccionAuditoria.LOGIN, "Inicio de sesión exitoso");
        DESCRIPCIONES.put(AccionAuditoria.ACTUALIZAR, "Actualización realizada");
        DESCRIPCIONES.put(AccionAuditoria.ELIMINAR, "Eliminación realizada");
        DESCRIPCIONES.put(AccionAuditoria.LISTAR, "Listado realizado");
        DESCRIPCIONES.put(AccionAuditoria.CONTRASENACREAR, "Contraseña creada");
    }

    public static String getDescripcion(AccionAuditoria accion) {
        return DESCRIPCIONES.getOrDefault(accion, "Acción desconocida");
    }
}


