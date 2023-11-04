package com.nutech.repository;

import com.nutech.model.entity.Banner;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static com.nutech.repository.DataSourceConfig.getDataSource;

@Repository
public class BannerRepository {

    public List<Banner> findAll() {

        try (Connection connection = getDataSource().getConnection()) {
            String sql = """
                    SELECT *
                    FROM banner
                    """;

            try (Statement statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery(sql);

                List<Banner> banners = new ArrayList<>();
                while (resultSet.next()) {
                    banners.add(Banner.builder()
                            .bannerName(resultSet.getString("banner_name"))
                            .bannerImage(resultSet.getString("banner_image"))
                            .description(resultSet.getString("description"))
                            .build());
                }
                return banners;
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
