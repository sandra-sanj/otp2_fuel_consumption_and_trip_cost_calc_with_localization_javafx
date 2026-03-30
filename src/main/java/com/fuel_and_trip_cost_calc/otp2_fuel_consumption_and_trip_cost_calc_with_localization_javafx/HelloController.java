package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class HelloController {
    @FXML
    public Label lblDistance;

    @FXML
    public TextField txtDistance;
    public Label lblConsumption;
    public TextField txtConsumption;
    public Label lblPrice;
    public TextField txtPrice;
    public Button btnCalculate;
    public Button btnEN;
    public Button btnFR;
    public Button btnJP;
    public Button btnIR;
    public Label lblInvalidInput;
    public Label lblResult;

    private Locale locale = new Locale("en", "US");

    @FXML
    public void onCalculateButtonClick(ActionEvent actionEvent) {
        //lblResult.setText("result calculated");

        Calculator calculator = new Calculator();
        // todo: add error handling
        double consumption = Double.parseDouble(String.valueOf(txtConsumption.getText()));
        double distance = Double.parseDouble(String.valueOf(txtDistance.getText()));
        double fuel = calculator.totalFuel(consumption, distance);
        double price = Double.parseDouble(String.valueOf(txtPrice.getText()));
        //lblResult.setText("Fuel: " + fuel + ", Cost: " + calculator.totalCost(fuel, price));
        double totalPrice = calculator.totalCost(fuel, price);
        //lblResult.setText(String.valueOf(calculator.totalCost(fuel, price)));

        ResourceBundle resource = ResourceBundle.getBundle("MessagesBundle", locale);
        lblResult.setText(MessageFormat.format(resource.getString("result.label"), fuel, totalPrice));
    }

    @FXML
    public void onLanguageEnglishButtonClick(ActionEvent actionEvent) {
        setLanguage("en", "US");
    }

    @FXML
    public void onLanguageFrenchButtonClick(ActionEvent actionEvent) {
        setLanguage("fr", "FR");
    }

    @FXML
    public void onLanguageJapaneseButtonClick(ActionEvent actionEvent) {
        setLanguage("ja", "JP");
    }

    @FXML
    public void onLanguagePersianButtonClick(ActionEvent actionEvent) {
        setLanguage("fa", "IR");
    }

    public void setLanguage(String language, String country) {
        this.locale = new Locale(language, country);
        ResourceBundle resource = ResourceBundle.getBundle("MessagesBundle", this.locale);
        // todo: display error if language file is missing

        lblDistance.setText(resource.getString("distance.label"));
        lblConsumption.setText(resource.getString("consumption.label"));
        lblPrice.setText(resource.getString("price.label"));
        btnCalculate.setText(resource.getString("calculate.button"));
        lblResult.setText(resource.getString("result.label"));
        lblInvalidInput.setText(resource.getString("invalid.input"));

        // empty textfields and result
        //txtDistance.setText(null);
        //txtConsumption.setText(null);
        //txtPrice.setText(null);
        lblResult.setText(null);
    }
}
