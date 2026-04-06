package com.fuel_and_trip_cost_calc.otp2_fuel_consumption_and_trip_cost_calc_with_localization_javafx;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocalizationService {
    private static final String LOAD_STRINGS_SQL =
            "SELECT `key`, value " +
                    "FROM localization_strings " +
                    "WHERE language = ?";

    private static final String GET_STRING_SQL =
            "SELECT value " +
                    "FROM localization_strings " +
                    "WHERE `key` = ? AND language = ?";

    private static final String GET_ALL_KEYS_SQL =
            "SELECT DISTINCT `key` " +
                    "FROM localization_strings " +
                    "WHERE language = ?";

    public static Map<String, String> loadStrings(String language) {
        Map<String, String> translations = new HashMap<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(LOAD_STRINGS_SQL)) {

            stmt.setString(1, language);
            var response = stmt.executeQuery();

            while (response.next()) {
                translations.put(response.getString("key"), response.getString("value"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return translations;
    }

    public static String getString(String key, String language) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_STRING_SQL)) {

            stmt.setString(1, key);
            stmt.setString(2, language);

            var response = stmt.executeQuery();

            if (response.next()) {
                return response.getString("value");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static List<String> getAllKeys(String language) {
        List<String> keys = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(GET_ALL_KEYS_SQL)) {

            stmt.setString(1, language);
            var response = stmt.executeQuery();

            while (response.next()) {
                keys.add(response.getString("key"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return keys;
    }
}
