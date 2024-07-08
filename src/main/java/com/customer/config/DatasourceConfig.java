package com.customer.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

@Configuration
public class DatasourceConfig {
    private final Environment env;

    @Autowired
    public DatasourceConfig(final Environment env) {
        this.env = env;
    }

    @Bean
    public DataSource dataSource() {
        return DataSourceBuilder.create() //
                .driverClassName(env.getProperty("sqlite.driver-class-name"))
                .url(env.getProperty("sqlite.url"))
                .build();
    }
}
