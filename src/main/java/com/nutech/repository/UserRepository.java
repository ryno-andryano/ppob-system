package com.nutech.repository;

import com.nutech.model.dto.ProfileUpdateRequest;
import com.nutech.model.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.nutech.repository.DataSourceConfig.getDataSource;

@Repository
public class UserRepository {

    public boolean existByEmail(String email) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    SELECT *
                    FROM user
                    WHERE email = ?
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();
                return resultSet.next();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Optional<User> findByEmail(String email) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    SELECT *
                    FROM user
                    WHERE email = ?
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();

                if (!resultSet.next()) return Optional.empty();
                return Optional.of(new User(
                        resultSet.getString("email"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("password"),
                        resultSet.getString("profile_image"),
                        resultSet.getInt("balance")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void insert(User user) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    INSERT INTO user (email, password, first_name, last_name)
                    VALUES (?, ?, ?, ?)
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, user.getEmail());
                statement.setString(2, user.getPassword());
                statement.setString(3, user.getFirstName());
                statement.setString(4, user.getLastName());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public User update(String email, ProfileUpdateRequest request) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    UPDATE user
                    SET first_name = ?, last_name = ?
                    WHERE email = ?
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, request.getFirstName());
                statement.setString(2, request.getLastName());
                statement.setString(3, email);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findByEmail(email).get();

    }

    public User updateBalance(String email, int newBalance) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    UPDATE user
                    SET balance = ?
                    WHERE email = ?
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, newBalance);
                statement.setString(2, email);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return findByEmail(email).get();
    }
}
