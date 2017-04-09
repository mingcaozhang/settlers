package com.example.models.gameModels;
import javax.persistence.*;
import java.util.*;


@Entity
public class Game {

    public Game(){aPlayers = null; aVPsToWin = 10;}

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

    @Override
    public String toString() {
        return "Game{" +
                "gameid=" + gameid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;

        Game game = (Game) o;

        if (aVPsToWin != game.aVPsToWin) return false;
        if (aBarbarianPosition != game.aBarbarianPosition) return false;
        if (aGoldBank != game.aGoldBank) return false;
        if (aArmyStrength != game.aArmyStrength) return false;
        if (aBarbarianStrength != game.aBarbarianStrength) return false;
        if (aTurnCounter != game.aTurnCounter) return false;
        if (gameid != null ? !gameid.equals(game.gameid) : game.gameid != null) return false;
        if (aPlayers != null ? !aPlayers.equals(game.aPlayers) : game.aPlayers != null) return false;
        if (aBoard != null ? !aBoard.equals(game.aBoard) : game.aBoard != null) return false;
        if (aResourceCards != null ? !aResourceCards.equals(game.aResourceCards) : game.aResourceCards != null)
            return false;
        if (aCommodityCards != null ? !aCommodityCards.equals(game.aCommodityCards) : game.aCommodityCards != null)
            return false;
        if (aPhase != game.aPhase) return false;
        if (aRedDice != game.aRedDice) return false;
        if (aYellowDice != game.aYellowDice) return false;
        return aEventDice == game.aEventDice;
    }

    @Override
    public int hashCode() {
        int result = gameid.hashCode();
        return result;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long gameid;

    @OneToMany(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private final List<Player> aPlayers;

    private final int aVPsToWin;

    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private Board aBoard;

    @ElementCollection
    private Map<StealableCard.Resource, Integer> aResourceCards = new HashMap<>();
    @ElementCollection
    private Map<StealableCard.Commodity, Integer> aCommodityCards = new HashMap<>();

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
        this.gameid = gameid;
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

    /*public Game(){
        aVPsToWin =10;
        aBarbarianPosition = 6;
        aGoldBank = 50;
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
        */
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

    public void setBoard(Board pBoard) {aBoard = pBoard; }

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

    public Map<StealableCard.Resource, Integer> getResourceCards(){
        return aResourceCards;
    }

    public Map<StealableCard.Commodity, Integer> getCommodityCards(){
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

    public long getId(){return gameid;}
}
