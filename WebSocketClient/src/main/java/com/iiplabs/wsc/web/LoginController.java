package com.iiplabs.wsc.web;

import java.util.Date;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iiplabs.wsc.annotation.DefaultControllerAnnotation;
import com.iiplabs.wsc.dto.LoginForm;

@Controller
@DefaultControllerAnnotation
public class LoginController {

	private static final String LOGIN_FORM_ATTRIBUTE_NAME = "loginForm";
	
	@RequestMapping(value={"/login.jsp", "/login"}, method=RequestMethod.GET)
	public void displayLogin(ModelMap model) {
		model.addAttribute(LOGIN_FORM_ATTRIBUTE_NAME, new LoginForm());
	}

    @MessageMapping("/do-login" )
    @SendTo("/topic/do-login-response")
    public String addNum(LoginForm loginForm) throws Exception {
        return new Date().toString();
    }
	
}
