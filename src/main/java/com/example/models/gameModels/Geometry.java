package com.example.models.gameModels;


import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by G on 17/02/27.
 */
public abstract class Geometry {
    private int aX;
    private int aY;

    protected Geometry(int x,int y){
        aX = x;
        aY = y;
    }

    protected Queue<Hex> HexNeighbours = new LinkedList<Hex>();
    protected Queue<Intersection> IntersectionNeighbours = new LinkedList<Intersection>();
    protected Queue<Edge> EdgeNeighbours = new LinkedList<Edge>();


    public Coordinate getCoordinates(){return new Coordinate(aX,aY);}

    protected class Coordinate
    {
        private int x;
        private int y;
        Coordinate(int a,int b){
            x = a;
            y = b;
        }
        public int getX(){return x;}
        public int getY(){return y;}
    }

    public Queue<Edge> getEdgeNeighbours(){return EdgeNeighbours;};
    public Queue<Intersection> getIntersectionNeighbours(){return IntersectionNeighbours;};
    public Queue<Hex> getHexNeighbours(){return HexNeighbours;};

    public abstract void setEdgeNeighbours(Edge[][] pEdges);
    public abstract void setHexNeighbours(Hex[][] pHexes);
    public abstract void setIntersectionNeighbours(Intersection[][] pIntersections);
}
