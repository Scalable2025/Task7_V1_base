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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.sharding.repository.UserRepository2;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.sharding.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserRepository2.class),

        entityManagerFactoryRef = "shard2EntityManagerFactory",
        transactionManagerRef = "shard2TransactionManager"
)
public class Shard2JpaConfig {

        @Bean
        @ConfigurationProperties(prefix = "spring.datasource.shard2")
        public DataSourceProperties shard2DataSourceProperties() {
            return new DataSourceProperties();
        }
    
        @Bean
        public DataSource shard2DataSource() {
            return shard2DataSourceProperties()
                .initializeDataSourceBuilder()
                .build();
        }
    
        @Bean
        public LocalContainerEntityManagerFactoryBean shard2EntityManagerFactory(
                EntityManagerFactoryBuilder builder) {
            return builder
                .dataSource(shard2DataSource())
                .packages("com.example.sharding.model.user_details") // user entity package
                .persistenceUnit("shard2")
                .build();
        }
    
        @Bean
        public PlatformTransactionManager shard2TransactionManager(
                @Qualifier("shard2EntityManagerFactory") EntityManagerFactory emf) {
            return new JpaTransactionManager(emf);
        }
    }
