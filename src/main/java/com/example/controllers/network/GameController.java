package com.example.controllers.network;

import com.example.models.gameModels.Game;
import com.example.models.gameModels.GameManager;
import com.example.models.gameModels.Player;
import com.example.viewobjects.DiceRoll;
import com.example.viewobjects.PlayerAndPhase;
import com.example.viewobjects.ViewPeice;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Map;

@Controller
public class GameController {

    private static ArrayList<String> currPlayerList;
    private static Game aGame;
    private static Map<String, Player> aPlayers;

    private static int placeSettlementAndRoadCounter = 0;
    private static int placeCityAndRoadCounter = 0;


    public static void setCurrPlayerList(ArrayList<String> pList){
        for (String s : pList){
            currPlayerList.add(s);
        }
    }

    public static void createGame(){
        GameManager.createGame(10, currPlayerList);
        aGame = GameManager.getGame();
        aPlayers = GameManager.getPlayers();
    }



    @RequestMapping(value="/game", method= RequestMethod.GET)
    public String game(ModelMap model, Principal principal ) {

        String name = principal.getName(); //get logged in username
        model.addAttribute("username", name);

        return "game";
    }

    @MessageMapping("/start")
    @SendTo("/topic/turn-phase")
    public PlayerAndPhase getTurn(){
        PlayerAndPhase pap = new PlayerAndPhase(currPlayerList.get(placeSettlementAndRoadCounter),"PlaceSettlement");
        return pap;
    }

    @MessageMapping("/now-place-road")
    @SendTo("/topic/turn-phase")
    public PlayerAndPhase nowPlaceRoad(){
        PlayerAndPhase pap = new PlayerAndPhase(currPlayerList.get(placeSettlementAndRoadCounter),"PlaceRoad");
        return pap;
    }

    @MessageMapping("/next-turn-place-1")
    @SendTo("/topic/turn-phase")
    public PlayerAndPhase nextPersonPlaceSettlement(){
        placeSettlementAndRoadCounter = (placeSettlementAndRoadCounter+1)%4;
        PlayerAndPhase pap = new PlayerAndPhase(currPlayerList.get(placeSettlementAndRoadCounter),"PlaceSettlement");
        return pap;
    }

    @MessageMapping("/placeSettlement")
    @SendTo("/topic/settlement")
    public ViewPeice placeSettlement(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/buySettlement")
    @SendTo("/topic/settlement")
    public ViewPeice buySettlement(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/placeRoad")
    @SendTo("/topic/road")
    public ViewPeice placeRoad(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/buyRoad")
    @SendTo("/topic/road")
    public ViewPeice buyRoad(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/placeCity")
    @SendTo("/topic/city")
    public ViewPeice placeCity(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/buyCity")
    @SendTo("/topic/city")
    public ViewPeice buyCity(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/updateDice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){
        return pDice;
    }




}