package com.example.models.gameModels;
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
        for(Intersection i: lIntersections) {
            i.setEdgeNeighbours(aEdges);
            i.setHexNeighbours(aHexes);
            i.setIntersectionNeighbours(aIntersections);
        }
    }

    public Map<String, Player> getPlayers(){
        return getPlayers();
    }

    public void rollDice(int pYellow, int pRed, int pEvent){
        setPhase(GamePhase.TurnFirstPhase);
        setRedDice(DiceNumber.values()[pRed]);
        setYellowDice(DiceNumber.values()[pYellow]);
        int eventIndex = pEvent;
        switch (eventIndex){
            case 1:
                setEventDice(EventType.values()[0]);
            case 2:
                setEventDice(EventType.values()[0]);;
            case 3:
                setEventDice(EventType.values()[0]);
            case 4:
                setEventDice(EventType.values()[1]);
            case 5:
                setEventDice(EventType.values()[2]);
            case 6:
                setEventDice(EventType.values()[3]);
        }

        if (getTurnCounter() > 2) {
            checkBarbarian();
        }
        checkDice();
        setPhase(GamePhase.TurnDiceRolled);
    }

    private void checkBarbarian(){
        assert (getTurnCounter() > 2);
        if (getEventDice() == EventType.Barbarian){
            advanceBarbPosition();
        }
    }

    private void advanceBarbPosition(){
        assert getBarbarianPosition() > 0;
        updateBarbarianPosition();
        if (getBarbarianPosition()== 0){
            barbarianAttack();
            resetBarbPosition();
        }
    }

    private void barbarianAttack(){
        assert (getBarbarianPosition() == 0);
        calculateStrengths();
        if (getBarbarianStrength() > getArmyStrength()){
            defenseLoss();
        }
        defenseVictory();
    }

    private void calculateStrengths(){
        setBarbarianStrength(0);
        setArmyStrength(0);
        for (Map.Entry<String, Intersection> intersection : getIntersections().entrySet()){
            if (intersection.getValue().getOccupancyFlag()) {
                    if (intersection.getValue().getOccupant().getClass() == City.class) {
                        setBarbarianStrength(getBarbarianStrength() + 1);
                    }
                    else if (intersection.getValue().getOccupant().getClass().getSuperclass() == Knight.class){
                        if (((Knight)intersection.getValue().getOccupant()).getState()){
                            setArmyStrength(getArmyStrength() + ((Knight) intersection.getValue().getOccupant()).getStrength());
                        }
                    }
            }
        }
    }

    private void resetBarbPosition(){
        resetBarbarianPosition();
    }


    private void defenseVictory(){
        //TODO
    }

    private void defenseLoss(){
        //TODO
    }


    private void checkDice() {
        int numberRolled = getRedDice().add(getYellowDice());
        ArrayList<LandHex> tempLandHexes = getLandHexes().get(numberRolled);
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

    public void payout(Player pOwner, TerrainType pTerrainType, boolean isCity){
        if (pTerrainType == TerrainType.GoldMine){
            if (removeGold()) {
                pOwner.addGold();
            }
        }
        else {
            assert(!getResourceCards().get(pTerrainType.giveResource()).isEmpty());
            pOwner.addCard(getResourceCards().get(pTerrainType.giveResource()).remove());
        }

        if (isCity) {
            if (pTerrainType == TerrainType.Hills || pTerrainType == TerrainType.Fields){
                assert(!getResourceCards().get(pTerrainType.giveResource()).isEmpty());
                pOwner.addCard(getResourceCards().get(pTerrainType.giveResource()).remove());
            }
            else if (pTerrainType == TerrainType.GoldMine){
                if (removeGold()){
                    pOwner.addGold();
                }
            }
            else{
                assert(!getCommodityCards().get(pTerrainType.giveCommodity()).isEmpty());
                pOwner.addCard(getCommodityCards().get(pTerrainType.giveCommodity()).remove());
            }
        }
    }

    private boolean removeGold(){
        if (getGoldBank() >= 2) {
            setGoldBank(getGoldBank() - 2);
            return true;
        }
        return false;
    }

    public void placeSettlement(Player pPlayer, Intersection pIntersection){
        if (pPlayer != getCurrentPlayer()){
            //do nothing
        }
        else {
            assert (!pIntersection.getOccupancyFlag());
            assert (pPlayer.canGetSettlement());
            boolean eligible = checkIntersectionEligibility(pIntersection);
            assert (eligible);

            Settlement settlement = pPlayer.giveSettlement();
            pIntersection.setOccupant(settlement);
            if (getPhase() == Game.GamePhase.SetupRoundOne) {
                Queue<Hex> neighbours = pIntersection.getHexNeighbours();
                for (Hex hex : neighbours) {
                    payout(pPlayer, hex.getTerrainType(), false);
                }
            }
        }
    }

    public  void placeCity(Player pPlayer, Intersection pIntersection){
        if (pPlayer != getCurrentPlayer()){
            //do nothing
        }
        else{
            assert(!pIntersection.getOccupancyFlag());
            assert(pPlayer.canGetCity());
            boolean eligible = checkIntersectionEligibility(pIntersection);
            assert (eligible);

            City city = pPlayer.giveCity();
            pIntersection.setOccupant(city);
            if (getPhase() == Game.GamePhase.SetupRoundTwo) {
                Queue<Hex> neighbours = pIntersection.getHexNeighbours();
                for (Hex hex : neighbours) {
                    payout(pPlayer, hex.getTerrainType(), true);
                }
            }
        }
    }

    public void placeRoad(Player pPlayer, Edge pEdge){
        if (pPlayer != getCurrentPlayer()){
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
        if (getPhase() != Game.GamePhase.SetupRoundOne && getPhase()!= Game.GamePhase.SetupRoundTwo){
            eligible = checkNonSetupEligibility(pIntersection);
        }
        return eligible;
    }

    private boolean checkEdgeEligibility(Edge pEdge){
        boolean eligible = false;
        Queue<Intersection> neighbourIntersections = pEdge.getIntersectionNeighbours();
        for (Intersection intersection: neighbourIntersections){    //Iterate through all neighbours to see if they are occupied by the current player
            if (intersection.getOccupant().getOwner() != getCurrentPlayer()){
                continue;
            }
            else{
                eligible = true;
                break;
            }
        }
        if (getPhase()!= Game.GamePhase.SetupRoundOne && getPhase()!= Game.GamePhase.SetupRoundTwo){
            eligible = checkNonSetupEligibility(pEdge);
        }
        return eligible;
    }

    private boolean checkNonSetupEligibility(Geometry pGeometry){
        Queue<Edge> neighbourEdges = pGeometry.getEdgeNeighbours();
        boolean eligible = false;
        for (Edge edge : neighbourEdges){   //if any of the neighbouring edges have a road belonging to the player, it is eligible
            if (edge.getOccupant().getOwner() != getCurrentPlayer()){
                continue;
            }
            else{
                eligible = true;
                break;
            }
        }
        return eligible;
    }

    public void endTurn(){
        setPhase(Game.GamePhase.TurnSecondPhase);
        nextPlayer();
    }

    private void nextPlayer(){
        updateQueue();
        updateTurnCounter();
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
