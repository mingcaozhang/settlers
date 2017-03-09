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




    @MessageMapping("/placepiece")
    @SendTo("/topic/piece")
    public ViewPeice placepiece(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/placecity")
    @SendTo("/topic/city")
    public ViewPeice placecity(ViewPeice pNew){
        return pNew;
    }

    @MessageMapping("/updateDice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){
        return pDice;
    }




}