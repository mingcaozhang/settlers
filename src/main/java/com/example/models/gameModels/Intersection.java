package com.example.models.gameModels;

import java.util.Queue;

/**
 * Created by G on 17/02/27.
 */
public class Intersection extends Geometry {

    private HarbourType aHarbour;
    private IntersectionUnit aOccupant;
    private boolean isOccupied;


    public Intersection(int x, int y,HarbourType pHarbour)
    {
        super(x,y);
        aHarbour = pHarbour;
        isOccupied = false;
    }

    public boolean getOccupancyFlag(){return isOccupied;}

    public void setOccupant(IntersectionUnit pOccupant)
    {
        aOccupant = pOccupant;
        isOccupied = true;
    }
    public IntersectionUnit removeOccupant()
    {
        IntersectionUnit tempUnit = aOccupant;
        aOccupant = null;
        isOccupied = false;
        return tempUnit;
    }

    @Override
    public Queue<Hex> getHexNeighbours() {
        return null; // NOT DONE
    }
    @Override
    public Queue<Intersection> getIntersectionNeighbours() {
        return null; // NOT DONE
    }
    @Override
    public Queue<Edge> getEdgeNeighbours() {
        return null; // NOT DONE
    }
}