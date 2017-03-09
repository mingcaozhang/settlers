package com.example.viewobjects;

/**
 * Created by tooji on 3/9/2017.
 */
public class PlayerAndPhase {

    private String username;
    private String turnPhase;
    private String p1Color = "yellow";
    private String p2Color = "blue";
    private String p3Color = "orange";
    private String p4Color = "Red";

    public PlayerAndPhase(String pUsername, String pTurnPhase){
        username = pUsername;
        turnPhase = pTurnPhase;
    }

    public String getP1Color() {
        return p1Color;
    }

    public String getP2Color() {
        return p2Color;
    }

    public String getP3Color() {
        return p3Color;
    }

    public String getP4Color() {
        return p4Color;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTurnPhase() {
        return turnPhase;
    }

    public void setTurnPhase(String turnPhase) {
        this.turnPhase = turnPhase;
    }
}
