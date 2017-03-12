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

    private static ArrayList<String> currPlayerList = new ArrayList<String>();
    private static Game aGame;
    private static Map<String, Player> aPlayers ;

    private static int placeSettlementAndRoadCounter = 0;
    private static int placeCityAndRoadCounter = 0;

    private static int currPlayerTurn = 0;
    private static int currNumPlayers;

    private static final String player1color = "red";
    private static final String player2color = "yellow";
    private static final String player3color = "green";
    private static final String player4color = "blue";


    public static void setCurrPlayerList(ArrayList<String> pList){
        for (String s : pList){
            currPlayerList.add(s);
        }

        currNumPlayers = currPlayerList.size();
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
        model.addAttribute("startingPlayer",currPlayerList.get(0));

        for(int i = 0 ; i < currPlayerList.size(); i++){

            if (currPlayerList.get(i).matches(name)){
                String color = "";

                if (i == 0){
                    color = player1color;
                }else if(i == 2){
                    color = player2color;
                }else if(i == 3){
                    color = player3color;
                }else if( i== 4){
                    color = player4color;
                }
                model.addAttribute("myColor", color);
            }

        }

        model.addAttribute("player1",currPlayerList.get(0));
        model.addAttribute("player2",currPlayerList.get(1));
        //model.addAttribute("player3",currPlayerList.get(2));
        //model.addAttribute("player4",currPlayerList.get(3));

        model.addAttribute("player1_c", player1color);
        model.addAttribute("player2_c", player2color);
       // model.addAttribute("player3_c", player3color);
       // model.addAttribute("player4_c", player4color);



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

    @MessageMapping("/rolldice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){


        //backend code goes here

        return pDice;
    }

    @MessageMapping("/endturn")
    @SendTo("/topic/turninfo")
    public String endTurn(Principal user){

        System.out.println("I am here!!!!!!!!!!!!!");
        currPlayerTurn = (currPlayerTurn + 1)%currNumPlayers;
        //backend code goes here


        System.out.println("Returning!!!!!!!!");
        return currPlayerList.get(currPlayerTurn);
    }




}