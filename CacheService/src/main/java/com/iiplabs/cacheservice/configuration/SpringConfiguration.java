package com.iiplabs.cacheservice.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages={"com.iiplabs.cacheservice.service", 
		"com.iiplabs.cacheservice.web"})
@Import({SpringConfigurationWebService.class})
public class SpringConfiguration {

	public SpringConfiguration() {}
	    
}
