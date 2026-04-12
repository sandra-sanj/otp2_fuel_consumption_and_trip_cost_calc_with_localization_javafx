package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceTest {

    private static final String EN_US_LOCALE = "en_US";

    @Test
    void loadStrings() {
        Map<String, String> translations = LocalizationService.loadStrings(EN_US_LOCALE);
        assertEquals(6, translations.size());
    }

    @Test
    void getString() {
        String value = LocalizationService.getString("invalid.input", EN_US_LOCALE);
        assertEquals("Invalid input", value);
    }

    @Test
    void getAllKeys() {
        List<String> keys = LocalizationService.getAllKeys(EN_US_LOCALE);
        assertEquals(6, keys.size());
    }
}