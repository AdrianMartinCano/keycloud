package com.keycloud.keycloud.dto;

import com.keycloud.keycloud.model.Usuario;

public class LoginData {

    private Usuario usuario;


    public LoginData(Usuario usuario) {
        this.usuario = usuario;
    }





    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }


}
