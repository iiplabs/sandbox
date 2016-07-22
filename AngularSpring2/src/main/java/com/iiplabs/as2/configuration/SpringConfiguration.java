package com.iiplabs.as2.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages={"com.iiplabs.as2.service", 
		"com.iiplabs.as2.shared"})
@Import({SpringConfigurationMessages.class})
public class SpringConfiguration {

	public SpringConfiguration() {}
	    
}
