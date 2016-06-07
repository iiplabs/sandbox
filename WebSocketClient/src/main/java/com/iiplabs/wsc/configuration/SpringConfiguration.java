package com.iiplabs.wsc.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan(basePackages={"com.iiplabs.wsc.service", 
		"com.iiplabs.wsc.shared"})
@Import({SpringConfigurationData.class, 
	SpringConfigurationTransactionManager.class, 
	SpringConfigurationMessages.class})
public class SpringConfiguration {

	public SpringConfiguration() {}
	    
}
