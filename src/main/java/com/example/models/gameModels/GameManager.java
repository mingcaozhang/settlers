package com.example.models.gameModels;

import com.sun.org.apache.bcel.internal.generic.LAND;

import java.util.*;

/**
 * Created by G on 17/03/03.
 */
public class GameManager {
    private static GameManager gameManager = new GameManager();
    private static Game aGame;
    private static int aCount;

    private GameManager(){
        aCount = 0;
    }

    public GameManager getGameManager(){
        return gameManager;
    }

    public static void createGame(int pVPsToWin){
        Hex[][] pHexes = new Hex[20][20];
        Edge[][] pEdges = new Edge[20][20];
        Intersection[][] pIntersections = new Intersection[20][20];
        HashMap<Integer, ArrayList<LandHex>> pLandHexes = new HashMap<Integer, ArrayList<LandHex>>();

        for (int k = 2; k < 13; k++){
            pLandHexes.put(k, new ArrayList<LandHex>());
        }

        for (int i = 0; i < 20; i++){
            for (int j = 0; j < 20; j++){
                if (pHexes[i][j].getTerrainType() != TerrainType.Sea || pHexes[i][j].getTerrainType() != TerrainType.Desert || pHexes[i][j].getTerrainType() != null){
                     pLandHexes.get(((LandHex)pHexes[i][j]).getProductionNumber()).add((LandHex)pHexes[i][j]);
                }
            }
        }
        String[] temparray = new String[4];
        HashMap<String, Player> aListofPlayers = createPlayers(temparray);
        GameManager.setPlayerProperties(aListofPlayers);
        Game newGame = new Game(pVPsToWin, aListofPlayers, aCount++, pHexes, pEdges, pIntersections, pLandHexes);
        aGame = newGame;
    }

    public static HashMap<String, Player> createPlayers(String[] pUsernames){
        HashMap<String, Player> aPlayers = new HashMap<String,Player>();
        if (pUsernames[0] != null) {
            Player p1 = new Player(pUsernames[0], Player.Color.Blue);
            aPlayers.put(p1.getUsername(), p1);
        }
        if (pUsernames[1] != null) {
            Player p2 = new Player(pUsernames[1], Player.Color.Orange);
            aPlayers.put(p2.getUsername(), p2);
        }
        if (pUsernames[2] != null) {
            Player p3 = new Player(pUsernames[1], Player.Color.Red);
            aPlayers.put(p3.getUsername(), p3);
        }
        if (pUsernames[3] != null) {
            Player p4 = new Player(pUsernames[1], Player.Color.White);
            aPlayers.put(p4.getUsername(), p4);
        }
        return aPlayers;
    }

    public static void setPlayerProperties(HashMap<String, Player> pListofPlayers){
        Queue<City> allCities = City.getUnits();
        Queue<Settlement> allSettlements = Settlement.getUnits();
        Queue<Road> allRoads = Road.getUnits();
        Queue<Ship> allShips = Ship.getUnits();
        Queue<Wall> allWalls = Wall.getUnits();
        Queue<BasicKnight> allBasicKnights = BasicKnight.getUnits();
        Queue<StrongKnight> allStrongKnights = StrongKnight.getUnits();
        Queue<MightyKnight> allMightyKnights = MightyKnight.getUnits();

        for (Player player : pListofPlayers.values()){
            Queue<City> aCities = new LinkedList<City>();
            Queue<Settlement> aSettlements = new LinkedList<Settlement>();
            Queue<Road> aRoads = new LinkedList<Road>();
            Queue<Ship> aShips = new LinkedList<Ship>();
            Queue<Wall> aWalls = new LinkedList<Wall>();
            Queue<BasicKnight> aBasicKnights = new LinkedList<BasicKnight>();
            Queue<StrongKnight> aStrongKnights = new LinkedList<StrongKnight>();
            Queue<MightyKnight> aMightyKnights = new LinkedList<MightyKnight>();

            addCitiesToPlayer(allCities, aCities, player);
            addSettlementsToPlayer(allSettlements, aSettlements, player);
            addRoadsToPlayer(allRoads, aRoads, player);
            addShipsToPlayer(allShips, aShips, player);
            addWallsToPlayer(allWalls, aWalls, player);
            addBasicKnightsToPlayer(allBasicKnights, aBasicKnights, player);
            addStrongKnightsToPlayer(allStrongKnights, aStrongKnights, player);
            addMightyKnightsToPlayer(allMightyKnights, aMightyKnights, player);
        }
    }

    private static void addCitiesToPlayer(Queue<City> allCities, Queue<City> playerCities, Player pPlayer){
        for (int i = 0; i < 4; i++){
            playerCities.add(allCities.remove());
        }
        pPlayer.setCities(playerCities);
    }

    private static void addSettlementsToPlayer(Queue<Settlement> allSettlements, Queue<Settlement> playerSettlements, Player pPlayer){
        for (int i = 0; i < 4; i++){
            playerSettlements.add(allSettlements.remove());
        }
        pPlayer.setSettlements(playerSettlements);
    }

    private static void addRoadsToPlayer(Queue<Road> allRoads, Queue<Road> playerRoads, Player pPlayer){
        for (int i = 0; i < 15; i++){
            playerRoads.add(allRoads.remove());
        }
        pPlayer.setRoads(playerRoads);
    }

    private static void addShipsToPlayer(Queue<Ship> allShips, Queue<Ship> playerShips, Player pPlayer){
        for (int i = 0; i < 15; i++){
            playerShips.add(allShips.remove());
        }
        pPlayer.setShips(playerShips);
    }

    private static void addWallsToPlayer(Queue<Wall> allWalls, Queue<Wall> playerWalls, Player pPlayer){
        for (int i = 0; i < 3; i++){
            playerWalls.add(allWalls.remove());
        }
        pPlayer.setWalls(playerWalls);
    }

    private static void addBasicKnightsToPlayer(Queue<BasicKnight> allBasicKnights, Queue<BasicKnight> playerBasicKnights, Player pPlayer){
        for (int i = 0; i < 2; i++){
            playerBasicKnights.add(allBasicKnights.remove());
        }
        pPlayer.setBasicKnights(playerBasicKnights);
    }

    private static void addStrongKnightsToPlayer(Queue<StrongKnight> allStrongKnights, Queue<StrongKnight> playerStrongKnights, Player pPlayer){
        for (int i = 0; i < 2; i++){
            playerStrongKnights.add(allStrongKnights.remove());
        }
        pPlayer.setStrongKnights(playerStrongKnights);
    }

    private static void addMightyKnightsToPlayer(Queue<MightyKnight> allMightyKnights, Queue<MightyKnight> playerMightyKnights, Player pPlayer){
        for (int i = 0; i < 2; i++){
            playerMightyKnights.add(allMightyKnights.remove());
        }
        pPlayer.setMightyKnights(playerMightyKnights);
    }
    
    public static void getGame(int pCount){
        //sql query select game where acount = pcount
    }

    public static void rollDice(){
        assert (aGame != null);
        aGame.rollDice();
    }

    public static void placeSettlement(Player pPlayer, Intersection pIntersection){
        assert (aGame != null);
        aGame.placeSettlement(pPlayer, pIntersection);
    }

    public static void placeCity(Player pPlayer, Intersection pIntersection){
        assert (aGame != null);
        aGame.placeCity(pPlayer, pIntersection);
    }

    public static void placeRoad(Player pPlayer, Intersection pIntersection){
        assert (aGame != null);
        aGame.placeCity(pPlayer, pIntersection);
    }
}
