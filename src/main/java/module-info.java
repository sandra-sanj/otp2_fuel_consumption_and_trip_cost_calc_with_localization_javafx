module com.libraryreservationsystem.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx to javafx.fxml;
    exports com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;
}