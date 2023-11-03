package com.nutech.repository;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    @Bean
    public static DataSource getDataSource() {
        return DataSourceBuilder
                .create()
                .username("root")
                .password("1234")
                .url("jdbc:mysql://localhost:3306/ppob")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .build();
    }

}
