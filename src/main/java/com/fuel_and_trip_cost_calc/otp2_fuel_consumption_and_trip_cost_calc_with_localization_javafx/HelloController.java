package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.Map;

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

    private Locale currentLocale;
    private Map<String, String> translations;

    @FXML
    public void initialize() {
        this.currentLocale = new Locale("en", "US");
        setLanguage();
    }

    @FXML
    public void onCalculateButtonClick(ActionEvent actionEvent) {
        Calculator calculator = new Calculator();

        try {
            double consumption = Double.parseDouble(String.valueOf(txtConsumption.getText()));
            double distance = Double.parseDouble(String.valueOf(txtDistance.getText()));
            double totalFuel = calculator.totalFuel(consumption, distance);
            double price = Double.parseDouble(String.valueOf(txtPrice.getText()));
            double totalCost = calculator.totalCost(totalFuel, price);
            lblResult.setText(MessageFormat.format(this.translations.get("result.label"), totalFuel, totalCost));

            CalculationService.saveCalculation(distance, consumption, price, totalFuel, totalCost, currentLocale.toString());

        } catch (Exception e) {
            lblResult.setText(this.translations.get("invalid.input"));
        }
    }

    @FXML
    public void onLanguageEnglishButtonClick(ActionEvent actionEvent) {
        this.currentLocale = new Locale("en", "US");
        setLanguage();
    }

    @FXML
    public void onLanguageFrenchButtonClick(ActionEvent actionEvent) {
        this.currentLocale = new Locale("fr", "FR");
        setLanguage();
    }

    @FXML
    public void onLanguageJapaneseButtonClick(ActionEvent actionEvent) {
        this.currentLocale = new Locale("ja", "JP");
        setLanguage();
    }

    @FXML
    public void onLanguagePersianButtonClick(ActionEvent actionEvent) {
        this.currentLocale = new Locale("fa", "IR");
        setLanguage();
    }

    public void setLanguage() {
        this.translations = LocalizationService.loadStrings(this.currentLocale.toString());

        lblDistance.setText(this.translations.get("distance.label"));
        lblConsumption.setText(this.translations.get("consumption.label"));
        lblPrice.setText(this.translations.get("price.label"));
        btnCalculate.setText(this.translations.get("calculate.button"));
        lblResult.setText(this.translations.get("result.label"));

        // empty result
        lblResult.setText(null);
    }
}
