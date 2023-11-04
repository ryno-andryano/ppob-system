package com.nutech.repository;

import com.nutech.model.dto.TransactionHistoryResponse;
import com.nutech.model.entity.Transaction;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<TransactionHistoryResponse> findAll(String email) {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    SELECT *
                    FROM transaction
                    WHERE user_email = ?
                    ORDER BY created_on DESC
                    """;

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, email);
                ResultSet resultSet = statement.executeQuery();

                List<TransactionHistoryResponse> transactions = new ArrayList<>();
                while (resultSet.next()) {
                    transactions.add(TransactionHistoryResponse.builder()
                            .invoiceNumber(resultSet.getString("invoice_number"))
                            .transactionType(resultSet.getString("transaction_type"))
                            .description(resultSet.getString("description"))
                            .totalAmount(resultSet.getInt("total_amount"))
                            .createdOn(resultSet.getString("created_on"))
                            .build());
                }
                return transactions;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
