package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculationServiceTest {

    @Test
    void saveCalculation() {
        assertDoesNotThrow(
                () -> CalculationService.saveCalculation(100.0, 6.5, 1.95, 6.5, 12.68, "en")
        );
    }
}