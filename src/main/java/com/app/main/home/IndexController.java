package com.app.main.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {
	
	
	@GetMapping("/")
	public String getIndex() {
		return "index";
	}

}
