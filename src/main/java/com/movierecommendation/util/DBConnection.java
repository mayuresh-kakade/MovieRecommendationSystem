
package com.movierecommendation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/movie_recommendation";
    private static final String USER = "root";
    private static final String PASSWORD = "Mska@7777";

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "MySQL JDBC driver is not available at runtime.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
