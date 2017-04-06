package com.example.models.gameModels;


import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Created by G on 17/02/27.
 */
@Entity
public abstract class Geometry {
    public Geometry(){}
    @Id
    protected String aId; // should be private final
    @OneToMany
    protected String[] splitId; // should be private final

    protected Geometry(String pId){
        aId = pId;
        splitId = pId.split("_");
    }

    @OneToMany
    protected List<Hex> HexNeighbours = new LinkedList<Hex>();
    @OneToMany
    protected List<Intersection> IntersectionNeighbours = new LinkedList<Intersection>();
    @OneToMany
    protected List<Edge> EdgeNeighbours = new LinkedList<Edge>();

    public String getId(){return aId;};

    public String getPrefix(){
        return  splitId[0];
    }

    public int getX(){
        return  Integer.parseInt(splitId[1]);
    }

    public int getY(){
        return  Integer.parseInt(splitId[2]);
    }

    public List<Edge> getEdgeNeighbours(){return EdgeNeighbours;};
    public List<Intersection> getIntersectionNeighbours(){return IntersectionNeighbours;};
    public List<Hex> getHexNeighbours(){return HexNeighbours;};

    public abstract void setEdgeNeighbours(Map<String,Edge> pEdges);
    public abstract void setHexNeighbours(Map<String,Hex> pHexes);
    public abstract void setIntersectionNeighbours(Map<String,Intersection> pIntersections);
}
