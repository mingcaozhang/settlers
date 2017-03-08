package com.example.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Register {
    @NotNull
    @Size(min=4, max=20)
    private String username;
    @NotNull
    @Size(min=4, max=20)
    private String password;
    @NotNull
    @Size(min=4, max=20)
    private String confirm;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getConfirm()
    {
        return confirm;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setConfirm(String confirm)
    {
        this.confirm = confirm;
    }
}