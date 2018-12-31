package com.mynotes.spring.mvc.passwordstrength.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.mynotes.spring.mvc.passwordstrength.constraint.ValidPassword;

public class UserDto {

    @NotEmpty
    private String name;

    @Email
    @NotEmpty
    private String email;

    @ValidPassword
    private String password;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
