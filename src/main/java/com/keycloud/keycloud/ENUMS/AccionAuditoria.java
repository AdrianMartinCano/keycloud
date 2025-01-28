package com.keycloud.keycloud.ENUMS;


import org.apache.tomcat.util.http.fileupload.util.LimitedInputStream;

public enum AccionAuditoria {
    CREAR("Crear"),
    LOGIN("Login"),
    ACTUALIZAR("Actualizar"),
    ELIMINAR("Eliminar"),
    LISTAR("Listadas contraseñas"),
    CONTRASENACREAR("Contraseña creada"),
    BORRARCONTRASENA("Contraseña borrada"),
    CREARCODIGORESTAURARPASSWORD("Se ha creado un token para cambiar la contrasena");

    private final String descripcion;

    AccionAuditoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
