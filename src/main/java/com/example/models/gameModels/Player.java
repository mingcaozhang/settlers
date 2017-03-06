package com.example.models.gameModels;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
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
    private HashMap<ResourceCard.ResourceType, Queue<ResourceCard>> aResourceCards;
    private HashMap<CommodityCard.CommodityType, Queue<CommodityCard>> aCommodityCards;
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
        aResourceCards = new HashMap<ResourceCard.ResourceType, Queue<ResourceCard>>();
        aCommodityCards = new HashMap<CommodityCard.CommodityType, Queue<CommodityCard>>();
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

    public void addCard(ResourceCard pResourceCard){
        aResourceCards.get(pResourceCard.getType()).add(pResourceCard);
    }

    public void addCard(CommodityCard pCommodityCard){
        aCommodityCards.get(pCommodityCard.getType()).add(pCommodityCard);
    }

    public void addGold(){
        aGold += 2;
    }

    public void setRoads(Queue<Road> pRoads){
        aRoads = pRoads;
    }

    public void setCities(Queue<City> pCities){
        aCities = pCities;
    }

    public void setSettlements(Queue<Settlement> pSettlements){
        aSettlements = pSettlements;
    }

    public void setShips(Queue<Ship> pShips){
        aShips = pShips;
    }

    public void setWalls(Queue<Wall> pWalls){
        aWalls = pWalls;
    }

    public void setBasicKnights(Queue<BasicKnight> pBasicKnights){
        aBasicKnights = pBasicKnights;
    }



}
