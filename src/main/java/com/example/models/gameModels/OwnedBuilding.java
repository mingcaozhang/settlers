package com.example.models.gameModels;

public class OwnedBuilding {
    private Player aOwner;
    private Unit.Building aUnit;

    public OwnedBuilding(Player pOwner, Unit.Building pUnit){
        aOwner= pOwner;
        aUnit = pUnit;
    }

    public Player getOwner(){
        return aOwner;
    }

    public boolean isCity(){
        return (aUnit == Unit.Building.CITY);
    }
}
