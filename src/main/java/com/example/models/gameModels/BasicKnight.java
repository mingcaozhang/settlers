package com.example.models.gameModels;
//import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;

import java.util.Queue;
import java.util.LinkedList;
/**
 * Created by G on 17/03/02.
 */
public class BasicKnight extends Knight {
    private static final Queue<BasicKnight> aBasicKnights = new LinkedList<BasicKnight>();
    static{
        int maxKnights = 8;
        for(int i = 0; i < maxKnights; i++){
            BasicKnight newBasicKnight = new BasicKnight();
            aBasicKnights.add(newBasicKnight);
        }
    }
    private BasicKnight(){
        super(1);
    }

    public static Queue<BasicKnight> getUnits(){
        Queue<BasicKnight> aCopy = new LinkedList<BasicKnight>();
        aCopy = aBasicKnights;
        return aCopy;
    }
}
