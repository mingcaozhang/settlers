package com.example.models.gameModels;

/**
 * Created by G on 17/02/27.
 */
public class LandHex extends Hex {

    private TerrainType aType;
    private int aProductionNumber;
    private boolean hasMerchant;
    private boolean hasRobber;

    public LandHex(int x, int y, int pProd, TerrainType pType){
        super(x,y);
        aType = pType;
        aProductionNumber = pProd;
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
        if(hasRobber==true)
            hasRobber = false;
        else
            hasRobber = true;
    }

    public boolean getMerchantFlag(){
        return hasMerchant;
    }

    public void updateMerchantFlag()
    {
        if(hasMerchant==true)
            hasMerchant = false;
        else
            hasMerchant = true;
    }

    @Override
    public TerrainType getTerrainType() {
        return aType;
    }


}
