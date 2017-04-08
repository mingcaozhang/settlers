package com.example.forms;


public class Login {

    private String username;

    private String password;

    private boolean validStatus = true;

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public boolean getValidStatus(){ return validStatus;}

    public void setValidStatus(boolean status){ validStatus = status;}

}