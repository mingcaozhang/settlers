package com.example.models.gameModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G on 17/03/02.
 */
public class StrongKnight extends Knight{
    private static List<StrongKnight> aKnights = new ArrayList<StrongKnight>();
    static{
        int maxKnights = 8;
        for(int i = 0; i < maxKnights; i++){
            StrongKnight newStrongKnight = new StrongKnight();
            aKnights.add(newStrongKnight);
        }
    }
    private StrongKnight(){
        super(2);
    }

    public List<StrongKnight> getUnits(){
        List<StrongKnight> aCopy = new ArrayList<StrongKnight>();
        aCopy = aKnights;
        return aCopy;
    }
}
