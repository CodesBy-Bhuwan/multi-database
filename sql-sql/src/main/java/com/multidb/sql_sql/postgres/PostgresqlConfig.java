package com.multidb.sql_sql.postgres;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;

import org.springframework.boot.jdbc.autoconfigure.DataSourceProperties;
import org.springframework.boot.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.*;
import org.springframework.transaction.PlatformTransactionManager;

    @Configuration
    @EnableJpaRepositories(
            basePackages = "com.multidb.sql_sql.postgres",
            entityManagerFactoryRef = "postgresEntityManagerFactory",
            transactionManagerRef = "postgresTransactionManager"
    )
    public class PostgresqlConfig {

        @Bean
        @ConfigurationProperties("spring.datasource.postgres")
        public DataSourceProperties postgresDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean
        public DataSource postgresDataSource() {
            return postgresDataSourceProperties()
                    .initializeDataSourceBuilder()
                    .build();
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory(
                EntityManagerFactoryBuilder builder) {

            return builder
                    .dataSource(postgresDataSource())
                    .packages("com.example.multidb.postgres.entity")
                    .persistenceUnit("postgres")
                    .build();
        }

        @Bean
        public PlatformTransactionManager postgresTransactionManager(
                @Qualifier("postgresEntityManagerFactory")
                LocalContainerEntityManagerFactoryBean factory) {

            return new JpaTransactionManager(factory.getObject());
        }
    }
