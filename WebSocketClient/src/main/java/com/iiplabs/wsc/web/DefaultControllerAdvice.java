package com.iiplabs.wsc.web;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.iiplabs.wsc.annotation.DefaultControllerAnnotation;

@ControllerAdvice(annotations={DefaultControllerAnnotation.class})
public class DefaultControllerAdvice {

	private static final String CURRENT_LANGUAGE = "lang";
	
    @ModelAttribute(CURRENT_LANGUAGE)
    public String getCurrentLocale() {
    	return LocaleContextHolder.getLocale().getLanguage();
    }
    
}
