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
    public Button btnEN;

    @FXML
    public Button btnFR;

    @FXML
    public Button btnJP;

    @FXML
    public Button btnIR;

    @FXML
    public Label lblDistance;

    @FXML
    public TextField txtDistance;

    @FXML
    public Label lblConsumption;

    @FXML
    public TextField txtConsumption;

    @FXML
    public Label lblPrice;

    @FXML
    public TextField txtPrice;

    @FXML
    public Button btnCalculate;

    @FXML
    public Label lblResult;

    private ResourceBundle resource;

    @FXML
    public void initialize() {
        this.setLanguage(new Locale("en", "US"));
    }

    @FXML
    public void onCalculateButtonClick(ActionEvent actionEvent) {
        Calculator calculator = new Calculator();

        try {
            double consumption = Double.parseDouble(String.valueOf(txtConsumption.getText()));
            double distance = Double.parseDouble(String.valueOf(txtDistance.getText()));
            double fuel = calculator.totalFuel(consumption, distance);
            double price = Double.parseDouble(String.valueOf(txtPrice.getText()));
            double totalPrice = calculator.totalCost(fuel, price);
            lblResult.setText(MessageFormat.format(resource.getString("result.label"), fuel, totalPrice));
        } catch (Exception e) {
            lblResult.setText(resource.getString("invalid.input"));
        }
    }

    @FXML
    public void onLanguageEnglishButtonClick(ActionEvent actionEvent) {
        setLanguage(new Locale("en", "US"));
    }

    @FXML
    public void onLanguageFrenchButtonClick(ActionEvent actionEvent) {
        setLanguage(new Locale("fr", "FR"));
    }

    @FXML
    public void onLanguageJapaneseButtonClick(ActionEvent actionEvent) {
        setLanguage(new Locale("ja", "JP"));
    }

    @FXML
    public void onLanguagePersianButtonClick(ActionEvent actionEvent) {
        setLanguage(new Locale("fa", "IR"));
    }

    public void setLanguage(Locale locale) {
        this.resource = ResourceBundle.getBundle("MessagesBundle", locale);

        lblDistance.setText(resource.getString("distance.label"));
        lblConsumption.setText(resource.getString("consumption.label"));
        lblPrice.setText(resource.getString("price.label"));
        btnCalculate.setText(resource.getString("calculate.button"));
        lblResult.setText(resource.getString("result.label"));

        // empty textfields and result
        //txtDistance.setText(null);
        //txtConsumption.setText(null);
        //txtPrice.setText(null);
        lblResult.setText(null);
    }
}
