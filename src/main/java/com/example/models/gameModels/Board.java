package com.example.models.gameModels;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Entity
public class Board {
    @Override
    public String toString() {
        return "Board{" +
                "boardid=" + boardid +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long boardid;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String,Hex> aHexes = new HashMap<>();
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String,Edge> aEdges  = new HashMap<>();
    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Map<String,Intersection> aIntersections  = new HashMap<>();
    @ElementCollection
    private Map<Integer, ArrayList<LandHex>> aLandHexes = new HashMap<>();


    public Board(){
        aHexes = new HashMap<>();
        aEdges = new HashMap<>();
        aIntersections = new HashMap<>();
        aLandHexes = new HashMap<>();
    }


    public Map<String, Hex> getHexes(){
        return aHexes;
    }


    public Map<String, Edge> getEdges(){
        return aEdges;
    }


    public Map<String, Intersection> getIntersections(){
        return aIntersections;
    }


    public Map<Integer, ArrayList<LandHex>> getLandHexes(){
        return aLandHexes;
    }


    public void setAllNeighbours(){
        System.out.println("Friendly Neighbourhood SPODERMAN");
        for (String aKey : aHexes.keySet()){
            System.out.println(aHexes.get(aKey).getId());
            System.out.println(aHexes.get(aKey).getId());
            aHexes.get(aKey).setIntersectionNeighbours(aIntersections);
            aHexes.get(aKey).setHexNeighbours(aHexes);
            aHexes.get(aKey).setEdgeNeighbours(aEdges);
        }

        for (String aKey : aEdges.keySet()){
            System.out.println(aEdges.get(aKey).getId());
            System.out.println(aEdges.get(aKey).getId());
            aEdges.get(aKey).setIntersectionNeighbours(aIntersections);
            aEdges.get(aKey).setHexNeighbours(aHexes);
            aEdges.get(aKey).setEdgeNeighbours(aEdges);
        }

        for (String aKey : aIntersections.keySet()){
            System.out.println(aIntersections.get(aKey).getId());
            aIntersections.get(aKey).setIntersectionNeighbours(aIntersections);
            aIntersections.get(aKey).setHexNeighbours(aHexes);
            aIntersections.get(aKey).setEdgeNeighbours(aEdges);
        }
        System.out.println("Neighbours set");
    }


    @Override
    public int hashCode() {
        int result = boardid.hashCode();
        return result;
    }
}
