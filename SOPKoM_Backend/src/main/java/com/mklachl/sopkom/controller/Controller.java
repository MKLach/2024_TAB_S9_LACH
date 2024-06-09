package com.mklachl.sopkom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Kontroler ogólny obsługujący różne żądania GET.
 */
@org.springframework.stereotype.Controller
public class Controller {

    /**
     * Endpoint zwracający wiadomość dla nieautoryzowanych użytkowników.
     * @return Wiadomość tekstowa.
     */
    @GetMapping(path = "/not_auth", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<String> getString() {
        return new ResponseEntity<>("hello not auth", HttpStatus.OK);
    }

    /**
     * Endpoint zwracający wiadomość tylko dla autoryzowanych użytkowników.
     * @return Wiadomość tekstowa.
     */
    @GetMapping(path = "/get2")
    public ResponseEntity<String> getString2() {
        return new ResponseEntity<>("authenticated only", HttpStatus.OK);
    }
}
