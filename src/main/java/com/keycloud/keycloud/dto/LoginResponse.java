package com.keycloud.keycloud.dto;

public class LoginResponse {

    private LoginData login;
    private ErrorResponse error;


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
