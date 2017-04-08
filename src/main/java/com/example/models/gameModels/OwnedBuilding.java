package com.example.models.gameModels;


import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;

@Embeddable
public class OwnedBuilding {

    public OwnedBuilding(){}

    @OneToOne
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
