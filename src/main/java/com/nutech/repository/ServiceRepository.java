package com.nutech.repository;

import com.nutech.model.entity.Service;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public boolean existByCode(String serviceCode) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    SELECT *
                    FROM service
                    WHERE service_code = ?
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, serviceCode);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<Service> findByCode(String serviceCode) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    SELECT *
                    FROM service
                    WHERE service_code = ?
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, serviceCode);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) return Optional.empty();
                return Optional.of(Service.builder()
                        .serviceCode(resultSet.getString("service_code"))
                        .serviceName(resultSet.getString("service_name"))
                        .serviceIcon(resultSet.getString("service_icon"))
                        .serviceTariff(resultSet.getInt("service_tariff"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
