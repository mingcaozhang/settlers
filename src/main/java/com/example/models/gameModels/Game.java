package com.example.models.gameModels;
import java.util.*;

/**
 * Created by G on 17/03/03.
 */
public class Game {
    private final List<Player> aPlayers = new ArrayList<Player>();
    private final int aVPsToWin;

    private Map<String,Hex> aHexes = new HashMap<String,Hex>();
    private Map<String,Edge> aEdges = new HashMap<String,Edge>();
    private Map<String,Intersection> aIntersections = new HashMap<String,Intersection>();

    public List<Hex> lHexes = new ArrayList<Hex>();
    public List<Edge> lEdges = new ArrayList<Edge>();
    public List<Intersection> lIntersections = new ArrayList<Intersection>();

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
//      aPlayerQueue = new LinkedList<Player>();
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


    public Player 
      (){
        return aCurrentPlayer;
    }

    private void waitToSet(){
        try {
            Thread.sleep(10000);
        } catch(InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }


    public void setAllNeighbours(){
       // System.out.println("hello YOUTUBE COMMUNITY");
        for(int i=0;i< lHexes.size();i++)
        {
            lHexes.get(i).setEdgeNeighbours(aEdges);
          //  waitToSet();
            lHexes.get(i).setHexNeighbours(aHexes);
           // waitToSet();
            lHexes.get(i).setIntersectionNeighbours(aIntersections);
           // waitToSet();
          //  System.out.println(lHexes.get(i).getIntersectionNeighbours().peek().getId());
          //  System.out.println(lHexes.get(i).getId());

            //   System.out.println("hello YOUTUBE COMMUNITY");
        }
        for(int i=0;i< lEdges.size();i++)
        {
           // waitToSet();
            lEdges.get(i).setEdgeNeighbours(aEdges);
           // waitToSet();
            lEdges.get(i).setHexNeighbours(aHexes);
           // waitToSet();
            lEdges.get(i).setIntersectionNeighbours(aIntersections);
           // waitToSet();
         //   System.out.println("its my birthday today");

        }
        for(int i=0;i< lIntersections.size();i++) {
            lIntersections.get(i).setEdgeNeighbours(aEdges);
           // waitToSet();
            lIntersections.get(i).setHexNeighbours(aHexes);
           // waitToSet();
            lIntersections.get(i).setIntersectionNeighbours(aIntersections);
          //  System.out.println("And i am just so happy");

        }
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

//        if (getTurnCounter() > 2) {
//            checkBarbarian();
//        }
        checkDice();
//        setPhase(GamePhase.TurnDiceRolled);
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

    private void payout(Player pOwner, TerrainType pTerrainType, boolean isCity){
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

    public void setupPayout(){
        for (Map.Entry<String,Intersection> intersection : aIntersections.entrySet()){
            if (intersection.getValue().getOccupant().getClass() == City.class){
                Player owner = intersection.getValue().getOccupant().getOwner();
                Intersection cityLocation = intersection.getValue();
                for(Hex hex : cityLocation.getHexNeighbours()){
                    TerrainType pTerrainType = hex.getTerrainType();
                    owner.addCard(getResourceCards().get(pTerrainType.giveResource()).remove());
                }
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

    private void paySettlement(Player pPlayer){
        aResourceCards.get(ResourceCard.ResourceType.Sheep).add(pPlayer.removeResource(ResourceCard.ResourceType.Sheep));
        aResourceCards.get(ResourceCard.ResourceType.Wheat).add(pPlayer.removeResource(ResourceCard.ResourceType.Wheat));
        aResourceCards.get(ResourceCard.ResourceType.Brick).add(pPlayer.removeResource(ResourceCard.ResourceType.Brick));
        aResourceCards.get(ResourceCard.ResourceType.Wood).add(pPlayer.removeResource(ResourceCard.ResourceType.Wood));
    }

    private void payCity(Player pPlayer){;
        aResourceCards.get(ResourceCard.ResourceType.Wheat).add(pPlayer.removeResource(ResourceCard.ResourceType.Wheat));
        aResourceCards.get(ResourceCard.ResourceType.Wheat).add(pPlayer.removeResource(ResourceCard.ResourceType.Wheat));
        aResourceCards.get(ResourceCard.ResourceType.Ore).add(pPlayer.removeResource(ResourceCard.ResourceType.Ore));
        aResourceCards.get(ResourceCard.ResourceType.Ore).add(pPlayer.removeResource(ResourceCard.ResourceType.Ore));
        aResourceCards.get(ResourceCard.ResourceType.Ore).add(pPlayer.removeResource(ResourceCard.ResourceType.Ore));
    }

    private void payRoad(Player pPlayer){
        aResourceCards.get(ResourceCard.ResourceType.Brick).add(pPlayer.removeResource(ResourceCard.ResourceType.Brick));
        aResourceCards.get(ResourceCard.ResourceType.Wood).add(pPlayer.removeResource(ResourceCard.ResourceType.Wood));
    }

    public void placeSettlement(Player pPlayer, Intersection pIntersection){
        assert(!pIntersection.getOccupancyFlag());
        assert(pPlayer.canGetSettlement());
        assert(checkIntersectionEligibility(pIntersection, pPlayer));
        assert(checkBuySettlement(pPlayer));

        paySettlement(pPlayer);
        Settlement settlement = pPlayer.giveSettlement();
        pIntersection.setOccupant(settlement);
    }

    public void placeCity(Player pPlayer, Intersection pIntersection){
        assert(pIntersection.getOccupancyFlag());
        assert(pIntersection.getOccupant().getClass() == Settlement.class);
        assert(pIntersection.getOccupant().getOwner() == pPlayer);
        assert(pPlayer.canGetCity());
        assert(checkBuyCity(pPlayer));

        payCity(pPlayer);
        City city = pPlayer.giveCity();
        pPlayer.addSettlement((Settlement)pIntersection.getOccupant());
        pIntersection.setOccupant(city);
    }

    public void placeRoad(Player pPlayer, Edge pEdge){
        assert(!pEdge.getOccupancyFlag());
        assert(pPlayer.canGetRoad());
        assert(checkEdgeEligibility(pEdge, pPlayer));
        assert(checkBuyRoad(pPlayer));

        payRoad(pPlayer);
        Road road = pPlayer.giveRoad();
        pEdge.setOccupant(road);
    }

    private boolean checkIntersectionEligibility(Intersection pIntersection, Player pPlayer){
        boolean eligible1 = true;
        Queue<Intersection> neighbourIntersections = pIntersection.getIntersectionNeighbours();
        for (Intersection intersection: neighbourIntersections){    //Iterate through all neighbours to see if they are occupied
            if (intersection.getOccupancyFlag()){
                eligible1 = false;
                break;
            }
        }

        Queue<Edge> neighbourEdges = pIntersection.getEdgeNeighbours();
        boolean eligible2 = false;
        for (Edge edge: neighbourEdges){
            if (edge.getOccupancyFlag()){
                if (edge.getOccupant().getOwner() == pPlayer) {
                    eligible2 = true;
                    break;
                }
            }
        }
        return (eligible1 && eligible2);
    }

    private boolean checkEdgeEligibility(Edge pEdge, Player pPlayer){
        Queue<Edge> neighbourEdges = pEdge.getEdgeNeighbours();
        boolean eligible = false;
        for (Edge edge: neighbourEdges) {
            if (edge.getOccupancyFlag()) {
                if (edge.getOccupant().getOwner() == pPlayer) {
                    eligible = true;
                    break;
                }
            }
        }
        return eligible;
    }

    private boolean checkBuySettlement(Player pPlayer) {
        if (pPlayer.getResourceCards().get(ResourceCard.ResourceType.Sheep).isEmpty()){
            return false;
        }
        else if (pPlayer.getResourceCards().get(ResourceCard.ResourceType.Ore).isEmpty()){
            return false;
        }
        else if (pPlayer.getResourceCards().get(ResourceCard.ResourceType.Wood).isEmpty()){
            return false;
        }
        else if (pPlayer.getResourceCards().get(ResourceCard.ResourceType.Brick).isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkBuyCity(Player pPlayer){
        if (pPlayer.getResourceCards().get(ResourceCard.ResourceType.Ore).size() < 3){
            return false;
        }
        else if (pPlayer.getResourceCards().get(ResourceCard.ResourceType.Wheat).size() < 2){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkBuyRoad(Player pPlayer){
       if (pPlayer.getResourceCards().get(ResourceCard.ResourceType.Wood).isEmpty()){
           return false;
       }
       else if (pPlayer.getResourceCards().get(ResourceCard.ResourceType.Brick).isEmpty()){
           return false;
       }
       else{
           return true;
       }

    }

    public void setupSettlement(Player pPlayer, Intersection pIntersection){
        assert (!pIntersection.getOccupancyFlag());
        assert (pPlayer.canGetSettlement());
        assert (checkIntersectionSetupEligibility(pIntersection, pPlayer));

        Settlement settlement = pPlayer.giveSettlement();
        pIntersection.setOccupant(settlement);
    }

    public void setupCity(Player pPlayer, Intersection pIntersection){
        assert(!pIntersection.getOccupancyFlag());
        assert(pPlayer.canGetCity());
        assert(checkIntersectionSetupEligibility(pIntersection, pPlayer));

        City city = pPlayer.giveCity();
        pIntersection.setOccupant(city);
    }

    public void setupRoad(Player pPlayer, Edge pEdge){
        assert(!pEdge.getOccupancyFlag());
        assert(pPlayer.canGetRoad());
        assert(checkEdgeSetupEligibility(pEdge, pPlayer));

        Road road = pPlayer.giveRoad();
        pEdge.setOccupant(road);
    }

    private boolean checkIntersectionSetupEligibility(Intersection pIntersection, Player pPlayer){
        boolean eligible = true;
        Queue<Intersection> neighbourIntersections = pIntersection.getIntersectionNeighbours();
        for (Intersection intersection: neighbourIntersections){    //Iterate through all neighbours to see if they are occupied
            if (intersection.getOccupancyFlag()){
                eligible = false;
                break;
            }
        }
        return eligible;
    }

    private boolean checkEdgeSetupEligibility(Edge pEdge, Player pPlayer){
        Queue<Intersection> neighbourIntersections = pEdge.getIntersectionNeighbours();
        boolean eligible = false;
        for (Intersection intersection: neighbourIntersections) {
            if (intersection.getOccupancyFlag()) {
                if (intersection.getOccupant().getOwner() == pPlayer) {
                    eligible = true;
                    break;
                }
            }
        }
        return eligible;
    }

    public void endTurn(){
        setPhase(Game.GamePhase.TurnSecondPhase);
        nextPlayer();
    }

    private void nextPlayer(){
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
