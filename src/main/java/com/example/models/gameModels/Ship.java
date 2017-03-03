package com.example.models.gameModels;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by G on 17/02/27.
 */
public class Ship extends EdgeUnit{

    private static final List<Ship> aShips = new ArrayList<Ship>();

    static{
        int maxShips = 60;
        for(int i = 0; i < maxShips; i++){
            Ship newShip = new Ship();
        }
    }

    private Ship(){
    }

    @Override
    public List<Ship> getUnits(){
        List<Ship> aCopy = new ArrayList<Ship>();
        aCopy = aShips;
        return aCopy;
    }
}
