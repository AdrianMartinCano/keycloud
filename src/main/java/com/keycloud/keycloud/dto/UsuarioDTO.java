package com.keycloud.keycloud.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class UsuarioDTO {
    private Long id;
    private String nombreUsuario;
    private String email;
    private ResetTokenDTO resetTokens;
    private ErrorResponse errorResponse;
    private String passwd;





    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public ResetTokenDTO getResetTokens() {
        return resetTokens;
    }

    public void setResetTokens(ResetTokenDTO resetTokens) {
        this.resetTokens = resetTokens;
    }

    public ErrorResponse getErrorResponse() {
        return errorResponse;
    }

    public void setErrorResponse(ErrorResponse errorResponse) {
        this.errorResponse = errorResponse;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}