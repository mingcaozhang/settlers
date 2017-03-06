package com.example.models.gameModels;

/**
 * Created by G on 17/03/03.
 */
public class ProgressCard extends Card {
    public interface ProgresssType {}
    private final ProgresssType aProgressType;
    public ProgresssType getType(){return aProgressType;}
    protected ProgressCard(ProgresssType pProgressType){
        aProgressType = pProgressType;
    }

}
