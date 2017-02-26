package com.example.controllers.network;

import java.util.Map;

import com.example.forms.LoginForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {


    private String message = "MAxi";

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String welcome(Map<String, Object> model) {
        model.put("message", this.message);
        return "welcome";
    }

    @RequestMapping(value="/", method=RequestMethod.POST)
    public String login(LoginForm pLoginForm, Map<String, Object> model)
    {
        model.put("message", pLoginForm.getUsername());
        return "welcome";
    }

}