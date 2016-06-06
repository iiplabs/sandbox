package com.iiplabs.wsc.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.engine.transaction.jta.platform.internal.JBossAppServerJtaPlatform;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.support.lob.DefaultLobHandler;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@DependsOn("transactionManager")
@EnableJpaRepositories(basePackages={"com.iiplabs.wsc.repository"}, 
entityManagerFactoryRef="entityManagerFactory", transactionManagerRef="transactionManager")
public class SpringConfigurationData {
	
	private static Logger logger = Logger.getLogger(SpringConfigurationData.class);

	public SpringConfigurationData() {
		super();
	}
	
    @Bean 
    public PersistenceExceptionTranslationPostProcessor translationPostProcessor() {
    	return new PersistenceExceptionTranslationPostProcessor();
    }
    
    @Bean 
    public DefaultLobHandler lobHandler() {
    	return new DefaultLobHandler();
    }
    
	@Bean
    public DataSource dataSource() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		EmbeddedDatabase embeddedDatabase = builder
				.setType(EmbeddedDatabaseType.H2)
				.setName("wscdb")
				.addScript("sql/create-db.sql")
				.addScript("sql/insert-data.sql")
				.build();
		return embeddedDatabase;
	}

	@Bean
	public HibernateJpaVendorAdapter hibernateJpaVendorAdapter() {
		HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
		adapter.setGenerateDdl(false);
		adapter.setShowSql(false);
		return adapter;
	}
	
    @Bean
    public EntityManagerFactory entityManagerFactory() {
        final LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
        bean.setJpaVendorAdapter(hibernateJpaVendorAdapter());
        bean.setJtaDataSource(dataSource());
        bean.setJpaProperties(getJpaProperties());
        bean.setPersistenceUnitName("hibernate_h2");
        bean.setPackagesToScan("com.iiplabs.wsc.model.dao");
        bean.afterPropertiesSet();
        return bean.getObject();
    }
	
    private Properties getJpaProperties() {
        final Properties props = new Properties();
        props.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        props.setProperty("javax.persistence.transactionType", "JTA");
        props.setProperty("hibernate.transaction.jta.platform", JBossAppServerJtaPlatform.class.getName());
        props.setProperty("hibernate.hbm2ddl.auto", "validate");
        return props;
    }
    
}
