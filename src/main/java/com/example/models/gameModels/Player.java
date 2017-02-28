package com.example.models.gameModels;

/**
 * Created by G on 17/02/28.
 */
public class Player {
    private final Color aColor;
    private final String aUsername;
    private int aVPs;
    private int aGold;
    private int aRouteLength;
    private boolean hasMerchant;
    private boolean hasLongestTradeRoute;
    private boolean hasAqueduct;
    private boolean hasFortress;
    private boolean hasTradingHouse;

    public Player(String pUsername, Color pColor){
        aUsername = pUsername;
        aColor = pColor;
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
}
