package com.example.models.gameModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G on 17/02/28.
 */
public class City extends IntersectionUnit {
    private static final List<City> aCities = new ArrayList<City>();
    private boolean hasMetropolis;
    private Metropolis aMetropolis;

    static {
        int maxCities = 16;
        for (int i = 0; i < maxCities; i++) {
            City newCity = new City();
        }
    }

    private City() {
        hasMetropolis = false;
        aMetropolis = null;
    }

    public void addMetropolis(ImprovementType pType){
        assert hasMetropolis == false;
        assert aMetropolis == null;
        aMetropolis = Metropolis.getMetropolis(pType);
        hasMetropolis = true;
    }

    public void removeMetropolis(){
        assert hasMetropolis == true;
        assert aMetropolis != null;
        aMetropolis = null;
        hasMetropolis = false;
    }

    @Override
    public List<City> getUnits() {
        List<City> aCopy = new ArrayList<City>();
        aCopy = aCities;
        return aCopy;
    }
}
