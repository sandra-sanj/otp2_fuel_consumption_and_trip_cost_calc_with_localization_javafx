package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void getConnection() {
        assertDoesNotThrow(() -> {
            try (Connection connection = DatabaseConnection.getConnection()) {
                assertNotNull(connection);
                assertFalse(connection.isClosed());
            }
        });
    }
}