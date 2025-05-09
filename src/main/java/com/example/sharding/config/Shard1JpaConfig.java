package com.example.sharding.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.sharding.repository.UserRepository1;

import jakarta.persistence.EntityManagerFactory;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.sharding.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserRepository1.class),
        entityManagerFactoryRef = "shard1EntityManagerFactory",
        transactionManagerRef = "shard1TransactionManager"

)
public class Shard1JpaConfig {
        @Primary
        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.shard1")
        public DataSourceProperties shard1DataSourceProperties() {
            return new DataSourceProperties();
        }
        @Primary
        @Bean
        public DataSource shard1DataSource() {
            return shard1DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
        }
        @Primary
        @Bean
        public LocalContainerEntityManagerFactoryBean shard1EntityManagerFactory(
                EntityManagerFactoryBuilder builder) {
            return builder
                .dataSource(shard1DataSource())
                .packages("com.example.sharding.model.user_name") // user entity package
                .persistenceUnit("shard1")
                .build();
        }
        @Primary
        @Bean
        public PlatformTransactionManager shard1TransactionManager(
                @Qualifier("shard1EntityManagerFactory") EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }
    }
