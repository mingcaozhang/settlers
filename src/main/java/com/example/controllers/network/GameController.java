package com.example.controllers.network;

import com.example.models.gameModels.Game;
import com.example.models.gameModels.GameManager;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class GameController {

    private boolean gamecreated = false;
    private int currTurn = 1;

    @RequestMapping(value="/game", method= RequestMethod.GET)
    public String game(Map<String, Object> model) {

        return "game";
    }

    public ArrayList<String> playerList = new ArrayList<String>();

    @MessageMapping("/gameinit")
    @SendTo("/topic/game")
    public void gameInit() throws Exception{
        //Thread.sleep(1000); //simulated delay
        //return (playerList);

    }






}