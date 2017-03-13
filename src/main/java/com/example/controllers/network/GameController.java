package com.example.controllers.network;

import com.example.models.gameModels.*;
import com.example.viewobjects.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private static List<String> currPlayerList = new ArrayList<String>(); // Get a list of players too?
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




    public static void setCurrPlayerList(ArrayList<String> pList){
        for (String s : pList){
            currPlayerList.add(s);
        }

        currNumPlayers = currPlayerList.size();
    }

    public static void createGame(){
        List<String> myColors = new ArrayList<String>();
        myColors.add(0,player1color);
        myColors.add(1,player2color);
        myColors.add(2,player3color);
        myColors.add(3,player3color);
        aGame = new Game(10,currPlayerList,myColors);
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


    @MessageMapping("/placesettlement")
    @SendTo("/topic/piece")
    public ViewPiece placeSettlement(ViewPiece pNew, Principal caller){
        Player callingPlayer = new Player(null, null, 0);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeSettlement(callingPlayer, aGame.getIntersections().get(pNew.getId()));
        return pNew;
    }

    @MessageMapping("/placecity")
    @SendTo("/topic/piece")
    public ViewPiece placeCity(ViewPiece pNew, Principal caller){
        Player callingPlayer = new Player(null, null, 0);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeCity(callingPlayer, aGame.getIntersections().get(pNew.getId()));
        return pNew;
    }

    @MessageMapping("/placeroad")
    @SendTo("/topic/piece")
    public ViewPiece placeRoad(ViewPiece pNew, Principal caller){
        Player callingPlayer = new Player(null, null, 0);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeRoad(callingPlayer, aGame.getEdges().get(pNew.getId()));
        return pNew;
    }

    @MessageMapping("/setupsettlement")
    @SendTo("/topic/piece")
    public ViewPiece setupSettlement(ViewPiece pNew, Principal caller){
        Player callingPlayer = new Player(null, null, 0);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.setupSettlement(callingPlayer, aGame.getIntersections().get(pNew.getId()));
        return pNew;
    }

    @MessageMapping("/setupcity")
    @SendTo("/topic/city")
    public ViewPiece setupCity(ViewPiece pNew, Principal caller){
        Player callingPlayer = new Player(null, null, 0);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.setupCity(callingPlayer, aGame.getIntersections().get(pNew.getId()));
        return pNew;
    }

    @MessageMapping("/setuproad")
    @SendTo("/topic/piece")
    public ViewPiece setupRoad(ViewPiece pNew, Principal caller){
        Player callingPlayer = new Player(null, null, 0);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.setupRoad(callingPlayer, aGame.getEdges().get(pNew.getId()));
        return pNew;
    }

    @SendTo("/topic/playerIncrement")
    public PlayerIncrement setupPayout(){
        PlayerIncrement increment = new PlayerIncrement();

        for(Player player: aGame.getPlayers()){
            //setp#
        }

        //        TODO
//        increment.setp1();
//        increment.setp2();
//        increment.setp3();
//        increment.setp4();
        return increment;
    }

    @MessageMapping("/rolldice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){
        aGame.rollDice(pDice.getYellow(), pDice.getRed(), pDice.getEvent());
        diceRollPayout();
        return pDice;
    }

    @SendTo("/topic/playerIncrement")
    private PlayerIncrement diceRollPayout(){
        PlayerIncrement increment = new PlayerIncrement();
        for (Player player : aGame.getPlayers()){
            //setp#
        }
//        TODO
//        increment.setp1();
//        increment.setp2();
//        increment.setp3();
//        increment.setp4();
        return increment;
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


    @MessageMapping("/edge")
    public void getEdge(ViewEdge pEdge) throws Exception{
        Edge aEdge = new Edge(pEdge.getId());
    //    System.out.println(aEdge.getId());
    //    System.out.println(aEdge.getX());
    //    System.out.println(aEdge.getY());
    //    System.out.println(aEdge.getPrefix());
        aGame.getEdges().put(aEdge.getId(),aEdge);
        aGame.lEdges.add(aEdge);
    }

    @MessageMapping("/hex")
    public void getHex(ViewHex pHex) throws Exception{

        Hex aHex;
            System.out.println("Hexagon");

        switch (pHex.getTerrainType())
        {
            case "wood" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Forest ); break;
            case "ore" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Mountains); break;
            case "brick" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Hills); break;
            case "sheep" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Pasture ); break;
            case "gold" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.GoldMine); break;
            case "wheat" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Fields); break;
            case "desert" : aHex = new LandHex(pHex.getId(),pHex.getNumber(),TerrainType.Desert); break;
            case "sea" : aHex = new SeaHex(pHex.getId());
            default: aHex= new SeaHex(pHex.getId());
        }
        aGame.getHexes().put(aHex.getId(),aHex);
        aGame.lHexes.add(aHex);
    }

    @MessageMapping("/intersection")
    public void getIntersection(ViewIntersection pIntersection) throws Exception{
        System.out.println("Intersection");
        Intersection aIntersection = new Intersection(pIntersection.getId(), HarbourType.None);
        aGame.getIntersections().put(aIntersection.getId(),aIntersection);
        aGame.lIntersections.add(aIntersection);
    }

    @MessageMapping("/setNeighbours")
    public void setNeighbours() throws Exception
    {
        System.out.println("HNNNNNNNG");

        aGame.setAllNeighbours();
    }
}