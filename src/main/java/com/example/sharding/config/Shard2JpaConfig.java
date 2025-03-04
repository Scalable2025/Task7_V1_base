package com.example.sharding.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.example.sharding.repository.UserRepository2;

@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.sharding.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserRepository2.class),

        entityManagerFactoryRef = "shard2EntityManagerFactory",
        transactionManagerRef = "shard2TransactionManager"
)
public class Shard2JpaConfig {
}
