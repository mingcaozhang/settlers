package com.example.models.gameModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by G on 17/03/02.
 */
public class MightyKnight extends Knight {
    private static final List<MightyKnight> aKnights = new ArrayList<MightyKnight>();
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

    public List<MightyKnight> getUnits(){
        List<MightyKnight> aCopy = new ArrayList<MightyKnight>();
        aCopy = aKnights;
        return aCopy;
    }
}
