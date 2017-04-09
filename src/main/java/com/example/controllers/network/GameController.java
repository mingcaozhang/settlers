package com.example.controllers.network;

import com.example.models.gameModels.*;
import com.example.viewobjects.*;
import com.google.gson.Gson;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.json.*;
import com.google.gson.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

@Controller
public class GameController {

    @Autowired
    private GameManager gameManager;

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
                String color = gameManager.getGame().getPlayers().get(i).getColor();
                model.addAttribute("myColor", color);
            }
        }

        for(int i = 0 ; i < currPlayerList.size(); i++){
            model.addAttribute("player"+(i+1),currPlayerList.get(i));
        }

        for(int i = 0 ; i < currPlayerList.size(); i++){
            model.addAttribute("player"+(i+1)+"_c", gameManager.getGame().getPlayers().get(i).getColor());
        }
        aGame = gameManager.getGame();

        return "game";
    }

    private Player getPlayerFromString(String pPlayerString){
        Player returnedPlayer = null;
        for (Player player : gameManager.getGame().getPlayers()){
            if (player.getUsername().equals(pPlayerString)){
                returnedPlayer = player;
            }
        }
        return returnedPlayer;
    }

    @MessageMapping("/placesettlement")
    @SendTo("/topic/settlement")
    public ViewPiece placeSettlement(ViewPiece pNew, Principal caller){
        Player checkee = getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        boolean isValid = gameManager.checkBuySettlement(checkee) && gameManager.checkSettlementPlaceEligibility(checker,pNew.getColor());
        if(isValid)
        {
            gameManager.paySettlement(checkee);
            gameManager.placeSettlement(checkee, checker);
        }
        pNew.setValid(isValid);
        return pNew;
    }

    @MessageMapping("/placecity")
    @SendTo("/topic/city")
    public ViewPiece placeCity(ViewPiece pNew, Principal caller){
        Player checkee = getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        boolean isValid = gameManager.checkBuyCity(checkee) && gameManager.checkCityPlaceEligibility(checker,pNew.getColor());
        if(isValid)
        {
            gameManager.payCity(checkee);
            gameManager.placeCity(checkee, checker);
        }
        pNew.setValid(isValid);
        return pNew;
    }

    @MessageMapping("/placeroad")
    @SendTo("/topic/road")
    public ViewPiece placeRoad(ViewPiece pNew, Principal caller){
        Player checkee = getPlayerFromString(caller.getName());
        Edge checker = gameManager.getGame().getBoard().getEdges().get(pNew.getId());
        boolean isValid = gameManager.checkBuyRoad(checkee) && gameManager.checkRoadEligibility(checker,pNew.getColor());
        if(isValid)
        {
            gameManager.payRoad(checkee);
            gameManager.placeRoad(checkee, checker);
        }
        pNew.setValid(isValid);
        return pNew;
    }

    @MessageMapping("/placeship")
    @SendTo("/topic/ship")
    public ViewPiece placeShip(ViewPiece pNew, Principal caller){
        Player checkee = getPlayerFromString(caller.getName());
        Edge checker = gameManager.getGame().getBoard().getEdges().get(pNew.getId());
        boolean isValid = gameManager.checkBuyShip(checkee) && gameManager.checkShipEligibility(checker,pNew.getColor());
        if(isValid)
        {
            gameManager.payShip(checkee);
            gameManager.placeShip(checkee, checker);
        }
        pNew.setValid(isValid);
        return pNew;
    }

    // SETUP IS FIRST 2 TURNS

    @MessageMapping("/setupsettlement")
    @SendTo("/topic/settlement")
    public ViewPiece setupSettlement(ViewPiece pNew, Principal caller){
        Player checkee = getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        boolean isValid = gameManager.checkIntersectionSetupEligibility(checker);
        if(isValid) {
            gameManager.placeSettlement(checkee, checker);
        }
        pNew.setValid(isValid);
        return pNew;
    }

    @MessageMapping("/setupcity")
    @SendTo("/topic/city")
    public ViewPiece setupCity(ViewPiece pNew, Principal caller){
        Player checkee = getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        boolean isValid = gameManager.checkIntersectionSetupEligibility(checker);
        if(isValid) {
            gameManager.placeCity(checkee, checker);
        }
        pNew.setValid(isValid);
        return pNew;
    }

    @MessageMapping("/setuproad")
    @SendTo("/topic/road")
    public ViewPiece setupRoad(ViewPiece pNew, Principal caller) {
        Player checkee = getPlayerFromString(caller.getName());
        Edge checker = gameManager.getGame().getBoard().getEdges().get(pNew.getId());
        boolean isValid = gameManager.checkBuyRoad(checkee) && gameManager.checkRoadEligibility(checker, pNew.getColor());
        if (isValid){
            gameManager.placeRoad(checkee, checker);
        }
        pNew.setValid(isValid);
        return pNew;
    }

    @SendTo("/topic/playerIncrement")
    public void setupPayout(){
        gameManager.setupPayout();
        showPlayerIncrement();
    }

    @MessageMapping("/rolldice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){
        gameManager.rollDice(pDice.getYellow(), pDice.getRed(), pDice.getEvent());
        return pDice;
    }

    //TODO @MessageMapping(" ")
    @SendTo("/topic/playerIncrement")
    public PlayerIncrement showPlayerIncrement(){
        PlayerIncrement increment = new PlayerIncrement();
        setPlayerIncrement(increment);
        return increment;
    }

    private void setPlayerIncrement(PlayerIncrement pIncrement){
        for (String pUsername : currPlayerList){
            for (Player player : gameManager.getGame().getPlayers()) {
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
                                    player.getResourceCards().get(StealableCard.Resource.WHEAT),
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
                                    player.getResourceCards().get(StealableCard.Resource.WHEAT),
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
                                    player.getResourceCards().get(StealableCard.Resource.WHEAT),
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
                                    player.getResourceCards().get(StealableCard.Resource.WHEAT),
                                    player.getCommodityCards().get(StealableCard.Commodity.COIN),
                                    player.getCommodityCards().get(StealableCard.Commodity.CLOTH),
                                    player.getCommodityCards().get(StealableCard.Commodity.PAPER));
                    }
                }
            }
        }
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
        gameManager.getGame().getBoard().getEdges().put(aEdge.getId(),aEdge);

    }

    @MessageMapping("/hex")
    public void getHex(String bigJson) throws Exception{

        JSONArray aArray = new JSONArray(bigJson);
        Gson gson = new Gson();

        for(int i=0;i<aArray.length();i++) {
            JSONObject jsonHex = aArray.getJSONObject(i);

            ViewHex pHex = gson.fromJson(jsonHex.toString(), ViewHex.class);

            switch (pHex.getTerrainType()) {
                case "wood":
                    LandHex aHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Forest);
                    gameManager.getGame().getBoard().getHexes().put(aHex.getId(), aHex);
                    gameManager.getGame().getBoard().getLandHexes().get(aHex.getProductionNumber()).add(aHex);
                    break;
                case "ore":
                    LandHex bHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Mountains);
                    gameManager.getGame().getBoard().getHexes().put(bHex.getId(), bHex);
                    gameManager.getGame().getBoard().getLandHexes().get(bHex.getProductionNumber()).add(bHex);
                    break;
                case "brick":
                    LandHex cHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Hills);
                    gameManager.getGame().getBoard().getHexes().put(cHex.getId(), cHex);
                    gameManager.getGame().getBoard().getLandHexes().get(cHex.getProductionNumber()).add(cHex);
                    break;
                case "sheep":
                    LandHex dHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Pasture);
                    gameManager.getGame().getBoard().getHexes().put(dHex.getId(), dHex);
                    gameManager.getGame().getBoard().getLandHexes().get(dHex.getProductionNumber()).add(dHex);
                    break;
                case "gold":
                    LandHex eHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.GoldMine);
                    gameManager.getGame().getBoard().getHexes().put(eHex.getId(), eHex);
                    gameManager.getGame().getBoard().getLandHexes().get(eHex.getProductionNumber()).add(eHex);
                    break;
                case "wheat":
                    LandHex fHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Fields);
                    gameManager.getGame().getBoard().getHexes().put(fHex.getId(), fHex);
                    gameManager.getGame().getBoard().getLandHexes().get(fHex.getProductionNumber()).add(fHex);
                    break;
                case "desert":
                    LandHex gHex = new LandHex(pHex.getId(), pHex.getNumber(), TerrainType.Desert);
                    gameManager.getGame().getBoard().getHexes().put(gHex.getId(), gHex);
                    gameManager.getGame().getBoard().getLandHexes().get(gHex.getProductionNumber()).add(gHex);
                    break;
                case "sea":
                    SeaHex hHex = new SeaHex(pHex.getId());
                default:
                     hHex = new SeaHex(pHex.getId());
            }

        }

    }

    @MessageMapping("/intersection")
    public void getIntersection(ViewIntersection pIntersection) throws Exception{

       // System.out.println("Intersection");
        Intersection aIntersection = new Intersection(pIntersection.getId(), HarbourType.None);
        gameManager.getGame().getBoard().getIntersections().put(aIntersection.getId(),aIntersection);
    }

    @MessageMapping("/setNeighbours")
    public void setNeighbours() throws Exception
    {
        gameManager.getGame().getBoard().setAllNeighbours();
        //gameManager.saveGame();

    }
}