package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        String host = System.getenv().getOrDefault("DB_HOST", "localhost");
        String port = System.getenv().getOrDefault("DB_PORT", "3306");
        String db = System.getenv().getOrDefault("DB_NAME", "fuel_and_trip_cost_calc_translation");
        String user = System.getenv().getOrDefault("DB_USER", "test_user");
        String pass = System.getenv().getOrDefault("DB_PASSWORD", "password");

        String url = String.format("jdbc:mariadb://%s:%s/%s", host, port, db);

        return DriverManager.getConnection(url, user, pass);
    }
}
