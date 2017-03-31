package com.example.models.gameModels;
import java.util.*;

/**
 * Created by G on 17/03/03.
 */
public class Game {
  
    public enum GamePhase{
        SetupRoundOne,SetupRoundTwo, TurnFirstPhase, TurnDiceRolled, TurnSecondPhase, Completed
    }

    public enum DiceNumber{
        One, Two, Three, Four, Five, Six;
        public int add(DiceNumber pNumber){
            return pNumber.ordinal() + ordinal() + 2;
        }
        public int toInt(){
            return ordinal() + 1;
        }
    }

    public enum EventType{
        Barbarian, Trade, Politics, Science
    }

    private final List<Player> aPlayers;
    private final int aVPsToWin;
    private Board aBoard;
    private HashMap<StealableCard.Resource, Integer> aResourceCards;
    private HashMap<StealableCard.Commodity, Integer> aCommodityCards;

    private GamePhase aPhase;
    private int aBarbarianPosition;
    private int aGoldBank;
    private int aArmyStrength;
    private int aBarbarianStrength;
    private DiceNumber aRedDice;
    private DiceNumber aYellowDice;
    private EventType aEventDice;
    private int aTurnCounter;

    public Game(int pVPsToWin, List<Player> pPlayers, Board pBoard){
        aBoard = pBoard;

        aVPsToWin = pVPsToWin;
        aBarbarianPosition = 6;
        aGoldBank = 50;
        aPlayers = pPlayers;

        aPhase = GamePhase.SetupRoundOne;
        aArmyStrength = 0;
        aBarbarianStrength = 0;
        aTurnCounter = 1;
        aResourceCards = new HashMap<>();
        aCommodityCards = new HashMap<>();
        for (StealableCard.Resource resource : StealableCard.Resource.values()){
            aResourceCards.put(resource, StealableCard.Resource.maxResources());
        }
        for (StealableCard.Commodity commodity : StealableCard.Commodity.values()){
            aCommodityCards.put(commodity, StealableCard.Commodity.maxCommodities());
        }

    }

    public void setPhase(GamePhase pPhase){
        aPhase = pPhase;
    }

    public void setRedDice(DiceNumber pRedDice){
        aRedDice = pRedDice;
    }

    public void setYellowDice(DiceNumber pYellowDice){
        aYellowDice = pYellowDice;
    }

    public void setEventDice(EventType pEventDice){
        aEventDice = pEventDice;
    }

    public void setBarbarianStrength(int pStrength){
        aBarbarianStrength = pStrength;
    }

    public void setArmyStrength(int pStrength){
        aArmyStrength = pStrength;
    }

    public void setGoldBank(int pGoldBank){
        aGoldBank = pGoldBank;
    }

    public void updateBarbarianPosition(){
        aBarbarianPosition--;
    }

    public void resetBarbarianPosition(){
        aBarbarianPosition = 6;
    }

    public void updateTurn(){
        aTurnCounter++;
    }

    public Board getBoard(){
        return aBoard;

    }

    public List<Player> getPlayers(){
        return aPlayers;
    }

    public EventType getEventDice(){
        return aEventDice;
    }

    public DiceNumber getRedDice(){
        return aRedDice;
    }

    public DiceNumber getYellowDice(){
        return aYellowDice;
    }

    public int getBarbarianPosition(){
        return aBarbarianPosition;
    }

    public int getBarbarianStrength(){
        return aBarbarianStrength;
    }

    public int getArmyStrength(){
        return aArmyStrength;
    }

    public HashMap<StealableCard.Resource, Integer> getResourceCards(){

        return aResourceCards;
    }

    public HashMap<CommodityCard.CommodityType, Queue<CommodityCard>> getCommodityCards(){
        return aCommodityCards;
    }

    public int getGoldBank(){
        return aGoldBank;
    }

    public GamePhase getPhase(){
        return aPhase;
    }

    public int getTurnCounter(){
        return aTurnCounter;

    }
}
