package com.example.controllers.network;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

@Controller
public class GameController {
    @RequestMapping(value="/game", method= RequestMethod.GET)
    public String game(Map<String, Object> model) {

        return "game";
    }

}