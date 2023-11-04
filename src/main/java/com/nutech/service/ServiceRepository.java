package com.nutech.service;

import com.nutech.model.entity.Service;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.nutech.repository.DataSourceConfig.getDataSource;

@Repository
public class ServiceRepository {

    public List<Service> findAll() {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    SELECT *
                    FROM service
                    """;

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                List<Service> services = new ArrayList<>();
                while (resultSet.next()) {
                    services.add(Service.builder()
                            .serviceCode(resultSet.getString("service_code"))
                            .serviceName(resultSet.getString("service_name"))
                            .serviceIcon(resultSet.getString("service_icon"))
                            .serviceTariff(resultSet.getInt("service_tariff"))
                            .build());
                }
                return services;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
