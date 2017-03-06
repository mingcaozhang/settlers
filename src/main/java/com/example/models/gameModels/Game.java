package com.example.models.gameModels;
import java.util.*;

/**
 * Created by G on 17/03/03.
 */
public class Game {
    private final int aID;
    private GamePhase aPhase;
    private final int aVPsToWin;
    private int aBarbarianPosition;
    private int aGoldBank;
    private int aArmyStrength;
    private int aBarbarianStrength;
    private DiceNumber aRedDice;
    private DiceNumber aYellowDice;
    private EventType aEventDice;
    private Map<String, Player> aPlayers = new HashMap<String, Player>();

    public Game(int pVPsToWin, Map<String, Player> pPlayers, int pID){
        aID = pID;
        aVPsToWin = pVPsToWin;
        aBarbarianPosition = 6;
        aGoldBank = 50;
        aPlayers = pPlayers;
        aPhase = GamePhase.SetupRoundOne;
        aArmyStrength = 0;
        aBarbarianStrength = 0;
    }

    public void rollDice(){
        DiceNumber pRedDice = DiceNumber.values()[(int)(Math.random() * DiceNumber.values().length)];
        DiceNumber pYellowDice = DiceNumber.values()[(int)(Math.random() * DiceNumber.values().length)];
        EventType pEventDice = EventType.values()[(int)(Math.random() * EventType.values().length)];
        aRedDice = pRedDice;
        aYellowDice = pYellowDice;
        aEventDice = pEventDice;
    }

    public void checkBarbarian(){
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

    }

    private void defenseLoss(){

    }

    private void resetBarbPosition(){
        aBarbarianPosition = 6;
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
