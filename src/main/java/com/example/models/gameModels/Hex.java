package com.example.models.gameModels;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by G on 17/02/27.
 */
public abstract class Hex extends Geometry{

    public abstract TerrainType getTerrainType();
    private boolean aVillain;

    public Hex(int x,int y) {
        super(x,y);
        aVillain=false;
    }

    public boolean hasVillain(){
        return aVillain;
    }

    public void updateRobberFlag()
    {
        if(aVillain==true)
            aVillain = false;
        else
            aVillain = true;
    }

    public void setEdgeNeighbours(Edge[][] pEdges){
        int x = this.getCoordinates().getX()*2;
        int y = this.getCoordinates().getY()*2;

        EdgeNeighbours.add(pEdges[x-1][y-1]);
        EdgeNeighbours.add(pEdges[x-1][y+1]);
        EdgeNeighbours.add(pEdges[x-2][y]);
        EdgeNeighbours.add(pEdges[x+2][y]);
        EdgeNeighbours.add(pEdges[x+1][y-1]);
        EdgeNeighbours.add(pEdges[x+1][y+1]);
    }

    @Override
    public void setHexNeighbours(Hex[][] pHexes ){
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();

        HexNeighbours.add(pHexes[x-2][y]);
        HexNeighbours.add(pHexes[x+2][y]);
        HexNeighbours.add(pHexes[x+1][y-1]);
        HexNeighbours.add(pHexes[x+1][y+1]);
        HexNeighbours.add(pHexes[x-1][y+1]);
        HexNeighbours.add(pHexes[x-1][y-1]);
        HexNeighbours.add(pHexes[x][y-2]);
        HexNeighbours.add(pHexes[x][y+2]);
    }

    @Override
    public void setIntersectionNeighbours(Intersection[][] pIntersections){
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();

        IntersectionNeighbours.add(pIntersections[x][y]);
        IntersectionNeighbours.add(pIntersections[x][y-1]);
        IntersectionNeighbours.add(pIntersections[x+1][y]);
        IntersectionNeighbours.add(pIntersections[x+1][y]);
        IntersectionNeighbours.add(pIntersections[x-1][y-1]);
        IntersectionNeighbours.add(pIntersections[x-1][y-1]);
    }

}
