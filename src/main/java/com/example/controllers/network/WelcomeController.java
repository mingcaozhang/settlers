package com.example.controllers.network;

import java.util.Map;

import com.example.forms.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.repositories.UserRepository;
import com.example.models.networkModels.User;

import javax.validation.Valid;


@Controller
public class WelcomeController {

    @RequestMapping(value="/", method=RequestMethod.GET)
    public String welcome(Login login) {

        return "home";
    }



}