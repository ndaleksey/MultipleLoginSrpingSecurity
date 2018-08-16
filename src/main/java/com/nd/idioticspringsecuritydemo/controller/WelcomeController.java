package com.nd.idioticspringsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Shishkov A.V. on 16.08.18.
 */
@Controller
public class WelcomeController {
	@GetMapping(path = {"/", "/index"})
	public String showWelcomePage() {
		return "index";
	}
}
