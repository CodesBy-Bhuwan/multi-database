package com.multidb.sql_sql.config;


import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

    @Configuration
    public class FlywayConfig {

        private final DataSource mysqlDataSource;
        private final DataSource postgresDataSource;

        public FlywayConfig(
                @Qualifier("mysqlDataSource") DataSource mysqlDataSource,
                @Qualifier("postgresDataSource") DataSource postgresDataSource) {

            this.mysqlDataSource = mysqlDataSource;
            this.postgresDataSource = postgresDataSource;
        }

        @PostConstruct
        public void migrate() {

            Flyway.configure()
                    .dataSource(mysqlDataSource)
                    .locations("classpath:db/migration/mysql")
                    .load()
                    .migrate();

            Flyway.configure()
                    .dataSource(postgresDataSource)
                    .locations("classpath:db/migration/postgres")
                    .load()
                    .migrate();
        }
    }