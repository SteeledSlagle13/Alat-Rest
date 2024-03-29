package com.apexlegendsat.springmvc.backend.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:apexlegends.properties" })
public class HibernateConfiguration {
	
	static Logger logger = LogManager.getLogger(HibernateConfiguration.class.getName());
	
	@Autowired
	private Environment environment;
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
		
		logger.info("Looking in the Session Factory Method!");
		
		sessionFactory.setDataSource(dataSource());
		sessionFactory.setPackagesToScan(new String[] { "com.apexlegendsat.springmvc.backend.entity" });
		sessionFactory.setHibernateProperties(hibernateProperties());
		
		logger.info("We got a session factory");
		
		return sessionFactory;
	}
	
	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
		dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
		dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
		dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
		
		logger.info("We have database properties!");
		logger.info("url {}" + environment.getRequiredProperty("jdbc.url"));
		logger.info("username {}" + environment.getRequiredProperty("jdbc.username"));
		logger.info("password {}" + environment.getRequiredProperty("jdbc.password"));
		
		return dataSource;
	}
	
	private Properties hibernateProperties() {
		Properties properties = new Properties();
		
		properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
		properties.put("hibernate.formate_sql", environment.getRequiredProperty("hibernate.format_sql"));
				
		return properties;
	}
	
	@Bean
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory s) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		
		txManager.setSessionFactory(s);
		
		return txManager;
	}

}
