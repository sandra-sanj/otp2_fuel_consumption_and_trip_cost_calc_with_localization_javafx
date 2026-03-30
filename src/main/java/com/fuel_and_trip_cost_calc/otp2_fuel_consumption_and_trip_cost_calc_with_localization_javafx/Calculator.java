package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

public class Calculator {

    public double totalFuel(double consumption, double distance) {
        return (consumption / 100) * distance;
    }

    public double totalCost(double fuel, double price) {
        return fuel * price;
    }
}
