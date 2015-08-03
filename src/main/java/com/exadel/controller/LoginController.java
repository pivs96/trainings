package com.exadel.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.security.Principal;
import java.util.Collection;

@RestController
public class LoginController {
    private final Logger log = LoggerFactory.getLogger(LoginController.class);
    @RequestMapping(value="/loguser", method = RequestMethod.GET)
    Principal user(Principal user) { return user; }

    @RequestMapping( value = "/logout", method = RequestMethod.GET )
    public String homeGret()
    {
        return "Welcome home ";
    }

}

