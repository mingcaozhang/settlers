package com.example.models.gameModels;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by G on 17/03/02.
 */
public class MightyKnight extends Knight {
    private static final Queue<MightyKnight> aKnights = new LinkedList<MightyKnight>();
    static{
        int maxKnights = 8;
        for(int i = 0; i < maxKnights; i++){
            MightyKnight newMightyKnight = new MightyKnight();
            aKnights.add(newMightyKnight);
        }
    }
    private MightyKnight(){
        super(3);
    }

    public Queue<MightyKnight> getUnits(){
        Queue<MightyKnight> aCopy = new LinkedList<MightyKnight>();
        aCopy = aKnights;
        return aCopy;
    }
}
