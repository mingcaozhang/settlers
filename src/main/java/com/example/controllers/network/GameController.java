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

import javax.swing.text.View;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

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
                String color = gameManager.getGame().getPlayers().get(i).getaColor();
                model.addAttribute("myColor", color);
            }
        }

        for(int i = 0 ; i < currPlayerList.size(); i++){
            model.addAttribute("player"+(i+1),currPlayerList.get(i));
        }

        for(int i = 0 ; i < currPlayerList.size(); i++){
            model.addAttribute("player"+(i+1)+"_c", gameManager.getGame().getPlayers().get(i).getaColor());
        }
        aGame = gameManager.getGame();

        return "game";
    }


    @MessageMapping("/placesettlement")
    @SendTo("/topic/settlement")
    public ViewPiece placeSettlement(ViewPiece pNew, Principal caller){
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        System.out.println("Can buy: "+gameManager.checkBuySettlement(checkee));
        System.out.println("Can place: "+gameManager.checkSettlementPlaceEligibility(checker,pNew.getColor()));
        boolean isValid = gameManager.checkBuySettlement(checkee) && gameManager.checkSettlementPlaceEligibility(checker,pNew.getColor());
        if(isValid)
        {
            gameManager.paySettlement(checkee);
            gameManager.placeSettlement(checkee, checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @MessageMapping("/placecity")
    @SendTo("/topic/city")
    public ViewPiece placeCity(ViewPiece pNew, Principal caller){
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        boolean isValid = gameManager.checkBuyCity(checkee) && gameManager.checkCityPlaceEligibility(checker,pNew.getColor());
        if(isValid)
        {
            gameManager.payCity(checkee);
            gameManager.placeCity(checkee, checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @MessageMapping("/placeroad")
    @SendTo("/topic/road")
    public ViewPiece placeRoad(ViewPiece pNew, Principal caller){
        System.out.println("    Buying road");
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Edge checker = gameManager.getGame().getBoard().getEdges().get(pNew.getId());
        System.out.println("Can buy: "+gameManager.checkBuyRoad(checkee));
        System.out.println("Can place: "+gameManager.checkRoadEligibility(checker,pNew.getColor()));
        boolean isValid = gameManager.checkBuyRoad(checkee) && gameManager.checkRoadEligibility(checker,pNew.getColor());
        if(isValid)
        {
            gameManager.payRoad(checkee);
            gameManager.placeRoad(checkee, checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @MessageMapping("/placeship")
    @SendTo("/topic/ship")
    public ViewPiece placeShip(ViewPiece pNew, Principal caller){
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Edge checker = gameManager.getGame().getBoard().getEdges().get(pNew.getId());
        boolean isValid = gameManager.checkBuyShip(checkee) && gameManager.checkShipEligibility(checker,pNew.getColor());
        if(isValid)
        {
            gameManager.payShip(checkee);
            gameManager.placeShip(checkee, checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @MessageMapping("/placeknight")
    @SendTo("/topic/knight")
    public ViewPiece placeKnight(ViewPiece pNew, Principal caller){
        System.out.println(" Place Knight");
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        System.out.println("Buy knight : "+gameManager.checkBuyKnight(checkee));
        System.out.println("Place knight : "+gameManager.checkKnightPlaceEligibility(checker, pNew.getColor()));

        boolean isValid = gameManager.checkBuyKnight(checkee) && gameManager.checkKnightPlaceEligibility(checker, pNew.getColor());
        if (isValid){
            gameManager.payKnight(checkee);
            gameManager.placeKnight(checkee, checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @MessageMapping("/upgradeknight")
    @SendTo("/topic/upgradeknight")
    public ViewPiece upgradeKnight(ViewPiece pNew, Principal caller){
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        System.out.println("Buy knight : "+gameManager.checkBuyKnight(checkee));
        System.out.println("Place knight : "+gameManager.checkUpgradeKnightEligibility(checker, pNew.getColor()));
        boolean isValid = gameManager.checkBuyKnight(checkee) && gameManager.checkUpgradeKnightEligibility(checker, pNew.getColor());
        if (isValid){
            gameManager.payKnight(checkee);
            gameManager.upgradeKnight(checkee, checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @MessageMapping("/activateknight")
    @SendTo("/topic/activateknight")
    public ViewPiece activateKnight(ViewPiece pNew, Principal caller){
        System.out.println("activate knight");
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        boolean isValid = gameManager.checkActivateEligibility(checker, pNew.getColor()) && gameManager.checkBuyActivation(checkee);
        System.out.println("Activate Pay : "+ gameManager.checkBuyActivation(checkee));
        System.out.println("Activate Eligible : "+ gameManager.checkActivateEligibility(checker, pNew.getColor()));
        if (isValid){
            gameManager.payActivation(checkee);
            gameManager.activateKnight(checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @SendTo("/topic/robber")
    public boolean moveRobber(){
        return true;
    }

    @MessageMapping("/placerobber")
    @SendTo("/topic/robber")
    public ViewRobber placeRobber(ViewRobber pRobber){
        Hex hex = gameManager.getGame().getBoard().getHexes().get(pRobber.getHexId());
        boolean isValid = (hex.getTerrainType() != TerrainType.Sea);
        boolean hasStealable = false;
        for (Intersection neighbour : hex.getIntersectionNeighbours()){
            if (neighbour.getBuilding() != null){
                Player owner = neighbour.getBuilding().getOwner();
                if (owner.getaStealableCardAmount() > 0) {
                    hasStealable = true;
                    break;
                }
            }
        }
        pRobber.setValid(isValid);
        pRobber.setHasStealable(hasStealable);
        return pRobber;
    }

    @MessageMapping("/stealresource")
    @SendTo("/topic/steal")
    public ViewSteal stealResource(ViewSteal pSteal, Principal caller){
        Intersection intersection = gameManager.getGame().getBoard().getIntersections().get(pSteal.getIntersectionID());
        Player stealer = gameManager.getPlayerFromString(caller.getName());
        boolean isValid = intersection.getBuilding().getOwner() != null && intersection.getBuilding().getOwner().getaStealableCardAmount() > 0;
        if (isValid){
            Player victim = intersection.getBuilding().getOwner();
            StealableCard.Resource aResource = null;
            StealableCard.Commodity aCommodity = null;
            boolean hasResource = false;
            boolean hasCommodity = false;
            for (StealableCard.Resource resource : victim.getaResourceCards().keySet()){
                if (victim.getaResourceCards().get(resource) > 0){
                    hasResource = true;
                    break;
                }
            }
            for (StealableCard.Commodity commodity : victim.getaCommodityCards().keySet()){
                if (victim.getaCommodityCards().get(commodity) > 0){
                    hasCommodity = true;
                    break;
                }
            }

            do{
                Random random = new Random();
                int rng = random.nextBoolean() ? 1 : 2;
                switch(rng) {
                    case 1:
                        if (hasResource) {
                        aResource = StealableCard.Resource.values()[(int) Math.random() * StealableCard.Resource.values().length];
                            if (victim.getaResourceCards().get(aResource) > 0) {
                                break;
                            }
                        }
                    case 2:
                        if (hasCommodity) {
                            aCommodity = StealableCard.Commodity.values()[(int) Math.random() * StealableCard.Commodity.values().length];
                            if (victim.getaCommodityCards().get(aCommodity) > 0){
                                break;
                            }
                        }
                }
            }while(aResource == null || aCommodity == null);
            if (aResource != null){
                victim.removeResource(aResource,1);
                stealer.addResource(aResource, 1);
            }
            else if (aCommodity != null){
                victim.removeCommodity(aCommodity,1);
                stealer.addCommodity(aCommodity, 1);
            }
        }
        pSteal.setValid(isValid);
        return pSteal;
    }

    @SendTo("/topic/barbarian")
    public ViewBarbarian barbarianResult(ViewBarbarian pView){
        return pView;
    }

    @MessageMapping("/barbarianselectedtype")
    public void barbarianSelectedType(ProgSelectedType pType){
        gameManager.executeBarbarianTiedVictory(pType);
    }

    @MessageMapping("/barbarianselectedcity")
    @SendTo("/topic/downgrade")
    public ViewRemoveCity barbarianSelectedCity(ViewRemoveCity pView, Principal caller){
        Player player = gameManager.getPlayerFromString(caller.getName());
        Intersection intersection = gameManager.getGame().getBoard().getIntersections().get(pView.getId());
        boolean isValid = intersection.getBuilding() != null && intersection.getBuilding().isCity();
        if(isValid)
        {
            intersection.removeBuilding();
            if (player.canGetBuilding(Unit.Building.SETTLEMENT)){
                intersection.setBuilding(player.removeBuilding(Unit.Building.SETTLEMENT));
            }
            player.addBuilding(Unit.Building.CITY);
        }
        pView.setSettlement(isValid);

        return pView;
    }

    // SETUP IS FIRST 2 TURNS

    @MessageMapping("/setupsettlement")
    @SendTo("/topic/settlement")
    public ViewPiece setupSettlement(ViewPiece pNew, Principal caller){
        System.out.println("Check settlement");
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        boolean isValid = gameManager.checkIntersectionSetupEligibility(checker);
        if(isValid) {
            gameManager.placeSettlement(checkee, checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @MessageMapping("/setupcity")
    @SendTo("/topic/city")
    public ViewPiece setupCity(ViewPiece pNew, Principal caller){
        System.out.println("Check city");
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Intersection checker = gameManager.getGame().getBoard().getIntersections().get(pNew.getId());
        boolean isValid = gameManager.checkIntersectionSetupEligibility(checker);
        if(isValid) {
            gameManager.placeCity(checkee, checker);
        }
        pNew.setIsValid(isValid);
        return pNew;
    }

    @MessageMapping("/setuproad")
    @SendTo("/topic/road")
    public ViewPiece setupRoad(ViewPiece pNew, Principal caller) {
        //System.out.println("----  "+pNew.getId());
        Player checkee = gameManager.getPlayerFromString(caller.getName());
        Edge checker = gameManager.getGame().getBoard().getEdges().get(pNew.getId());
        boolean isValid = gameManager.checkRoadEligibility(checker, pNew.getColor());
        if (checkee.getaBuildings().get(Unit.Building.CITY) == 3){
            boolean isCity = false;
            for (Intersection neighbour : checker.getIntersectionNeighbours()){
                if (neighbour.getBuilding()!= null && neighbour.getBuilding().isCity()){
                    isCity = true;
                    break;
                }
            }
            isValid = isValid && isCity;
        }
        if (isValid){
            //System.out.println("VALID");
            gameManager.placeRoad(checkee, checker);
        }
        //System.out.println("5");
        pNew.setIsValid(isValid);
        //System.out.println("returning");
        return pNew;
    }

    @MessageMapping("/setupDone")
    @SendTo("/topic/setupDone")
    public boolean setupPayout(){
        gameManager.setupPayout();
        return true;
    }

    @MessageMapping("/rolldice")
    @SendTo("/topic/dice")
    public DiceRoll showDice(DiceRoll pDice){
        gameManager.rollDice(pDice.getYellow(), pDice.getRed(), pDice.getEvent());
        return pDice;
    }

    @MessageMapping("/getResources")
    @SendTo("/topic/playerIncrement")
    public PlayerIncrement showPlayerIncrement(){
        PlayerIncrement increment = new PlayerIncrement();
        setPlayerIncrement(increment);
        return increment;
    }

    private void setPlayerIncrement(PlayerIncrement pIncrement){
        for (String pUsername : currPlayerList){
            Player player = gameManager.getPlayerFromString(pUsername);
            int index = currPlayerList.indexOf(player.getaUsername());
            switch (index) {
                case 0:
                    pIncrement.setp1(
                            player.getaGold(),
                            player.getaResourceCards().get(StealableCard.Resource.ORE),
                            player.getaResourceCards().get(StealableCard.Resource.BRICK),
                            player.getaResourceCards().get(StealableCard.Resource.WOOD),
                            player.getaResourceCards().get(StealableCard.Resource.SHEEP),
                            player.getaResourceCards().get(StealableCard.Resource.WHEAT),
                            player.getaCommodityCards().get(StealableCard.Commodity.COIN),
                            player.getaCommodityCards().get(StealableCard.Commodity.CLOTH),
                            player.getaCommodityCards().get(StealableCard.Commodity.PAPER));
                    continue;
                case 1:
                    pIncrement.setp2(
                            player.getaGold(),
                            player.getaResourceCards().get(StealableCard.Resource.ORE),
                            player.getaResourceCards().get(StealableCard.Resource.BRICK),
                            player.getaResourceCards().get(StealableCard.Resource.WOOD),
                            player.getaResourceCards().get(StealableCard.Resource.SHEEP),
                            player.getaResourceCards().get(StealableCard.Resource.WHEAT),
                            player.getaCommodityCards().get(StealableCard.Commodity.COIN),
                            player.getaCommodityCards().get(StealableCard.Commodity.CLOTH),
                            player.getaCommodityCards().get(StealableCard.Commodity.PAPER));
                    continue;
                case 2:
                    pIncrement.setp3(
                            player.getaGold(),
                            player.getaResourceCards().get(StealableCard.Resource.ORE),
                            player.getaResourceCards().get(StealableCard.Resource.BRICK),
                            player.getaResourceCards().get(StealableCard.Resource.WOOD),
                            player.getaResourceCards().get(StealableCard.Resource.SHEEP),
                            player.getaResourceCards().get(StealableCard.Resource.WHEAT),
                            player.getaCommodityCards().get(StealableCard.Commodity.COIN),
                            player.getaCommodityCards().get(StealableCard.Commodity.CLOTH),
                            player.getaCommodityCards().get(StealableCard.Commodity.PAPER));
                    continue;
                case 3:
                    pIncrement.setp4(
                            player.getaGold(),
                            player.getaResourceCards().get(StealableCard.Resource.ORE),
                            player.getaResourceCards().get(StealableCard.Resource.BRICK),
                            player.getaResourceCards().get(StealableCard.Resource.WOOD),
                            player.getaResourceCards().get(StealableCard.Resource.SHEEP),
                            player.getaResourceCards().get(StealableCard.Resource.WHEAT),
                            player.getaCommodityCards().get(StealableCard.Commodity.COIN),
                            player.getaCommodityCards().get(StealableCard.Commodity.CLOTH),
                            player.getaCommodityCards().get(StealableCard.Commodity.PAPER));
                    continue;
                }
            }
    }

    @MessageMapping("/traderequest")
    @SendTo("/topic/traderequest")
    public ViewPlayerTrade tradeRequest(ViewPlayerTrade pTrade, Principal caller){
        return pTrade;
    }

    @MessageMapping("/playertrade")
    @SendTo("/topic/playertrade")
    public ViewPlayerTrade playerTrade(ViewPlayerTrade pTrade, Principal caller){
        PlayerTrade aPlayerTrade = pTrade.toPlayerTrade();
        boolean isValid = gameManager.checkPlayerTradeEligibility(aPlayerTrade);
        if (isValid){
            aPlayerTrade.execute();
        }
        pTrade.setValid(isValid);
        return pTrade;
    }


    @MessageMapping("/maritimetrade")
    @SendTo("/topic/maritimetrade")
    public ViewMaritimeTrade maritimeTrade(ViewMaritimeTrade pTrade, Principal caller){
        MaritimeTrade aMaritimeTrade = pTrade.toMaritimeTrade();
        boolean isValid = gameManager.checkMaritimeTradeEligibility(aMaritimeTrade);
        if (isValid){
            aMaritimeTrade.execute();
        }
        pTrade.setValid(isValid);
        return pTrade;
    }

    @MessageMapping("/cityimprovement")
    @SendTo("/topic/cityimprovement")
    public ViewCityImprovement upgradeCity(ViewCityImprovement pView, Principal caller){
        Player player = gameManager.getPlayerFromString(caller.getName());
        StealableCard.Commodity aType = null;
        for (StealableCard.Commodity c : StealableCard.Commodity.values()){
            if (pView.getaType().equals(c.toString())){
                aType = c;
            }
        }
        boolean isValid = gameManager.buyCityImprovementEligibility(player ,aType);
        if (isValid){
            gameManager.buyCityImprovement(player, aType);
        }
        pView.setValid(isValid);
        return pView;
    }

    @MessageMapping("/endturn")
    @SendTo("/topic/turninfo")
    public PlayerAndPhase endTurn(Principal user){
        if(turnCounter == (currPlayerList.size()-1)){
            //System.out.println("first if");
            //System.out.println(currPlayerList.size()-1);
            Collections.reverse(currPlayerList);
            currPlayerTurn = 0;
            pap.setSetup1(false);
            pap.setSetup2(true);

        }else if(turnCounter == (2*(currPlayerList.size())-1)){
            //System.out.println("second if");
            //System.out.println(currPlayerList.size()-1);
            Collections.reverse(currPlayerList);
            currPlayerTurn = 0;
            pap.setSetup1(false);
            pap.setSetup2(false);

        }else{
            currPlayerTurn = (currPlayerTurn + 1)%currNumPlayers;
        }

        pap.setUsername(currPlayerList.get(currPlayerTurn));

        //System.out.println("Player's Turn "+ currPlayerList.get(currPlayerTurn));
        //System.out.println("turn count = "+currPlayerTurn);

        this.turnCounter++;
        for (String name : currPlayerList){
            System.out.println(name);
        }
        return pap;
    }


    @MessageMapping("/hex")
       public void getHex(String bigJson) throws Exception{

        JSONArray aArray = new JSONArray(bigJson);
        Gson gson = new Gson();

        for(int i=0;i<aArray.length();i++) {
            JSONObject jsonHex = aArray.getJSONObject(i);
            ViewHex pHex = gson.fromJson(jsonHex.toString(), ViewHex.class);
            gameManager.getGame().getBoard().setHex(pHex);
        }
        //System.out.println("DONE");
        gameManager.getGame().getBoard().makeEdges();
        gameManager.getGame().getBoard().makeIntersections();
        gameManager.getGame().getBoard().setAllNeighbours();
        //gameManager.saveGame();
    }

    @MessageMapping("/executeprogresscard")
    @SendTo("/progresscard")
    public ViewProgressCard executeCard(ViewProgressCard pView, Principal caller){
        String pType = pView.getaType();
        Player owner = gameManager.getPlayerFromString(caller.getName());
        boolean isValid = false;
        switch (pType){
            case "Politics":
                ProgressCard.Politics aPoliticsCard = gameManager.toPoliticsProgressCard(pView);
                isValid = gameManager.usePoliticsCardEligibility(aPoliticsCard, owner);
                if (isValid){
                    gameManager.usePoliticsCard(aPoliticsCard, owner);
                }
            case "Trade":
                ProgressCard.Trade aTradeCard = gameManager.toTradeProgressCard(pView);
                isValid = gameManager.useTradeCardEligibility(aTradeCard, owner);
                if (isValid){
                    gameManager.useTradeCard(aTradeCard, owner);
                }
            case "Science":
                ProgressCard.Science aScienceCard = gameManager.toScienceProgressCard(pView);
                isValid = gameManager.useScienceCardEligibility(aScienceCard, owner);
                if (isValid){
                    gameManager.useScienceCard(aScienceCard, owner);
                }
        }
        pView.setValid(isValid);
        return pView;
    }

    @MessageMapping("/showvictorypoints")
    @SendTo("/topic/victorypoints")
    public ViewVictoryPoints showVictoryPoints(){
        ViewVictoryPoints pNew = new ViewVictoryPoints();
        for (Player player : aGame.getPlayers()){
            switch (player.getaIndex()){
                case 0:
                    pNew.setP1points(player.getaVPs());
                case 1:
                    pNew.setP2points(player.getaVPs());
                case 2:
                    pNew.setP3points(player.getaVPs());
                case 3:
                    pNew.setP4points(player.getaVPs());
            }
        }
        pNew.setPointsToWin(aGame.getaVPsToWin());
        return pNew;
    }
}