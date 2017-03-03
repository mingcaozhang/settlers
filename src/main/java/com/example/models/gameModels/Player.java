package com.example.models.gameModels;
import java.util.List;
import java.util.ArrayList;

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
    private List<City> aCities;
    private List<Road> aRoads;
    private List<Ship> aShips;
    private List<Settlement> aSettlements;
    private List<BasicKnight> aBasicKnights;
    private List<StrongKnight> aStrongKnights;
    private List<MightyKnight> aMightyKnights;
    private List<Wall> aWalls;

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
}
