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
    private boolean aMerchant;
    private boolean aLongestTradeRoute;
    private boolean aAqueduct;
    private boolean aFortress;
    private boolean aTradingHouse;
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
        aMerchant = false;
        aLongestTradeRoute = false;
        aAqueduct = false;
        aFortress = false;
        aTradingHouse = false;
    }

    public enum Color{
        Blue, Orange, Red, White
    }

    public Color getColor(){
        return aColor;
    }

    public String getUsername(){ return aUsername; }

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

    public boolean hasMerchant(){return aMerchant;}

    public boolean hasLongestTradeRoute(){return aLongestTradeRoute;}

    public boolean hasAqueduct(){return aAqueduct;}

    public boolean hasFortress(){return aFortress;}

    public boolean hasTradingHouse(){return aTradingHouse;}

    public boolean canGetWall(){
        if(aWalls.isEmpty())
            return false;
        else
            return true;
    }

    public boolean canGetRoad(){
        if(aRoads.isEmpty())
            return false;
        else
            return true;
    }

    public boolean canGetCity(){
        if(aCities.isEmpty())
            return false;
        else
            return true;
    }

    public boolean canGetShip(){
        if(aShips.isEmpty())
            return false;
        else
            return true;
    }

    public boolean canGetSettlement(){
        if(aSettlements.isEmpty())
            return false;
        else
            return true;
    }

    public boolean canGetBasicKnight(){
        if(aBasicKnights.isEmpty())
            return false;
        else
            return true;
    }

    public boolean canGetStrongKnight(){
        if(aStrongKnights.isEmpty())
            return false;
        else
            return true;
    }

    public boolean canGetMightyKnight(){
        if(aMightyKnights.isEmpty())
            return false;
        else
            return true;
    }

    public Road giveRoad(){
        return aRoads.remove();
    }

    public Settlement giveSettlement(){
        return aSettlements.remove();
    }

    public City giveCity(){
        return aCities.remove();
    }

    public Ship giveShip(){
        return aShips.remove();
    }

    public BasicKnight giveBasicKnight(){
        return aBasicKnights.remove();
    }

    public StrongKnight giveStrongKnight(){
        return aStrongKnights.remove();
    }

    public MightyKnight giveMightyKnight(){
        return aMightyKnights.remove();
    }
    
}