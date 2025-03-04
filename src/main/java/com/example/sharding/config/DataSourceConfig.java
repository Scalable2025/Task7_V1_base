package com.example.sharding.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
@Configuration
@EnableTransactionManagement

public class DataSourceConfig {

    @Primary
    @Bean
    @ConfigurationProperties("spring.datasource.shard1")
    public DataSourceProperties shard1DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Primary
    @Bean(name = "shard1DataSource")
    public DataSource shard1DataSource() {
        return shard1DataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Primary
    @Bean(name = "shard1EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean shard1EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("shard1DataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.sharding.model") // Ensure correct package
                .persistenceUnit("shard1")
                .build();
    }

    @Primary
    @Bean(name = "shard1TransactionManager")
    public PlatformTransactionManager shard1TransactionManager(
            @Qualifier("shard1EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    // Second Shard Configuration
    @Bean
    @ConfigurationProperties("spring.datasource.shard2")
    public DataSourceProperties shard2DataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean(name = "shard2DataSource")
    public DataSource shard2DataSource() {
        return shard2DataSourceProperties()
                .initializeDataSourceBuilder()
                .type(HikariDataSource.class)
                .build();
    }

    @Bean(name = "shard2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean shard2EntityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("shard2DataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.example.sharding.model") // Ensure correct package
                .persistenceUnit("shard2")
                .build();
    }

    @Bean(name = "shard2TransactionManager")
    public PlatformTransactionManager shard2TransactionManager(
            @Qualifier("shard2EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }
}
