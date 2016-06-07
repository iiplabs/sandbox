package com.iiplabs.wsc.web;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iiplabs.wsc.annotation.DefaultControllerAnnotation;
import com.iiplabs.wsc.dto.BaseMessageDto;
import com.iiplabs.wsc.dto.LoginForm;
import com.iiplabs.wsc.service.IUserService;
import com.iiplabs.wsc.util.DtoFactory;

@Controller
@DefaultControllerAnnotation
public class LoginController {

	private static Logger logger = Logger.getLogger(LoginController.class);
	
	private static final String LOGIN_FORM_ATTRIBUTE_NAME = "loginForm";
	
	@Autowired
	private IUserService userService;
	
	@RequestMapping(value={"/login.jsp", "/login"}, method=RequestMethod.GET)
	public void displayLogin(ModelMap model) {
		model.addAttribute(LOGIN_FORM_ATTRIBUTE_NAME, new LoginForm());
	}

    @MessageMapping("/do-login" )
    @SendTo("/topic/do-login-response")
    public BaseMessageDto doLogin(LoginForm loginForm) throws Exception {
    	return userService.doLogin(DtoFactory.newAuthenticationRequestDto(loginForm));
    }
	
}
