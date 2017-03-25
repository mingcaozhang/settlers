package com.example.models.gameModels;

/**
 * Created by G on 17/03/03.
 */
public class ProgressCard extends Card {
    public interface ProgresssType {}
    private final String aDescription;
    private final ProgresssType aProgressType;
    public ProgresssType getType(){return aProgressType;}
    public String getDescription(){return aDescription;}
    protected ProgressCard(ProgresssType pProgressType, String pDescription){
        aDescription = pDescription;
        aProgressType = pProgressType;
    }


}
