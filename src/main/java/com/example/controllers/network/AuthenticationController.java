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


import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;



/* Handles all user authentications */
@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/", method= RequestMethod.POST)
    public String login(Login login, Model model)
    {

        try {
            User loginUser = userRepository.getByUsername(login.getUsername());
            if(loginUser.comparePassword(login.getPassword()))
            {
                return "redirect:/lobby";
            }
        }
        catch (NullPointerException e)
        {
            login.setValidStatus(false);
            return "home";
        }

        login.setValidStatus(false);
        return "home";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String regpage(Register register, Model model)
    {

        return "register";
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public String register(@Valid Register register, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "register";
        }
        String confirm = register.getConfirm();
        User newUser = new User(register.getUsername(),register.getPassword());
        userRepository.save(newUser);

        model.addAttribute("message", register.getUsername());
        return "redirect:/";
    }

}


