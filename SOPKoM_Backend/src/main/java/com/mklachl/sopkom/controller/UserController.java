package com.mklachl.sopkom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mklachl.sopkom.helpers.Helpers;
import com.mklachl.sopkom.model.dto.UserDto;
import com.mklachl.sopkom.model.entity.User;
import com.mklachl.sopkom.services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = {"*"}, maxAge = 3600)
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * Endpoint do pobrania danych zalogowanego użytkownika.
     * @return UserDto z danymi użytkownika
     */
    @GetMapping
    public UserDto getLoggedInUser() {
        User user = Helpers.getUserFromContext(userService);
        
        if (user == null) {
            throw new NullPointerException("critical exception!!!");
        }

        return new UserDto(user);
    }
}
