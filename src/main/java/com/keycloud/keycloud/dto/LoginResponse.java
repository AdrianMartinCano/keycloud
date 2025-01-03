package com.keycloud.keycloud.dto;

public class LoginResponse {

    private LoginData login;  // Contendrá los datos del usuario si el login es exitoso
    private ErrorResponse error;  // Contendrá el error si hay un fallo

    // Getters y setters
    public LoginData getLogin() {
        return login;
    }

    public void setLogin(LoginData login) {
        this.login = login;
    }

    public ErrorResponse getError() {
        return error;
    }

    public void setError(ErrorResponse error) {
        this.error = error;
    }
}
