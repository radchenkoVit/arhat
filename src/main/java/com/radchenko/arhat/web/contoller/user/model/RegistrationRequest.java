package com.radchenko.arhat.web.contoller.user.model;

public class RegistrationRequest {
    private String name;
    private String email;
    private String password;

    public RegistrationRequest(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
