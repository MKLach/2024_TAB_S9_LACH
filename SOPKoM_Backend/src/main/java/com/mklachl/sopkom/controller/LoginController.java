package com.mklachl.sopkom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class LoginController {

  
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    
    @GetMapping("/home")
    public String getLogoutPage(){
        return "home";
    }
}
