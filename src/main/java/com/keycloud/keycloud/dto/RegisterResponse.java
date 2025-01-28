package com.keycloud.keycloud.dto;

import com.keycloud.keycloud.model.Usuario;

public class RegisterResponse {

    private Usuario usuario;
    private ErrorResponse error;


    public RegisterResponse(Usuario usuario, ErrorResponse error) {
        this.usuario = usuario;
        this.error = error;
    }

    public RegisterResponse() {
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
}
