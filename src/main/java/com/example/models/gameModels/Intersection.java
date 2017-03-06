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

    public boolean getOccupancyFlag(){
        return isOccupied;
    }

    public IntersectionUnit getOccupant(){
        return aOccupant;
    }

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
    public void setEdgeNeighbours(Edge[][] pEdges){
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();

        EdgeNeighbours.add(pEdges[x*2+1][y*2+1]);
        EdgeNeighbours.add(pEdges[x*2-1][y*2+1]);
        if(x%2==0&&y%2==0 || x%2==1&&y%2==1) { //INTERSECTION UPWARDS
            EdgeNeighbours.add(pEdges[x*2][y*2+2]);
        }
        else { //INTERSECTION DOWNWARDS
            EdgeNeighbours.add(pEdges[x*2][y*2]);
        }
    }

    @Override
    public void setHexNeighbours(Hex[][] pHexes ){
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();

        if(x%2==0&&y%2==0 || x%2==1&&y%2==1) { //INTERSECTION UPWARDS
            HexNeighbours.add(pHexes[x][y]);
            HexNeighbours.add(pHexes[x+1][y+1]);
            HexNeighbours.add(pHexes[x-1][y+1]);
        }
        else { //INTERSECTION DOWNWARDS
            HexNeighbours.add(pHexes[x][y+1]);
            HexNeighbours.add(pHexes[x+1][y]);
            HexNeighbours.add(pHexes[x-1][y]);
        }
    }

    @Override
    public void setIntersectionNeighbours(Intersection[][] pIntersections){
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();

        IntersectionNeighbours.add(pIntersections[x+1][y]);
        IntersectionNeighbours.add(pIntersections[x-1][y]);

        if(x%2==0&&y%2==0 || x%2==1&&y%2==1) { //INTERSECTION UPWARDS

            IntersectionNeighbours.add(pIntersections[x][y+1]);
        }
        else { //INTERSECTION DOWNWARDS
            IntersectionNeighbours.add(pIntersections[x][y-1]);
        }

    }

}