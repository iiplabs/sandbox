package com.iiplabs.wsc.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iiplabs.wsc.annotation.DefaultControllerAnnotation;

@Controller
@DefaultControllerAnnotation
public class IndexController {

	public IndexController() {
		super();
	}
	
	@RequestMapping(value={"/redirect"}, method=RequestMethod.GET)
	public String redirect() {
		return "redirect:/login";
	}

}
