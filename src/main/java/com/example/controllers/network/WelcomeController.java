package com.example.controllers.network;

import java.util.Map;

import com.example.forms.Login;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class WelcomeController {


    private String message = "MAxi";

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String welcome(Login login, Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String login(Login login, Map<String, Object> model)
    {
        model.put("message", login.getUsername());
        return "welcome";
    }

}