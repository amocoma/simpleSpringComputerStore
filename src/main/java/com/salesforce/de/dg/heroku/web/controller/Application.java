package com.salesforce.de.dg.heroku.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value={"", "/"})

public class Application {
	@RequestMapping(method = RequestMethod.GET)
	public String printHello() {
		return "redirect:/computers/";
	}
}



