package com.example.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/")
public class WelcomeController {
	
	@RequestMapping(method=RequestMethod.GET)
	public String welcome() {
		return "welcome";
	}
}
