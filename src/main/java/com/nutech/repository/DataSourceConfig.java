package com.nutech.repository;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private static DataSource dataSource;

    @Bean
    public static DataSource getDataSource() {
        if (dataSource == null)
            dataSource = DataSourceBuilder
                    .create()
                    .username("root")
                    .password("hGeEh4C3d1GH141CgaG4g5gcCCEhFeHA")
                    .url("jdbc:mysql://viaduct.proxy.rlwy.net:22077/railway")
                    .driverClassName("com.mysql.cj.jdbc.Driver")
                    .build();

        return dataSource;
    }

}
