package com.example.controllers.network;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class GameRoomController {
    @RequestMapping(value="/gameroom", method= RequestMethod.GET)
    public String gameroom(Map<String, Object> model) {

        return "gameroom";
    }

}