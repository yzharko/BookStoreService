package ru.goth.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "ru.goth")
public class DataBaseConfig {

    @Bean
    public DataSource dataSource() {
        ConfigLoader configLoader = new ConfigLoader();
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(configLoader.getDriverName());
        config.setJdbcUrl(configLoader.getDbUrl());
        config.setUsername(configLoader.getDbUsername());
        config.setPassword(configLoader.getDbPassword());

        HikariDataSource dataSource;
        dataSource = new HikariDataSource(config);
        return dataSource;
    }

    @Bean
    public NamedParameterJdbcTemplate template(DataSource dataSource) {
        return new NamedParameterJdbcTemplate(dataSource);
    }
}
