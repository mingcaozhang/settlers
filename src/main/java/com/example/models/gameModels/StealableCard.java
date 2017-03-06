package com.example.models.gameModels;

/**
 * Created by G on 17/03/02.
 */
public abstract class StealableCard extends Card {
    private final StealableType aStealableType;
    public StealableType getType(){
        return aStealableType;
    }

    protected StealableCard(StealableType pStealableType){
        aStealableType = pStealableType;
    }

    public interface StealableType {
    }
}
