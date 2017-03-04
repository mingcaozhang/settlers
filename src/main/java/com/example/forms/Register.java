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

    private String confirmpassword;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public String getConfirmPassword()
    {
        return confirmpassword;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public void setConfirmPassword(String confirmpassword)
    {
        this.confirmpassword = confirmpassword;
    }
}
