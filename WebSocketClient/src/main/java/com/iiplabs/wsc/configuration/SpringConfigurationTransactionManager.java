package com.iiplabs.wsc.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.jta.JtaTransactionManager;

@Configuration
@EnableTransactionManagement
public class SpringConfigurationTransactionManager {

	public SpringConfigurationTransactionManager() {
		super();
	}
	
    @Bean
    public JtaTransactionManager transactionManager() {
    	JtaTransactionManager transactionManager = new JtaTransactionManager();
    	transactionManager.setAllowCustomIsolationLevels(true);
    	transactionManager.setDefaultTimeout(1200); // 20 minutes
        return transactionManager;
    }
    
}
