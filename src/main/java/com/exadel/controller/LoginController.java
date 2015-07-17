package com.exadel.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;

@RestController
public class LoginController {

    @RequestMapping(value="/loguser", method = RequestMethod.GET)
    Principal user(Principal user)
    {
        return user;
    }

    @RequestMapping( value = "/logout", method = RequestMethod.GET )
    public String homeGret()
    {
        return "Welcome home ";
    }



}

