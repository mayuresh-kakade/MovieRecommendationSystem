
package com.movierecommendation.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL =
            "jdbc:mysql://localhost:3306/movie_recommendation";
    private static final String USER = "root";

    // Replace this with your actual MySQL password.
    private static final String PASSWORD = "Mska@7777";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
