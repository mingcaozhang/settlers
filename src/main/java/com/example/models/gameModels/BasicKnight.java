package com.example.models.gameModels;
import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;

import java.util.List;
import java.util.ArrayList;
/**
 * Created by G on 17/03/02.
 */
public class BasicKnight extends Knight {
    private static final List<BasicKnight> aKnights = new ArrayList<BasicKnight>();
    static{
        int maxKnights = 8;
        for(int i = 0; i < maxKnights; i++){
            BasicKnight newBasicKnight = new BasicKnight();
            aKnights.add(newBasicKnight);
        }
    }
    private BasicKnight(){
        super(1);
    }

    public List<BasicKnight> getUnits(){
        List<BasicKnight> aCopy = new ArrayList<BasicKnight>();
        aCopy = aKnights;
        return aCopy;
    }
}
