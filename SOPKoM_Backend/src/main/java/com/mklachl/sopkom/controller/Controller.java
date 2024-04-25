package com.mklachl.sopkom.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;


@org.springframework.stereotype.Controller
public class Controller {
	
	@GetMapping(path = "/not_auth", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> getString() {
		
		
		return new ResponseEntity<>("hello not auth", HttpStatus.OK);
	}
	
	@GetMapping(path = "/get2")
	public ResponseEntity<String> getString2() {
		
		
		return new ResponseEntity<>("authenticated only", HttpStatus.OK);
	}
	

}
