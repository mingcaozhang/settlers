package com.example.controllers.network;

import com.example.models.gameModels.*;
import com.example.viewobjects.*;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.*;

@Controller
public class GameController {

    private static ArrayList<String> currPlayerList = new ArrayList<String>(); // Get a list of players too?
    private static Game aGame;
    private static Map<String, Player> aPlayers ; // here

    private static int placeSettlementAndRoadCounter = 0;
    private static int placeCityAndRoadCounter = 0;

    private static int currPlayerTurn = 0;
    private static int currNumPlayers;

    private static final String player1color = "red";
    private static final String player2color = "yellow";
    private static final String player3color = "green";
    private static final String player4color = "blue";

    private static final PlayerAndPhase pap = new PlayerAndPhase();

    private static int turnCounter = 0;

    public static ArrayList<Player> createPlayers(ArrayList<String> currPlayerList){
        ArrayList<Player> aPlayers = new ArrayList<Player>();
        for (int i = 0; i < currPlayerList.size(); i++){
            if(i == 0) {
                Player player1 = new Player(currPlayerList.get(i), player1color);
                aPlayers.add(player1);
            }
            else if (i == 1) {
                Player player2 = new Player(currPlayerList.get(i), player2color);
                aPlayers.add(player2);
            }
            else if (i == 2) {
                Player player3 = new Player(currPlayerList.get(i), player3color);
                aPlayers.add(player3);
            }
            else if (i == 3) {
                Player player4 = new Player(currPlayerList.get(i), player4color);
                aPlayers.add(player4);
            }
        }
        return aPlayers;
    }

    public static void setCurrPlayerList(ArrayList<String> pList){
        for (String s : pList){
            currPlayerList.add(s);
        }

        currNumPlayers = currPlayerList.size();
        pap.setSetup1(true);
        pap.setSetup2(false);
    }

    public static void createGame(){

    }



    @RequestMapping(value="/game", method= RequestMethod.GET)
    public String game(ModelMap model, Principal caller) {

        String name = caller.getName(); //get logged in username
        model.addAttribute("username", name);
        model.addAttribute("startingPlayer",currPlayerList.get(0));

        for(int i = 0 ; i < currPlayerList.size(); i++){

            if (currPlayerList.get(i).matches(name)){
                String color = "";

                if (i == 0){
                    color = player1color;
                }else if(i == 1){
                    color = player2color;
                }else if(i == 2){
                    color = player3color;
                }else if( i== 3){
                    color = player4color;
                }
                model.addAttribute("myColor", color);
            }

        }

        model.addAttribute("player1",currPlayerList.get(0));
        model.addAttribute("player2",currPlayerList.get(1));
        model.addAttribute("player3",currPlayerList.get(2));
        //model.addAttribute("player4",currPlayerList.get(3));

        model.addAttribute("player1_c", player1color);
        model.addAttribute("player2_c", player2color);
        model.addAttribute("player3_c", player3color);
       // model.addAttribute("player4_c", player4color);

        GameRoomController.resetGameRoomPlayerList();
        return "game";
    }



