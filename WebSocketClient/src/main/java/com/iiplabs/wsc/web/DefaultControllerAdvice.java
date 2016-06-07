package com.iiplabs.wsc.web;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.iiplabs.wsc.annotation.DefaultControllerAnnotation;

@ControllerAdvice(annotations={DefaultControllerAnnotation.class})
public class DefaultControllerAdvice {

	private static final String CURRENT_LANGUAGE = "lang";
	
    @ModelAttribute(CURRENT_LANGUAGE)
    public String getCurrentLocale() {
    	return LocaleContextHolder.getLocale().getLanguage();
    }
    
    @InitBinder
    public void binder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        binder.registerCustomEditor(Date.class, 
        		new CustomDateEditor(dateFormat, true));
    }

}
