package com.example.models.gameModels;

/**
 * Created by G on 17/03/03.
 */
public class PoliticsCard extends ProgressCard {
    public enum PoliticsType implements ProgresssType{
        Bishop,Constitution,Deserter, Diplomat, Entrigue, Saboteur, Spy, Warlord, Wedding
    }
    public PoliticsCard(PoliticsType pPoliticsType,String pDescription){super(pPoliticsType, pDescription);}

}
