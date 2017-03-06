package com.example.models.gameModels;

/**
 * Created by G on 17/03/02.
 */
public abstract class Card {
    private Player aOwner;

    public Player getOwner(){
        return aOwner;
    }
    public void updateOwner(Player pOwner){
        aOwner=pOwner;
    }
    public void updateOwner(){
        aOwner = null;
    }
}