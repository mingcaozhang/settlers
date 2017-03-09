package com.example.viewobjects;

/**
 * Created by tooji on 3/9/2017.
 */
public class PlayerAndPhase {

    private String username;
    private String turnPhase;

    public PlayerAndPhase(String pUsername, String pTurnPhase){
        username = pUsername;
        turnPhase = pTurnPhase;
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
