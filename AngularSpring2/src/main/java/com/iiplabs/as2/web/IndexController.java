package com.iiplabs.as2.web;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iiplabs.as2.annotation.DefaultControllerAnnotation;

@Controller
@DefaultControllerAnnotation
public class IndexController {

	private static Logger logger = Logger.getLogger(IndexController.class);
	
	public IndexController() {
		super();
	}
	
	@RequestMapping(value={"/redirect"}, method=RequestMethod.GET)
	public String redirect() {
		return "redirect:/index";
	}

	@RequestMapping(value={"/index.jsp", "/index"}, method=RequestMethod.GET)
	public void displayIndex() {
	}

}
