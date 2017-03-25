package com.example.models.gameModels;
import java.util.*;

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
        aResourceCards = new HashMap<>();
        aCommodityCards = new HashMap<>();
        for (StealableCard.Resource resource : StealableCard.Resource.values()){
            aResourceCards.put(resource, StealableCard.Resource.maxResources());
        }
        for (StealableCard.Commodity commodity : StealableCard.Commodity.values()){
            aCommodityCards.put(commodity, StealableCard.Commodity.maxCommodities());
        }


//      aPlayerQueue = new LinkedList<Player>();
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

    public HashMap<StealableCard.Resource, Integer> getResourceCards(){
        return aResourceCards;
    }

    public HashMap<StealableCard.Commodity, Integer> getCommodityCards(){
        return aCommodityCards;
    }

    public int getGoldBank(){
        return aGoldBank;
    }

    public GamePhase getPhase(){
        return aPhase;
    }

    //add and remove resources
    public void addResource(StealableCard.Resource pResource, int pAmount) {
        aResourceCards.put(pResource, aResourceCards.get(pResource) + pAmount);
    }
    public void removeResource(StealableCard.Resource pResource, int pAmount) {
        assert (aResourceCards.get(pResource) >= pAmount);
        aResourceCards.put(pResource, aResourceCards.get(pResource) - pAmount);
    }

    //add and remove commodities
    public void addCommodity(StealableCard.Commodity pCommodity, int pAmount) {
        aCommodityCards.put(pCommodity, aCommodityCards.get(pCommodity) + pAmount);
    }
    public void removeCommodity(StealableCard.Commodity pCommodity, int pAmount) {
        assert (aCommodityCards.get(pCommodity) >= pAmount);
        aCommodityCards.put(pCommodity, aCommodityCards.get(pCommodity) - pAmount);
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

        if (getTurnCounter() > 2) {
            checkBarbarian();
        }
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
                    if (intersection.getValue().getBuilding() != null){
                        setBarbarianStrength(getBarbarianStrength() + 1);
                    }
                    else if (intersection.getValue().getKnight() != null){
                        if ((intersection.getValue().getKnight().getState())){
                            setArmyStrength(getArmyStrength() + (intersection.getValue().getKnight()).getStrength());
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
        assert (pIntersection.getBuilding() != null);
        return pIntersection.getBuilding().getOwner();
    }

    private boolean checkIsCity(Intersection pIntersection){
        return (pIntersection.getBuilding() != null && pIntersection.getBuilding().isCity());
    }

    private void payout(Player pOwner, TerrainType pTerrainType, boolean isCity){
        if (pTerrainType == TerrainType.GoldMine){
            if (removeGold()) {
                pOwner.addGold();
            }
        }
        else {
            StealableCard.Resource resource = pTerrainType.giveResource();
            assert(aResourceCards.get(resource) > 0);
            pOwner.addResource(resource, 1);
            removeResource(resource, 1);
        }

        if (isCity) {
            StealableCard.Resource resource = pTerrainType.giveResource();
            if (pTerrainType == TerrainType.Hills || pTerrainType == TerrainType.Fields){
                assert(aResourceCards.get(resource) > 0);
                pOwner.addResource(resource, 1);
                removeResource(resource, 1);
            }
            else if (pTerrainType == TerrainType.GoldMine){
                if (removeGold()){
                    pOwner.addGold();
                }
            }
            else{
                StealableCard.Commodity commodity = pTerrainType.giveCommodity();
                assert(aCommodityCards.get(commodity) > 0);
                pOwner.addCommodity(commodity, 1);
                removeCommodity(commodity, 1);
            }
        }
    }

    public void setupPayout(){
        for (Map.Entry<String,Intersection> intersection : aIntersections.entrySet()){
            if (intersection.getValue().getBuilding() != null && intersection.getValue().getBuilding().isCity()){
                Player owner = intersection.getValue().getBuilding().getOwner();
                Intersection cityLocation = intersection.getValue();
                for(Hex hex : cityLocation.getHexNeighbours()){
                    TerrainType pTerrainType = hex.getTerrainType();
                    StealableCard.Resource resource = pTerrainType.giveResource();
                    owner.addResource(resource, 1);
                    removeResource(resource, 1);
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
        pPlayer.removeResource(StealableCard.Resource.SHEEP, 1);
        addResource(StealableCard.Resource.SHEEP, 1);
        pPlayer.removeResource(StealableCard.Resource.WHEAT, 1);
        addResource(StealableCard.Resource.WHEAT, 1);
        pPlayer.removeResource(StealableCard.Resource.BRICK, 1);
        addResource(StealableCard.Resource.BRICK, 1);
        pPlayer.removeResource(StealableCard.Resource.WOOD, 1);
        addResource(StealableCard.Resource.WOOD, 1);
    }

    private void payCity(Player pPlayer){
        pPlayer.removeResource(StealableCard.Resource.WHEAT, 2);
        addResource(StealableCard.Resource.WHEAT, 2);
        pPlayer.removeResource(StealableCard.Resource.ORE, 3);
        addResource(StealableCard.Resource.ORE, 3);
    }

    private void payRoad(Player pPlayer){
        pPlayer.removeResource(StealableCard.Resource.BRICK, 1);
        addResource(StealableCard.Resource.BRICK, 1);
        pPlayer.removeResource(StealableCard.Resource.WOOD, 1);
        addResource(StealableCard.Resource.WOOD, 1);
    }

    public void placeSettlement(Player pPlayer, Intersection pIntersection){
        assert(!pIntersection.getOccupancyFlag());
        assert(checkIntersectionEligibility(pIntersection, pPlayer));
        assert(checkBuySettlement(pPlayer));

        paySettlement(pPlayer);
        OwnedBuilding settlement = pPlayer.removeBuilding(Unit.Building.SETTLEMENT);
        pIntersection.setBuilding(settlement);
    }

    public void placeCity(Player pPlayer, Intersection pIntersection){
        assert(pIntersection.getOccupancyFlag());
        assert(pIntersection.getBuilding() != null && !pIntersection.getBuilding().isCity() && pIntersection.getBuilding().getOwner() == pPlayer);
        assert(checkBuyCity(pPlayer));

        payCity(pPlayer);
        OwnedBuilding city = pPlayer.removeBuilding(Unit.Building.CITY);
        pPlayer.addBuilding(Unit.Building.SETTLEMENT);
        pIntersection.setBuilding(city);
    }

    public void placeRoad(Player pPlayer, Edge pEdge){
        assert(!pEdge.getOccupancyFlag());
        assert(checkEdgeEligibility(pEdge, pPlayer));
        assert(checkBuyRoad(pPlayer));

        payRoad(pPlayer);
        OwnedTransport road = pPlayer.removeTransport(Unit.Transport.ROAD);
        pEdge.setTransport(road);
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
                if (edge.getTransport().getOwner() == pPlayer) {
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
                if (edge.getTransport().getOwner() == pPlayer) {
                    eligible = true;
                    break;
                }
            }
        }
        return eligible;
    }

    private boolean checkBuySettlement(Player pPlayer) {
        if (pPlayer.getResourceCards().get(StealableCard.Resource.SHEEP) == 0 ||
                pPlayer.getResourceCards().get(StealableCard.Resource.ORE) == 0 ||
                pPlayer.getResourceCards().get(StealableCard.Resource.WOOD) == 0 ||
                pPlayer.getResourceCards().get(StealableCard.Resource.BRICK) == 0){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkBuyCity(Player pPlayer){
        if (pPlayer.getResourceCards().get(StealableCard.Resource.ORE) < 3 || pPlayer.getResourceCards().get(StealableCard.Resource.WHEAT) < 2){
            return false;
        }
        else{
            return true;
        }
    }

    private boolean checkBuyRoad(Player pPlayer){
       if (pPlayer.getResourceCards().get(StealableCard.Resource.WOOD) == 0 || pPlayer.getResourceCards().get(StealableCard.Resource.BRICK) == 0){
           return false;
       }
       else{
           return true;
       }

    }

    public void setupSettlement(Player pPlayer, Intersection pIntersection){
        assert (!pIntersection.getOccupancyFlag());
        assert (checkIntersectionSetupEligibility(pIntersection));

        OwnedBuilding settlement = pPlayer.removeBuilding(Unit.Building.SETTLEMENT);
        pIntersection.setBuilding(settlement);
    }

    public void setupCity(Player pPlayer, Intersection pIntersection){
        assert(!pIntersection.getOccupancyFlag());
        assert(checkIntersectionSetupEligibility(pIntersection));

        OwnedBuilding city = pPlayer.removeBuilding(Unit.Building.CITY);
        pIntersection.setBuilding(city);
    }

    public void setupRoad(Player pPlayer, Edge pEdge){
        assert(!pEdge.getOccupancyFlag());
        assert(checkEdgeSetupEligibility(pEdge, pPlayer));

        OwnedTransport road = pPlayer.removeTransport(Unit.Transport.ROAD);
        pEdge.setTransport(road);
    }

    private boolean checkIntersectionSetupEligibility(Intersection pIntersection){
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
                if (intersection.getBuilding().getOwner() == pPlayer) {
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
