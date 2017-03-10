package com.example.models.gameModels;


/**
 * Created by G on 17/02/27.
 */
public class Edge extends Geometry{

    private boolean isOccupied;
    private EdgeUnit aOccupant;

    public Edge(int x, int y)
    {
        super(x,y);
        isOccupied = false;
    }

    public boolean getOccupancyFlag()
    {
       return isOccupied;
    }

    public EdgeUnit getOccupant(){
        return aOccupant;
    }

    public void setOccupant(EdgeUnit pOccupant)
    {
        aOccupant = pOccupant;
        isOccupied = true;
    }

    public EdgeUnit removeOccupant()
    {
        EdgeUnit tempUnit = aOccupant;
        aOccupant = null;
        isOccupied = false;
        return tempUnit;
    }

    public void setEdgeNeighbours(Edge[][] pEdges){
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();

        if(x%2==0) { // EDGES ARE VERTICAL
            EdgeNeighbours.add(pEdges[x+1][y+1]);
            EdgeNeighbours.add(pEdges[x+1][y-1]);
            EdgeNeighbours.add(pEdges[x-1][y+1]);
            EdgeNeighbours.add(pEdges[x-1][y-1]);
        }
        else {
            EdgeNeighbours.add(pEdges[x-2][y]);
            EdgeNeighbours.add(pEdges[x+2][y]);
            if(pEdges[x+1][y+1]==null){ // EDGES RIGHT SIDE IS DOWN
                EdgeNeighbours.add(pEdges[x - 1][y+1]);
                EdgeNeighbours.add(pEdges[x + 1][y-1]);
            }
            else {  // EDGES LEFT SIDE IS DOWN
                EdgeNeighbours.add(pEdges[x - 1][y-1]);
                EdgeNeighbours.add(pEdges[x + 1][y+1]);
            }
        }
    }

    public void setHexNeighbours(Hex[][] pHexes){
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();

        if(x%2==0) { // EDGES ARE VERTICAL
            x=x/2;
            y=y/2;
            HexNeighbours.add(pHexes[x-1][y]);
            HexNeighbours.add(pHexes[x+1][y]);
        }
        else if( ((x+1)/2)%2==0&&((y+1)/2)%2==0 || ((x+1)/2)%2==1&&((y+1)/2)%2==1){ // EDGES RIGHT SIDE IS DOWN
            HexNeighbours.add(pHexes[(x-1)/2][(y-1)/2]);
            HexNeighbours.add(pHexes[(x+1)/2][(y+1)/2]);
        }
        else {  // EDGES LEFT SIDE IS DOWN
            HexNeighbours.add(pHexes[(x+1)/2][(y-1)/2]);
            HexNeighbours.add(pHexes[(x-1)/2][(y+1)/2]);
        }
    }

    @Override
    public void setIntersectionNeighbours(Intersection[][] pIntersections){
        int x = this.getCoordinates().getX();
        int y = this.getCoordinates().getY();
        if(x%2==0) { // EDGES ARE VERTICAL
            x=x/2;
            y=y/2;
            IntersectionNeighbours.add(pIntersections[x][y]);
            IntersectionNeighbours.add(pIntersections[x][y-1]);
        }
        else{ // EDGES ARE HORIZONTAL
            IntersectionNeighbours.add(pIntersections[(x+1)/2][y]);
            IntersectionNeighbours.add(pIntersections[(x+1)/2-1][y]);
        }
    }
}
