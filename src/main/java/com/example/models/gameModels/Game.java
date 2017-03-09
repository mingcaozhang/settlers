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
    private int aTurnCounter;

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
        aTurnCounter = 1;
        aResourceCards = new HashMap<ResourceCard.ResourceType, Queue<ResourceCard>>();
        aCommodityCards = new HashMap<CommodityCard.CommodityType, Queue<CommodityCard>>();
        aResourceCards = ResourceCard.getResources();
        aCommodityCards = CommodityCard.getCommodities();
        aPlayerQueue = new LinkedList<Player>();
        for (Player player : aPlayers.values()){
            aPlayerQueue.add(player);
        }
        currentPlayer = aPlayerQueue.remove();
    }

    public void rollDice(){
        aPhase = GamePhase.TurnFirstPhase;
        aRedDice = DiceNumber.values()[(int)(Math.random() * DiceNumber.values().length)];
        aYellowDice = DiceNumber.values()[(int)(Math.random() * DiceNumber.values().length)];
        int eventIndex = (int)(Math.random() * 6);
        switch (eventIndex){
            case 1:
                aEventDice = EventType.values()[0];
            case 2:
                aEventDice = EventType.values()[0];
            case 3:
                aEventDice = EventType.values()[0];
            case 4:
                aEventDice = EventType.values()[1];
            case 5:
                aEventDice = EventType.values()[2];
            case 6:
                aEventDice = EventType.values()[3];
        }

        if (aTurnCounter > 2) {
            checkBarbarian();
        }
        checkDice();
        aPhase = GamePhase.TurnDiceRolled;
        nextPlayer();
    }

    private void nextPlayer(){
        aPlayerQueue.add(currentPlayer);
        currentPlayer = aPlayerQueue.remove();
        aTurnCounter++;
    }

    private void checkBarbarian(){
        assert (aTurnCounter > 2);
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
        calculateStrengths();
        if (aBarbarianStrength > aArmyStrength){
            defenseLoss();
        }
        defenseVictory();
    }

    private void calculateStrengths(){
        aBarbarianStrength = 0;
        aArmyStrength = 0;
        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                if (aIntersections[i][j].getOccupancyFlag()) {
                    if (aIntersections[i][j].getOccupant().getClass() == City.class) {
                        aBarbarianStrength += 1;
                    }
                    else if (aIntersections[i][j].getOccupant().getClass().getSuperclass() == Knight.class){
                        if (((Knight)aIntersections[i][j].getOccupant()).getState()){
                            aArmyStrength += ((Knight)aIntersections[i][j].getOccupant()).getStrength();
                        }
                    }
                }
            }
        }
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
                    boolean isCity = checkIsCity(intersection);
                    payout(owner, hex.getTerrainType(), isCity);
                }
            }
        }
    }

    private Player getPayee(Intersection pIntersection){
        return pIntersection.getOccupant().getOwner();
    }

    private boolean checkIsCity(Intersection pIntersection){
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
            boolean eligible = checkIntersectionEligibility(pIntersection);
            assert (eligible);

            Settlement settlement = pPlayer.giveSettlement();
            pIntersection.setOccupant(settlement);
            if (aPhase == GamePhase.SetupRoundOne) {
                Queue<Hex> neighbours = pIntersection.getHexNeighbours();
                for (Hex hex : neighbours) {
                    payout(pPlayer, hex.getTerrainType(), false);
                }
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
            boolean eligible = checkIntersectionEligibility(pIntersection);
            assert (eligible);

            City city = pPlayer.giveCity();
            pIntersection.setOccupant(city);
            if (aPhase == GamePhase.SetupRoundTwo) {
                Queue<Hex> neighbours = pIntersection.getHexNeighbours();
                for (Hex hex : neighbours) {
                    payout(pPlayer, hex.getTerrainType(), true);
                }
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
            assert(checkEdgeEligibility(pEdge));

            Road road = pPlayer.giveRoad();
            pEdge.setOccupant(road);
        }
    }

    private boolean checkIntersectionEligibility(Intersection pIntersection){
        boolean eligible = true;
        Queue<Intersection> neighbourIntersections = pIntersection.getIntersectionNeighbours();
        for (Intersection intersection: neighbourIntersections){    //Iterate through all neighbours to see if they are occupied
            if (intersection.getOccupancyFlag()){
                eligible = false;
                break;
            }
        }
        if (aPhase != GamePhase.SetupRoundOne && aPhase != GamePhase.SetupRoundTwo){
            eligible = checkNonSetupEligibility(pIntersection);
        }
        return eligible;
    }

    private boolean checkEdgeEligibility(Edge pEdge){
        boolean eligible = false;
        Queue<Intersection> neighbourIntersections = pEdge.getIntersectionNeighbours();
        for (Intersection intersection: neighbourIntersections){    //Iterate through all neighbours to see if they are occupied by the current player
            if (intersection.getOccupant().getOwner() != currentPlayer){
                continue;
            }
            else{
                eligible = true;
                break;
            }
        }
        if (aPhase != GamePhase.SetupRoundOne && aPhase != GamePhase.SetupRoundTwo){
            eligible = checkNonSetupEligibility(pEdge);
        }
        return eligible;
    }

    private boolean checkNonSetupEligibility(Geometry pGeometry){
        Queue<Edge> neighbourEdges = pGeometry.getEdgeNeighbours();
        boolean eligible = false;
        for (Edge edge : neighbourEdges){   //if any of the neighbouring edges have a road belonging to the player, it is eligible
            if (edge.getOccupant().getOwner() != currentPlayer){
                continue;
            }
            else{
                eligible = true;
                break;
            }
        }
        return eligible;
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
