package com.example.controllers.network;

import com.example.forms.Login;
import com.example.forms.Register;
import com.example.models.networkModels.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/* Handles all user authentications */
@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String login(@Valid Login login, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "home";
        }

        User newUser = new User(login.getUsername(),login.getPassword());
        userRepository.save(newUser);

        model.addAttribute("message", login.getUsername());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String regpage(Login login)
    {
        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@Valid Login login, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "home";
        }

        User newUser = new User(login.getUsername(),login.getPassword());
        userRepository.save(newUser);

        model.addAttribute("message", login.getUsername());
        return "home";
    }

}


