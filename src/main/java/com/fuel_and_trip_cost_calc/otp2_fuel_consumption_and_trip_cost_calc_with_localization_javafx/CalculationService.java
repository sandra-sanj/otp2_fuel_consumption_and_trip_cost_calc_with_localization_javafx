package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CalculationService {
    private static final String INSERT_SQL =
            "INSERT INTO calculation_records(distance, consumption, price, total_fuel, total_cost, language) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    public static void saveCalculation(
            double distance,
            double consumption,
            double price,
            double totalFuel,
            double totalCost,
            String language
    ) throws SQLException {

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {

            stmt.setDouble(1, distance);
            stmt.setDouble(2, consumption);
            stmt.setDouble(3, price);
            stmt.setDouble(4, totalFuel);
            stmt.setDouble(5, totalCost);
            stmt.setString(6, language);

            stmt.executeUpdate();

        }
    }
}
