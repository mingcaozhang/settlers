package com.example.models.gameModels;

/**
 * Created by G on 17/02/27.
 */
public class LandHex extends Hex {

    private TerrainType aType;
    private final int aProductionNumber;
    private boolean hasMerchant;
    private boolean hasRobber;

    public LandHex(int x, int y, int pProductionNumber, TerrainType pType){
        super(x,y);
        aType = pType;
        aProductionNumber = pProductionNumber;
        hasMerchant = false;
        hasRobber = false;
    }

    public int getProductionNumber(){
        return aProductionNumber;
    }

    public boolean getRobberFlag(){
        return hasRobber;
    }

    public void updateRobberFlag()
    {
        if(hasRobber)
            hasRobber = false;
        else
            hasRobber = true;
    }

    public boolean getMerchantFlag(){
        return hasMerchant;
    }

    public void updateMerchantFlag()
    {
        if(hasMerchant)
            hasMerchant = false;
        else
            hasMerchant = true;
    }


    @Override
    public TerrainType getTerrainType() {
        return aType;
    }


}
