package com.radchenko.arhat.web.contoller.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginRequest {
    @Email(message = "Email address should be valid")
    @NotNull
    private String email;

    @Size(min = 2, max = 30, message = "Password must be between 6 and 30 characters")
    @NotNull
    private String password;

    public LoginRequest() {}

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
