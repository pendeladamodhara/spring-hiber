package com.exmale.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class ConfigurationClass {
	
	  @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan("com");
	        Properties hibernateProperties = new Properties();
	        hibernateProperties.put("hibernate.dialect","org.hibernate.dialect.Oracle10gDialect");
	        hibernateProperties.put("hibernate.show_sql","true");
	        hibernateProperties.put("hibernate.hbm2ddl.auto","update");
	        sessionFactory.setHibernateProperties(hibernateProperties);
	        return sessionFactory;
	    }
	  @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
	        dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
	        dataSource.setUsername("system");
	        dataSource.setPassword("manager");
	        return dataSource;
	    }
	  @Bean
	    public HibernateTransactionManager transactionManager() {
	        HibernateTransactionManager txManager = new HibernateTransactionManager();
	        txManager.setSessionFactory(sessionFactory().getObject());
	        return txManager;
	    }
	

}
