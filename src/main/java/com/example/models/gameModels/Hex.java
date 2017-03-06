package com.example.models.gameModels;
import java.util.Queue;
/**
 * Created by G on 17/02/27.
 */
public abstract class Hex extends Geometry{

    public Hex(int x,int y) {super(x,y);}
    public abstract TerrainType getTerrainType();

    @Override
    public Hex getHexNeighbours() {
        return null; // NOT DONE
    }
    @Override
    public Queue<Intersection> getIntersectionNeighbours() {
        return null; // NOT DONE
    }
    @Override
    public Edge getEdgeNeighbours() {
        return null; // NOT DONE
    }
}
