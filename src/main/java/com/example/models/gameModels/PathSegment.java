package com.example.models.gameModels;

/**
 * Created by Ming-PC on 4/11/2017.
 */
public class PathSegment {
    private Edge aEdge;
    private boolean aVisited;

    public PathSegment(Edge pEdge){
        aEdge = pEdge;
        aVisited = false;
    }

    public Edge getaEdge() {
        return aEdge;
    }

    public void setaEdge(Edge aEdge) {
        this.aEdge = aEdge;
    }

    public boolean isaVisited() {
        return aVisited;
    }

    public void setaVisited(boolean aVisited) {
        this.aVisited = aVisited;
    }
}
