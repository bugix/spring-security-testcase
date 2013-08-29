package com.acme.testcase.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.acme.testcase.model.User;
import com.acme.testcase.repository.UserRepository;
import com.acme.testcase.service.UserService;

@Configuration
@EnableJpaRepositories(basePackageClasses = { UserRepository.class })
@EnableTransactionManagement
@ComponentScan(basePackageClasses = { EntityLoader.class, UserService.class })
public class ApplicationConfig {

	@Bean
	public DataSource dataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUrl("jdbc:h2:~/testcase");
		dataSource.setUsername("sa");
		dataSource.setPassword("");

		return dataSource;
	}

	@Bean(name = "entityManagerFactory")
	public EntityManagerFactory entityManagerFactory() {
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.H2);

		LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();

		Map<String, String> jpaPropertyMap = new HashMap<String, String>();
		jpaPropertyMap.put("hibernate.hbm2ddl.auto", "create");
		jpaPropertyMap.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");

		factory.setJpaPropertyMap(jpaPropertyMap);
		factory.setJpaVendorAdapter(vendorAdapter);
		factory.setPackagesToScan(User.class.getPackage().getName());
		factory.setDataSource(dataSource());

		factory.afterPropertiesSet();

		return factory.getObject();
	}

	@Bean
	public JpaDialect jpaDialect() {
		return new HibernateJpaDialect();
	}

	@Bean
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}
}