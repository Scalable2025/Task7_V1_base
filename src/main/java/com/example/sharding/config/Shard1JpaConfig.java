package com.example.sharding.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


import com.example.sharding.repository.UserRepository1;


@Configuration
@EnableJpaRepositories(
        basePackages = "com.example.sharding.repository",
        includeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = UserRepository1.class),

        entityManagerFactoryRef = "shard1EntityManagerFactory",
        transactionManagerRef = "shard1TransactionManager"
)
public class Shard1JpaConfig {
}
