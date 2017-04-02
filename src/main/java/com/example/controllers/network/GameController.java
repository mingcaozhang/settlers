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
import java.util.Collections;
import java.util.Map;

@Controller
public class GameController {

    private static ArrayList<String> currPlayerList = new ArrayList<String>(); // Get a list of players too?
    private static Game aGame;
    private static Map<String, Player> aPlayers ; // here

    private static int placeSettlementAndRoadCounter = 0;
    private static int placeCityAndRoadCounter = 0;
    private static int currNumPlayers;
    private static int currPlayerTurn = 0;
    private static final PlayerAndPhase pap = new PlayerAndPhase();
    private static int turnCounter = 0;
    public static void setCurrPlayerList(ArrayList<String> pList){
        for (String s : pList){
            currPlayerList.add(s);
        }

        currNumPlayers = currPlayerList.size();
        pap.setSetup1(true);
        pap.setSetup2(false);
    }

    @RequestMapping(value="/game", method= RequestMethod.GET)
    public String game(ModelMap model, Principal caller) {

        String name = caller.getName(); //get logged in username
        model.addAttribute("username", name);
        model.addAttribute("startingPlayer",currPlayerList.get(0));

        for(int i = 0 ; i < currPlayerList.size(); i++){
            if (currPlayerList.get(i).matches(name)){
                String color = GameManager.instance().getGame().getPlayers().get(i).getColor();
                model.addAttribute("myColor", color);
            }
        }

        for(int i = 0 ; i < currPlayerList.size(); i++){
            model.addAttribute("player"+(i+1),currPlayerList.get(i));
        }

        for(int i = 0 ; i < currPlayerList.size(); i++){
            model.addAttribute("player"+(i+1)+"_c", GameManager.getGame().getPlayers().get(i).getColor());
        }
        aGame = GameManager.getGame();

        return "game";
    }



    @MessageMapping("/placesettlement")
    @SendTo("/topic/settlement")
    public ViewPiece placeSettlement(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeSettlement(callingPlayer, aGame.getIntersections().get(pNew.getId()));*/
        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/placecity")
    @SendTo("/topic/city")
    public ViewPiece placeCity(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeCity(callingPlayer, aGame.getIntersections().get(pNew.getId()));*/
        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/placeroad")
    @SendTo("/topic/road")
    public ViewPiece placeRoad(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.placeRoad(callingPlayer, aGame.getEdges().get(pNew.getId()));*/
        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/setupsettlement")
    @SendTo("/topic/settlement")
    public ViewPiece setupSettlement(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.setupSettlement(callingPlayer, aGame.getIntersections().get(pNew.getId()));*/
        pNew.setValid(true);
        return pNew;
    }

    @MessageMapping("/setupcity")
    @SendTo("/topic/city")
    public ViewPiece setupCity(ViewPiece pNew, Principal caller){
        /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }

        aGame.setupCity(callingPlayer, aGame.getIntersections().get(pNew.getId()));*/
        pNew.setValid(true);
        return pNew;

    }

    @MessageMapping("/setuproad")
    @SendTo("/topic/road")
    public ViewPiece setupRoad(ViewPiece pNew, Principal caller){

         /*
        Player callingPlayer = new Player(null, null);
        for (Player player : aGame.getPlayers()){
            if (player.getUsername() == caller.getName()){
                callingPlayer = player;
                break;
            }
        }
        aGame.setupRoad(callingPlayer, aGame.getEdges().get(pNew.getId()));
        */
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
        /*
        for (String pUsername : currPlayerList){
            for (Player player : aGame.getPlayers()) {
                if (pUsername.equals(player.getUsername())) {
                    int index = currPlayerList.indexOf(player.getUsername());
                    switch (index) {
                        case 1:
                            pIncrement.setp1(
                                    player.getGold(),
                                    player.getResourceCards().get(StealableCard.Resource.ORE),
                                    player.getResourceCards().get(StealableCard.Resource.BRICK),
                                    player.getResourceCards().get(StealableCard.Resource.WOOD),
                                    player.getResourceCards().get(StealableCard.Resource.SHEEP),
                                    player.getCommodityCards().get(StealableCard.Commodity.COIN),
                                    player.getCommodityCards().get(StealableCard.Commodity.CLOTH),
                                    player.getCommodityCards().get(StealableCard.Commodity.PAPER));
                        case 2:
                            pIncrement.setp2(
                                    player.getGold(),
                                    player.getResourceCards().get(StealableCard.Resource.ORE),
                                    player.getResourceCards().get(StealableCard.Resource.BRICK),
                                    player.getResourceCards().get(StealableCard.Resource.WOOD),
                                    player.getResourceCards().get(StealableCard.Resource.SHEEP),
                                    player.getCommodityCards().get(StealableCard.Commodity.COIN),
                                    player.getCommodityCards().get(StealableCard.Commodity.CLOTH),
                                    player.getCommodityCards().get(StealableCard.Commodity.PAPER));
                        case 3:
                            pIncrement.setp3(
                                    player.getGold(),
                                    player.getResourceCards().get(StealableCard.Resource.ORE),
                                    player.getResourceCards().get(StealableCard.Resource.BRICK),
                                    player.getResourceCards().get(StealableCard.Resource.WOOD),
                                    player.getResourceCards().get(StealableCard.Resource.SHEEP),
                                    player.getCommodityCards().get(StealableCard.Commodity.COIN),
                                    player.getCommodityCards().get(StealableCard.Commodity.CLOTH),
                                    player.getCommodityCards().get(StealableCard.Commodity.PAPER));
                        case 4:
                            pIncrement.setp4(
                                    player.getGold(),
                                    player.getResourceCards().get(StealableCard.Resource.ORE),
                                    player.getResourceCards().get(StealableCard.Resource.BRICK),
                                    player.getResourceCards().get(StealableCard.Resource.WOOD),
                                    player.getResourceCards().get(StealableCard.Resource.SHEEP),
                                    player.getCommodityCards().get(StealableCard.Commodity.COIN),
                                    player.getCommodityCards().get(StealableCard.Commodity.CLOTH),
                                    player.getCommodityCards().get(StealableCard.Commodity.PAPER));
                    }
                }
            }
        }
        */
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

        Edge aEdge = new Edge(pEdge.getId());
    //    System.out.println(aEdge.getId());
    //    System.out.println(aEdge.getX());
    //    System.out.println(aEdge.getY());
    //    System.out.println(aEdge.getPrefix());
        GameManager.getGame().getBoard().getEdges().put(aEdge.getId(),aEdge);

    }

    @MessageMapping("/hex")
    public void getHex(ViewHex pHex) throws Exception{


        Hex aHex;
        SeaHex seaHex = new SeaHex(pHex.getId());
        //System.out.println(seaHex.getId());
       // System.out.println("Hexagon");
      /*  JSONArray aArray = new JSONArray(bigJson);
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
*/
            GameManager.getGame().getBoard().getHexes().put(seaHex.getId(), seaHex);
      //  }

    }

    @MessageMapping("/intersection")
    public void getIntersection(ViewIntersection pIntersection) throws Exception{

       // System.out.println("Intersection");
        Intersection aIntersection = new Intersection(pIntersection.getId(), HarbourType.None);
        GameManager.getGame().getBoard().getIntersections().put(aIntersection.getId(),aIntersection);
    }

    @MessageMapping("/setNeighbours")
    public void setNeighbours() throws Exception
    {
        GameManager.getGame().getBoard().setAllNeighbours();
    }
}