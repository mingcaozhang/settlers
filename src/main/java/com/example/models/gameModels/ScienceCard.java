package com.example.models.gameModels;

/**
 * Created by G on 17/03/03.
 */
public class ScienceCard extends ProgressCard {
    public enum ScienceType implements ProgresssType{
        Alchemist, Crane, Engineer, Inventor, Irrigation, Medecine, Mining, Printer, RoadBuilding, Smith
    }
    ScienceCard(ScienceType pScienceType, String pDescription){super(pScienceType,pDescription); }
}
