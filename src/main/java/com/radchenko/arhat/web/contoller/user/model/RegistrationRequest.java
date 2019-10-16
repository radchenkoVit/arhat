package com.radchenko.arhat.web.contoller.user.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RegistrationRequest {

    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @NotNull
    private String name;

    @Email(message = "Email address should be valid")
    @Size(max = 100, message = "Email address must not be more than 100 characters")
    @NotNull
    private String email;

    @Size(min = 2, max = 30, message = "Password must be between 6 and 30 characters")
    @NotNull
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
