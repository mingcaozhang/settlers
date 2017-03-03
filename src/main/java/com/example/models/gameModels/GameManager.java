package com.example.models.gameModels;

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
        Game newGame = new Game(pVPsToWin, pPlayers, aCount++);
        //add this game to psql db
        aGame = newGame;
    }

    public static void getGame(int pCount){
        //sql query select game where acount = pcount
    }

    public static void rollDice(){
        assert (aGame != null);
        aGame.rollDice();
    }

    public static void checkBarbarianStep(){
        assert (aGame != null);
        aGame.checkBarbarian();
    }
}
