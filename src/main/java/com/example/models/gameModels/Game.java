package com.example.models.gameModels;
import java.time.Clock;
import java.util.*;

/**
 * Created by G on 17/03/03.
 */
public class Game {
    private final int aID;
    private final Map<String, Player> aPlayers;
    private final int aVPsToWin;
    private final Hex[][] aHexes;
    private final Edge[][] aEdges;
    private final Intersection[][] aIntersections;
    private final HashMap<Integer, ArrayList<LandHex>> aLandHexes;
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
    private Player currentPlayer;
    private Queue<Player> aPlayerQueue;

    public Game(int pVPsToWin, Map<String, Player> pPlayers, int pID, Hex[][] pHexes, Edge[][] pEdges, Intersection[][] pIntersections, HashMap<Integer,ArrayList<LandHex>> pLandHexes ){
        aID = pID;
        aVPsToWin = pVPsToWin;
        aHexes = pHexes;
        aEdges = pEdges;
        aIntersections = pIntersections;
        aLandHexes = pLandHexes;
        aBarbarianPosition = 6;
        aGoldBank = 50;
        aPlayers = pPlayers;
        aPhase = GamePhase.SetupRoundOne;
        aArmyStrength = 0;
        aBarbarianStrength = 0;
        aResourceCards = new HashMap<ResourceCard.ResourceType, Queue<ResourceCard>>();
        aCommodityCards = new HashMap<CommodityCard.CommodityType, Queue<CommodityCard>>();
        aResourceCards = ResourceCard.getResources();
        aCommodityCards = CommodityCard.getCommodities();
        for (Player player : aPlayers.values()){
            aPlayerQueue.add(player);
        }
        currentPlayer = aPlayerQueue.peek();
    }

    public void rollDice(){
        DiceNumber pRedDice = DiceNumber.values()[(int)(Math.random() * DiceNumber.values().length)];
        DiceNumber pYellowDice = DiceNumber.values()[(int)(Math.random() * DiceNumber.values().length)];
        EventType pEventDice = EventType.values()[(int)(Math.random() * EventType.values().length)];
        aRedDice = pRedDice;
        aYellowDice = pYellowDice;
        aEventDice = pEventDice;
        checkBarbarian();
        checkDice();
    }

    private void checkBarbarian(){
        if (aEventDice == EventType.Barbarian){
            advanceBarbPosition();
        }
    }

    private void advanceBarbPosition(){
        assert aBarbarianPosition > 0;
        aBarbarianPosition--;
        if (aBarbarianPosition == 0){
            barbarianAttack();
            resetBarbPosition();
        }
    }


    private void barbarianAttack(){
        assert (aBarbarianPosition == 0);
        if (aBarbarianStrength > aArmyStrength){
            defenseLoss();
        }
        defenseVictory();
    }

    private void defenseVictory(){
        //TODO
    }

    private void defenseLoss(){
        //TODO
    }

    private void resetBarbPosition(){
        aBarbarianPosition = 6;
    }

    private void checkDice() {
        int numberRolled = aRedDice.add(aYellowDice);
        ArrayList<LandHex> tempLandHexes = aLandHexes.get(numberRolled);
        for (LandHex hex : tempLandHexes) {
            Queue<Intersection> tempIntersections = hex.getIntersectionNeighbours();
            for (Intersection intersection : tempIntersections) {
                if (intersection.getOccupancyFlag()) {
                    Player owner = getPayee(intersection);
                    boolean isCity = checkCityFlag(intersection);
                    payout(owner, hex.getTerrainType(), isCity);
                }
            }
        }
    }

    private Player getPayee(Intersection pIntersection){
        return pIntersection.getOccupant().getOwner();
    }

    private boolean checkCityFlag(Intersection pIntersection){
        return (pIntersection.getOccupant().getClass() == City.class);
    }

    private void payout(Player pOwner, TerrainType pTerrainType, boolean isCity){
        if (pTerrainType == TerrainType.GoldMine){
            if (removeGold()) {
                pOwner.addGold();
            }
        }
        else {
            assert(!aResourceCards.get(pTerrainType.giveResource()).isEmpty());
            pOwner.addCard(aResourceCards.get(pTerrainType.giveResource()).remove());
        }

        if (isCity) {
            if (pTerrainType == TerrainType.Hills || pTerrainType == TerrainType.Fields){
                assert(!aResourceCards.get(pTerrainType.giveResource()).isEmpty());
                pOwner.addCard(aResourceCards.get(pTerrainType.giveResource()).remove());
            }
            else if (pTerrainType == TerrainType.GoldMine){
                if (removeGold()){
                    pOwner.addGold();
                }
            }
            else{
                assert(!aCommodityCards.get(pTerrainType.giveCommodity()).isEmpty());
                pOwner.addCard(aCommodityCards.get(pTerrainType.giveCommodity()).remove());
            }
        }
    }

    private boolean removeGold(){
        if (aGoldBank >= 2) {
            aGoldBank -= 2;
            return true;
        }
        return false;
    }

    public void placeSettlement(Player pPlayer, Intersection pIntersection){
        if (pPlayer != currentPlayer){
            //do nothing
        }
        else {
            assert (!pIntersection.getOccupancyFlag());
            assert (pPlayer.canGetSettlement());
            Settlement settlement = pPlayer.giveSettlement();
            pIntersection.setOccupant(settlement);

            Queue<Hex> neighbours = pIntersection.getHexNeighbours();
            for (Hex hex : neighbours){
                payout(pPlayer, hex.getTerrainType(), false);
            }
        }
    }

    public void placeCity(Player pPlayer, Intersection pIntersection){
        if (pPlayer != currentPlayer){
            //do nothing
        }
        else{
            assert(!pIntersection.getOccupancyFlag());
            assert(pPlayer.canGetCity());
            City city = pPlayer.giveCity();
            pIntersection.setOccupant(city);

            Queue<Hex> neighbours = pIntersection.getHexNeighbours();
            for (Hex hex : neighbours){
                payout(pPlayer, hex.getTerrainType(), true);
            }
        }
    }

    public void placeRoad(Player pPlayer, Edge pEdge){
        if (pPlayer != currentPlayer){
            //do nothing
        }
        else{
            assert(!pEdge.getOccupancyFlag());
            assert(pPlayer.canGetRoad());
            Road road = pPlayer.giveRoad();
            pEdge.setOccupant(road);
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
