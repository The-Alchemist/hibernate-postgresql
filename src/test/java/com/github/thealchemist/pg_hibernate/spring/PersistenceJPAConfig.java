package com.github.thealchemist.pg_hibernate.spring;


import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;

/**
 * Inspiration from http://www.baeldung.com/2011/12/13/the-persistence-layer-with-spring-3-1-and-jpa/#javaconfig
 *
 * @author kpietrzak
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackageClasses=Marker.class)
@PropertySource("classpath:/database.properties")
public class PersistenceJPAConfig {

    @Inject
    private Environment env;

    private String driverClassName;
    private String username;
    private String password;
    private String url;

    @PostConstruct
    private void initDatabaseProperties() {
        driverClassName = env.getProperty("database.driverClassName");
        username = env.getProperty("database.username");
        password = env.getProperty("database.password");
        url = env.getProperty("database.url");
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { Marker.class.getPackage().getName() });

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();


		dataSource.setDriverClassName(driverClassName);
        dataSource.setUrl(url);

        dataSource.setUsername(username);
        dataSource.setPassword(password);

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert() {
        return new SimpleJdbcInsert(dataSource());
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }

    Properties additionalProperties() {
        Properties properties = new Properties();
        ImmutableSet<String> keys = ImmutableSet.of("hibernate.hbm2ddl.auto", "hibernate.dialect", "hibernate.show_sql", "hibernate.format_sql");
        for(String key : keys) {
            properties.setProperty(key, env.getProperty(key));
        }
        return properties;
    }
}