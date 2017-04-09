package com.example.models.gameModels;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import javax.persistence.OneToOne;

@Embeddable
public class OwnedKnight {

    public OwnedKnight(){}

    @OneToOne
    private Player aOwner;
    private Unit.Knight aUnit;
    private boolean aActiveState;

    public OwnedKnight(Player pOwner, Unit.Knight pUnit){
        aOwner= pOwner;
        aUnit = pUnit;
    }

    public Player getOwner(){
        return aOwner;
    }

    public boolean getState(){
        return aActiveState;
    }

    public int getStrength(){
        return aUnit.strength();
    }

    public Unit.Knight getUnit(){
        return aUnit;
    }

    public Unit.Knight getUpgrade(){
        return Unit.Knight.values()[aUnit.ordinal() + 1];
    }

    public boolean canUpgrade(){
        if (aUnit.ordinal() == Unit.Knight.values().length - 1){
            return false;
        }
        return true;
    }
}
