package ru.goth.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class DataBaseConfig {
    private DataBaseConfig() {
        throw new IllegalStateException("Utility class");
    }
    private static HikariDataSource dataSource;
    static {
        ConfigLoader configLoader = new ConfigLoader();
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(configLoader.getDriverName());
        config.setJdbcUrl(configLoader.getDbUrl());
        config.setUsername(configLoader.getDbUsername());
        config.setPassword(configLoader.getDbPassword());

        dataSource = new HikariDataSource(config);
    }

    public static DataSource getDataSource() {
        return dataSource;
    }
}