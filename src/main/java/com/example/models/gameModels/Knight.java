package com.example.models.gameModels;

/**
 * Created by G on 17/03/02.
 */

public abstract class Knight extends IntersectionUnit {
    private boolean aActiveState;
    private final int aStrength;

    public void updateState(){
        aActiveState = !aActiveState;
    }

    public boolean getState(){
        return aActiveState;
    }

    public int getStrength(){
        return aStrength;
    }

    protected Knight(int pStrength){
        aActiveState = false;
        aStrength = pStrength;
    }
}
