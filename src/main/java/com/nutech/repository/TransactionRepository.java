package com.nutech.repository;

import com.nutech.model.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.nutech.repository.DataSourceConfig.getDataSource;

@Repository
public class TransactionRepository {

    public void insert(Transaction transaction) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    INSERT INTO transaction
                    VALUES (?, ?, ?, ?, ?, ?)
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, transaction.getInvoiceNumber());
                statement.setString(2, transaction.getUserEmail());
                statement.setString(3, transaction.getTransactionType());
                statement.setString(4, transaction.getDescription());
                statement.setInt(5, transaction.getTotalAmount());
                statement.setString(6, transaction.getCreatedOn());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
