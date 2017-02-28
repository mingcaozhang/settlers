package com.example.models.gameModels;

import java.util.List;

/**
 * Created by G on 17/02/27.
 */
public abstract class OwnableUnit {
    private Player aOwner;
    private Player.Color aColor;

    public Player getOwner(){return aOwner; }
    public void setOwner(Player pOwner)
    {
        aOwner = pOwner;
        aColor = pOwner.getColor();
    }

    public abstract List getUnits();
}
