package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private static Calculator calculator;

    @BeforeAll
    static void setup() {
        calculator = new Calculator();
    }

    @Test
    void totalFuel() {
        assertEquals(3.0, calculator.totalFuel(2.5, 120));
    }

    @Test
    void totalCost() {
        assertEquals(219.48, calculator.totalCost(120, 1.829));
    }
}