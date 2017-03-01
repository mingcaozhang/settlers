package com.example.controllers.network;

import java.util.Map;

import com.example.forms.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;


@Controller
public class WelcomeController {


    private String message = "MAxi";

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String welcome(Login login) {

        return "welcome";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String login(@Valid Login login, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "welcome";
        }
        model.addAttribute("message", login.getUsername());
        return "/register";
    }

}