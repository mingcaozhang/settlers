package com.example.models.gameModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G on 17/03/02.
 */
public class Wall extends OwnableUnit{
    private static final List<Wall> aWalls = new ArrayList<Wall>();

    static {
        int maxWalls = 12;
        for (int i = 0; i < maxWalls; i++) {
            Wall newWall = new Wall();
        }
    }

    private Wall(){
    }

    public List<Wall> getUnits(){
        List<Wall> aCopy = new ArrayList<Wall>();
        aCopy = aWalls;
        return aCopy;
    }
}
