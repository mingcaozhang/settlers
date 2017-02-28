package com.example.models.gameModels;
import java.util.List;
import java.util.ArrayList;
/**
 * Created by G on 17/02/27.
 */
public final class Road extends EdgeUnit {

    private static final List<Road> aRoads = new ArrayList<Road>();

    static{
        int maxRoads = 60;
        for(int i = 0; i < maxRoads; i++){
            Road newRoad = new Road();
        }
    }

    private Road(){
    }

    @Override
    public List<Road> getUnits(){
        List<Road> aCopy = new ArrayList<Road>();
        aCopy = aRoads;
        return aCopy;
    }
}
