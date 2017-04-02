package com.example.models.gameModels;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private HashMap<String,Hex> aHexes;
    private HashMap<String,Edge> aEdges;
    private HashMap<String,Intersection> aIntersections;
    private HashMap<Integer, ArrayList<LandHex>> aLandHexes;


    public Board(){
        aHexes = new HashMap<>();
        aEdges = new HashMap<>();
        aIntersections = new HashMap<>();
        aLandHexes = new HashMap<>();
    }

    public HashMap<String, Hex> getHexes(){
        return aHexes;
    }

    public HashMap<String, Edge> getEdges(){
        return aEdges;
    }

    public HashMap<String, Intersection> getIntersections(){
        return aIntersections;
    }

    public HashMap<Integer, ArrayList<LandHex>> getLandHexes(){
        return aLandHexes;
    }
    public void setAllNeighbours(){
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
}
