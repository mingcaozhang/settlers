package com.example.models.gameModels;

public class OwnedTransport {
    private Player aOwner;
    private Unit.Transport aUnit;

    public OwnedTransport(Player pOwner, Unit.Transport pUnit){
        aOwner= pOwner;
        aUnit = pUnit;
    }

    public Player getOwner(){
        return aOwner;
    }
    public  Unit.Transport getUnit() { return  aUnit; }
}
