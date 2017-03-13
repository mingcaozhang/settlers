package com.example.models.gameModels;
import java.lang.reflect.Array;
import java.time.Clock;
import java.util.*;

/**
 * Created by G on 17/03/03.
 */
public class Game {
    private final List<Player> aPlayers = new ArrayList<Player>(); // TODO
    private final int aVPsToWin;

    private Map<String,Hex> aHexes = new HashMap<String,Hex>();
    private Map<String,Edge> aEdges = new HashMap<String,Edge>();
    private Map<String,Intersection> aIntersections = new HashMap<String,Intersection>();

    public Queue<Hex> lHexes = new LinkedList<Hex>();
    public Queue<Edge> lEdges = new LinkedList<Edge>();
    public Queue<Intersection> lIntersections = new LinkedList<Intersection>();

    private static HashMap<Integer, ArrayList<LandHex>> aLandHexes; //TODO maybe
    private HashMap<ResourceCard.ResourceType, Queue<ResourceCard>> aResourceCards;
    private HashMap<CommodityCard.CommodityType, Queue<CommodityCard>> aCommodityCards;
    private GamePhase aPhase;
    private int aBarbarianPosition;
    private int aGoldBank;
    private int aArmyStrength;
    private int aBarbarianStrength;
    private DiceNumber aRedDice;
    private DiceNumber aYellowDice;
    private EventType aEventDice;
    private Player aCurrentPlayer;
    private Queue<Player> aPlayerQueue;
    private int aTurnCounter;

    public Game(int pVPsToWin, List<String> pPlayers,List<String> pColors){
        aVPsToWin = pVPsToWin;
        aBarbarianPosition = 6;
        aGoldBank = 50;
        for(int i=0;i<pPlayers.size();i++)
        {
            Player aPlayer = new Player(pPlayers.get(i),pColors.get(i),i);
            aPlayers.add(i,aPlayer);
        }
        aPhase = GamePhase.SetupRoundOne;
        aArmyStrength = 0;
        aBarbarianStrength = 0;
        aTurnCounter = 1;
        aResourceCards = new HashMap<ResourceCard.ResourceType, Queue<ResourceCard>>();
        aCommodityCards = new HashMap<CommodityCard.CommodityType, Queue<CommodityCard>>();
        aResourceCards = ResourceCard.getResources();
        aCommodityCards = CommodityCard.getCommodities();
//        aPlayerQueue = new LinkedList<Player>();
        System.out.println("BILLY BOBBY");

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

    public void updateQueue(){
        aPlayerQueue.add(aCurrentPlayer);
        aCurrentPlayer = aPlayerQueue.remove();
    }

    public void updateTurnCounter(){
        aTurnCounter++;
    }

    public List<Player> getPlayers(){
        return aPlayers;
    }

    public int getTurnCounter(){
        return aTurnCounter;
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

    public Map<String, Intersection> getIntersections(){
        return aIntersections;
    }

    public Map<String, Hex> getHexes(){
        return aHexes;
    }

    public Map<String, Edge> getEdges(){
        return aEdges;
    }

    public HashMap<Integer, ArrayList<LandHex>> getLandHexes(){
        return aLandHexes;
    }

    public HashMap<ResourceCard.ResourceType, Queue<ResourceCard>> getResourceCards(){
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

    public Player getCurrentPlayer(){
        return aCurrentPlayer;
    }

    public void setAllNeighbours(){
    for(Hex h: lHexes)
    {
        h.setEdgeNeighbours(aEdges);
        h.setHexNeighbours(aHexes);
        h.setIntersectionNeighbours(aIntersections);
    }
    for(Edge e: lEdges)
    {
        e.setEdgeNeighbours(aEdges);
        e.setHexNeighbours(aHexes);
        e.setIntersectionNeighbours(aIntersections);
    }
    for(Intersection i: lIntersections)
    {
        i.setEdgeNeighbours(aEdges);
        i.setHexNeighbours(aHexes);
        i.setIntersectionNeighbours(aIntersections);
    }
    }

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
}
