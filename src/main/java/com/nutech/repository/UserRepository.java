package com.nutech.repository;

import com.nutech.model.dto.ProfileUpdateRequest;
import com.nutech.model.entity.User;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.nutech.repository.DataSourceConfig.getDataSource;

@Repository
public class UserRepository {

    public boolean existByEmail(String email) {
        String sql = """
                SELECT *
                FROM user
                WHERE email = ?
                """;

        try (PreparedStatement statement = getDataSource().getConnection().prepareStatement(sql)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public Optional<User> findByEmail(String email) {
        String sql = """
                SELECT *
                FROM user
                WHERE email = ?
                """;

        try (PreparedStatement statement = getDataSource().getConnection().prepareStatement(sql)) {
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
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public void insert(User user) {
        String sql = """
                INSERT INTO user (email, password, first_name, last_name)
                VALUES (?, ?, ?, ?)
                """;

        try (PreparedStatement statement = getDataSource().getConnection().prepareStatement(sql)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public User update(String email, ProfileUpdateRequest request) {
        String sql = """
                UPDATE user
                SET first_name = ?, last_name = ?
                WHERE email = ?
                """;
        
        try (PreparedStatement statement = getDataSource().getConnection().prepareStatement(sql)) {
            statement.setString(1, request.getFirstName());
            statement.setString(2, request.getLastName());
            statement.setString(3, email);
            statement.executeUpdate();
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }

        return findByEmail(email).get();
    }

}
