package com.example.models.gameModels;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by G on 17/03/02.
 */
public class StrongKnight extends Knight{
    private static Queue<StrongKnight> aKnights = new LinkedList<StrongKnight>();
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

    public Queue<StrongKnight> getUnits(){
        Queue<StrongKnight> aCopy = new LinkedList<StrongKnight>();
        aCopy = aKnights;
        return aCopy;
    }
}
