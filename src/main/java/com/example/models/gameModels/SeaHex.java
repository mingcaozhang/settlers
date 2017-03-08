package com.example.models.gameModels;

/**
 * Created by G on 17/02/27.
 */
public class SeaHex extends Hex {

    public SeaHex(int x, int y){
        super(x,y);
    }

    @Override
    public TerrainType getTerrainType() {
        return TerrainType.Sea;
    }


}
