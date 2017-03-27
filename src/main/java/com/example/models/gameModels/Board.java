package com.example.models.gameModels;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {
    private HashMap<String,Hex> aHexes;
    private HashMap<String,Edge> aEdges;
    private HashMap<String,Intersection> aIntersections;
    private HashMap<Integer, ArrayList<LandHex>> aLandHexes;


    public Board(HashMap<String, Hex> pHexes, HashMap<String, Edge> pEdges, HashMap<String, Intersection> pIntersections, HashMap<Integer, ArrayList<LandHex>> pLandHexes){
        aHexes = new HashMap<>();
        aEdges = new HashMap<>();
        aIntersections = new HashMap<>();
        aLandHexes = new HashMap<>();
        aHexes = pHexes;
        aEdges = pEdges;
        aIntersections = pIntersections;
        aLandHexes = pLandHexes;
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
//    public void setAllNeighbours(){
//        // System.out.println("hello YOUTUBE COMMUNITY");
//        for(int i=0;i< lHexes.size();i++)
//        {
//            lHexes.get(i).setEdgeNeighbours(aEdges);
//            //  waitToSet();
//            lHexes.get(i).setHexNeighbours(aHexes);
//            // waitToSet();
//            lHexes.get(i).setIntersectionNeighbours(aIntersections);
//            // waitToSet();
//            //  System.out.println(lHexes.get(i).getIntersectionNeighbours().peek().getId());
//            //  System.out.println(lHexes.get(i).getId());
//
//            //   System.out.println("hello YOUTUBE COMMUNITY");
//        }
//        for(int i=0;i< lEdges.size();i++)
//        {
//            // waitToSet();
//            lEdges.get(i).setEdgeNeighbours(aEdges);
//            // waitToSet();
//            lEdges.get(i).setHexNeighbours(aHexes);
//            // waitToSet();
//            lEdges.get(i).setIntersectionNeighbours(aIntersections);
//            // waitToSet();
//            //   System.out.println("its my birthday today");
//
//        }
//        for(int i=0;i< lIntersections.size();i++) {
//            lIntersections.get(i).setEdgeNeighbours(aEdges);
//            // waitToSet();
//            lIntersections.get(i).setHexNeighbours(aHexes);
//            // waitToSet();
//            lIntersections.get(i).setIntersectionNeighbours(aIntersections);
//            //  System.out.println("And i am just so happy");
//
//        }
//    }
}
