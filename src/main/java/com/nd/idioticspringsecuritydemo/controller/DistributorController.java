package com.nd.idioticspringsecuritydemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Shishkov A.V. on 16.08.18.
 */
@Controller
@RequestMapping("/distributor")
public class DistributorController {
	@GetMapping("/login")
	public String showLoginPane() {
		return "distributor/login";
	}

	@GetMapping("/main")
	public String showMainPage() {
		return "distributor/main";
	}
}
