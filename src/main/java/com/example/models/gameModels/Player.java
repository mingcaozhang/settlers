package com.example.models.gameModels;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Created by G on 17/02/28.
 */
public class Player {
    private final Color aColor;
    private final String aUsername;
    private final int aIndex;
    private int aVPs;
    private int aGold;
    private int aRouteLength;
    private boolean hasMerchant;
    private boolean hasLongestTradeRoute;
    private boolean hasAqueduct;
    private boolean hasFortress;
    private boolean hasTradingHouse;
    private Queue<City> aCities;
    private Queue<Road> aRoads;
    private Queue<Ship> aShips;
    private Queue<Settlement> aSettlements;
    private Queue<BasicKnight> aBasicKnights;
    private Queue<StrongKnight> aStrongKnights;
    private Queue<MightyKnight> aMightyKnights;
    private Queue<Wall> aWalls;

    public Player(String pUsername, Color pColor){
        aUsername = pUsername;
        aColor = pColor;
        aIndex = aColor.ordinal();
        aVPs = 0;
        aGold = 0;
        aRouteLength = 0;
        hasMerchant = false;
        hasLongestTradeRoute = false;
        hasAqueduct = false;
        hasFortress = false;
        hasTradingHouse = false;
    }

    public enum Color{
        Blue, Orange, Red, White
    }

    public Color getColor(){
        return aColor;
    }

    public int getIndex(){
        return aIndex;
    }

    public void getRoads(Queue<Road> pRoads){
        aRoads = pRoads;
    }

    public void getCities(Queue<City> pCities){
        aCities = pCities;
    }

    public void getSettlements(Queue<Settlement> pSettlements){
        aSettlements = pSettlements;
    }

    public void getShips(Queue<Ship> pShips){
        aShips = pShips;
    }

    public void getWalls(Queue<Wall> pWalls){
        aWalls = pWalls;
    }

    public void getBasicKnights(Queue<BasicKnight> pBasicKnights){
        aBasicKnights = pBasicKnights;
    }



}
