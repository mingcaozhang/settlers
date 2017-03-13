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
//        model.addAttribute("player2",currPlayerList.get(1));
        //model.addAttribute("player3",currPlayerList.get(2));
        //model.addAttribute("player4",currPlayerList.get(3));

        model.addAttribute("player1_c", player1color);
//        model.addAttribute("player2_c", player2color);
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


        //backend code goes here Payout

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
    public void getHex(String bigJson) throws Exception{

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
    }

    @MessageMapping("/intersection")
    public void getIntersection(ViewIntersection pIntersection) throws Exception{
    //    System.out.println("Intersection");
        Intersection aIntersection = new Intersection(pIntersection.getId(), HarbourType.None);
        aGame.getIntersections().put(aIntersection.getId(),aIntersection);
        aGame.lIntersections.add(aIntersection);
    }

    @MessageMapping("/setNeighbours")
    public void setNeighbours() throws Exception
    {
     //   System.out.println("HNNNNNNNG");
        aGame.setAllNeighbours();
    }
}