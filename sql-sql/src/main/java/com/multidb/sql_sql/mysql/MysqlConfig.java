package com.multidb.sql_sql.mysql;

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
            basePackages = "com.multidb.sql_sql.mysql",
            entityManagerFactoryRef = "mysqlEntityManagerFactory",
            transactionManagerRef = "mysqlTransactionManager"
    )
    public class MysqlConfig {

        @Bean
        @ConfigurationProperties("spring.datasource.mysql")
        public DataSourceProperties mysqlDataSourceProperties() {
            return new DataSourceProperties();
        }

        @Bean
        public DataSource mysqlDataSource() {
            return mysqlDataSourceProperties()
                    .initializeDataSourceBuilder()
                    .build();
        }

        @Bean
        public LocalContainerEntityManagerFactoryBean mysqlEntityManagerFactory(
                EntityManagerFactoryBuilder builder) {

            return builder
                    .dataSource(mysqlDataSource())
                    .packages("com.example.multidb.mysql.entity")
                    .persistenceUnit("mysql")
                    .build();
        }

        @Bean
        public PlatformTransactionManager mysqlTransactionManager(
                @Qualifier("mysqlEntityManagerFactory")
                LocalContainerEntityManagerFactoryBean factory) {

            return new JpaTransactionManager(factory.getObject());
        }
    }
