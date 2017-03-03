package com.example.models.gameModels;

import java.util.Queue;

/**
 * Created by G on 17/03/02.
 */

public abstract class Knight extends IntersectionUnit {
    private boolean aActiveState;
    private final int aStrength;
    private boolean hasBeenPromoted;

    public void updateState(){
        aActiveState = !aActiveState;
    }

    public boolean getState(){
        return aActiveState;
    }

    public int getStrength(){
        return aStrength;
    }

    public void updatePromotion() {hasBeenPromoted = !hasBeenPromoted;}

    public boolean canUpgrade(){return hasBeenPromoted;}

    protected Knight(int pStrength){
        aActiveState = false;
        aStrength = pStrength;
        hasBeenPromoted = false;
    }
    public abstract Queue getUnits();
}
