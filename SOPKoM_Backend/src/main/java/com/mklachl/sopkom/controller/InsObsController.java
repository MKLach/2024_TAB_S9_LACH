package com.mklachl.sopkom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InsObsController {

	@GetMapping("/instrukcja")
	public String get() {
		return "insobs.htm";
	}
	
}