    @MessageMapping("/placesettlement")
    @SendTo("/topic/settlement")
    public ViewPiece placeSettlement(ViewPiece pNew, Principal caller){
        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/placecity")
    @SendTo("/topic/city")
    public ViewPiece placeCity(ViewPiece pNew, Principal caller){

        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/placeroad")
    @SendTo("/topic/road")
    public ViewPiece placeRoad(ViewPiece pNew, Principal caller){

        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/setupsettlement")
    @SendTo("/topic/settlement")
    public ViewPiece setupSettlement(ViewPiece pNew, Principal caller){
        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/setupcity")
    @SendTo("/topic/city")
    public ViewPiece setupCity(ViewPiece pNew, Principal caller){
        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/setuproad")
    @SendTo("/topic/road")
    public ViewPiece setupRoad(ViewPiece pNew, Principal caller){
        pNew.setValid(true);
        return pNew;
    }

    @SendTo("/topic/playerIncrement")
    public PlayerIncrement setupPayout(){

        PlayerIncrement increment = new PlayerIncrement();
       // aGame.setupPayout();
        setPlayerIncrement(increment);
        return increment;
    }

    @MessageMapping("/rolldice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){
        //aGame.rollDice(pDice.getYellow(), pDice.getRed(), pDice.getEvent());*/
        /*diceRollPayout();*/
        return pDice;
    }

    @SendTo("/topic/playerIncrement")
    private PlayerIncrement diceRollPayout(){

        PlayerIncrement increment = new PlayerIncrement();
        //setPlayerIncrement(increment);
        return increment;
    }

    private void setPlayerIncrement(PlayerIncrement pIncrement){

    }

    @MessageMapping("/endturn")
    @SendTo("/topic/turninfo")
    public PlayerAndPhase endTurn(Principal user){



        if(turnCounter == (currPlayerList.size()-1)){
            System.out.println("first if");
            System.out.println(currPlayerList.size()-1);
            Collections.reverse(currPlayerList);
            currPlayerTurn = 0;
            pap.setSetup1(false);
            pap.setSetup2(true);

        }else if(turnCounter == (2*(currPlayerList.size())-1)){
            System.out.println("second if");
            System.out.println(currPlayerList.size()-1);
            Collections.reverse(currPlayerList);
            currPlayerTurn = 0;
            pap.setSetup1(false);
            pap.setSetup2(false);

        }else{
            currPlayerTurn = (currPlayerTurn + 1)%currNumPlayers;
        }

        pap.setUsername(currPlayerList.get(currPlayerTurn));

        System.out.println("Player's Turn "+ currPlayerList.get(currPlayerTurn));
        System.out.println("turn count = "+currPlayerTurn);

        this.turnCounter++;

        return pap;
    }


    @MessageMapping("/edge")
    public void getEdge(ViewEdge pEdge) throws Exception{
        /*
        Edge aEdge = new Edge(pEdge.getId());
    //    System.out.println(aEdge.getId());
    //    System.out.println(aEdge.getX());
    //    System.out.println(aEdge.getY());
    //    System.out.println(aEdge.getPrefix());
        aGame.getEdges().put(aEdge.getId(),aEdge);
        aGame.lEdges.add(aEdge);
        */
    }

    @MessageMapping("/hex")
    public void getHex(String bigJson) throws Exception{
        /*

        Hex aHex;
       // System.out.println("Hexagon");
        JSONArray aArray = new JSONArray(bigJson);
        Gson gson = new Gson();

        for(int i=0;i<aArray.length();i++) {

         //   System.out.println(i+" out of "+aArray.length());
            JSONObject jsonHex = aArray.getJSONObject(i);
         //   System.out.println(jsonHex);

            ViewHex pHex = gson.fromJson(jsonHex.toString(), ViewHex.class);
          //  System.out.println(pHex.getId());


            switch (pHex.getTerrainType()) {
                case "wood":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Forest);
                    break;
                case "ore":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Mountains);
                    break;
                case "brick":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Hills);
                    break;
                case "sheep":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Pasture);
                    break;
                case "gold":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.GoldMine);
                    break;
                case "wheat":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Fields);
                    break;
                case "desert":
                    aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Desert);
                    break;
                case "sea":
                    aHex = new SeaHex(pHex.getId());
                default:
                    aHex = new SeaHex(pHex.getId());
            }

            aGame.getHexes().put(aHex.getId(), aHex);
            aGame.lHexes.add(aHex);
        }
        */
    }

    @MessageMapping("/intersection")
    public void getIntersection(ViewIntersection pIntersection) throws Exception{

    }

    @MessageMapping("/setNeighbours")
    public void setNeighbours() throws Exception
    {

    }
}