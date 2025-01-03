package com.keycloud.keycloud.ENUMS;


public enum AccionAuditoria {
    CREAR("Crear"),
    LOGIN("Login"),
    ACTUALIZAR("Actualizar"),
    ELIMINAR("Eliminar");

    private final String descripcion;

    AccionAuditoria(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
