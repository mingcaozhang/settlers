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

    public static void createGame(int pVPsToWin, Map<String, Player> pPlayers){
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

        Game newGame = new Game(pVPsToWin, pPlayers, aCount++, pHexes, pEdges, pIntersections, pLandHexes);
        aGame = newGame;
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
