package com.example.models.gameModels;

import java.util.*;

/**
 * Created by G on 17/02/27.
 */
public abstract class OwnableUnit {
    private Player aOwner;
    private Player.Color aColor;

    public void setOwner(Player pOwner)
    {
        aOwner = pOwner;
        aColor = pOwner.getColor();
    }

    public Player getOwner(){
        return aOwner;
    }

    public abstract Queue getUnits();
}
