package com.mklachl.sopkom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * Endpoint do wyświetlania strony logowania.
     * @return Nazwa widoku strony logowania
     */
    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }
    
    /**
     * Endpoint do wyświetlania strony głównej po wylogowaniu.
     * @return Nazwa widoku strony głównej
     */
    @GetMapping("/home")
    public String getLogoutPage(){
        return "home";
    }
}
