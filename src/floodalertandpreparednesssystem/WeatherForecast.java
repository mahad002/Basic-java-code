package floodalertandpreparednesssystem;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class WeatherForecast {
    private String date;
    private String city;
    private String condition;
    private String riskLevel;

    public static final String[] malaysianCities = {
            "Kuala Lumpur", "George Town", "Ipoh", "Kuching", "Johor Bahru", "Kota Kinabalu",
            "Shah Alam", "Malacca City", "Alor Setar", "Miri", "Kangar", "Kuantan"
    };

    private static final String[] conditions = {"Sunny", "Cloudy", "Rainy", "Stormy"};
    private static final String[] riskLevels = {"Low", "Moderate", "High"};

    public WeatherForecast(String date, String city, String condition, String riskLevel) {
        this.date = date;
        this.city = city;
        this.condition = condition;
        this.riskLevel = riskLevel;
    }

    public static void createForecastFromUserInput() {
        String city = (String) JOptionPane.showInputDialog(null,
                "Select a city:", "Select City",
                JOptionPane.QUESTION_MESSAGE, null,
                malaysianCities, malaysianCities[0]);

        if (city != null) {
            String date = JOptionPane.showInputDialog("Enter date (yyyy-mm-dd):");
            String condition = getRandomCondition();
            String riskLevel = getRandomRiskLevel();

            WeatherForecast forecast = new WeatherForecast(date, city, condition, riskLevel);
            FloodAlertandPreparednessSystem.forecasts.add(forecast);

            JOptionPane.showMessageDialog(null, "Forecast created successfully:\n\n"
                    + "City: " + forecast.city + "\n"
                    + "Date: " + forecast.date + "\n"
                    + "Condition: " + forecast.condition + "\n"
                    + "Risk Level: " + forecast.riskLevel);
        }
    }

    private static String getFormattedDate(int daysOffset) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, daysOffset);
        return sdf.format(calendar.getTime());
    }

    private static String getRandomCondition() {
        return conditions[ThreadLocalRandom.current().nextInt(conditions.length)];
    }

    private static String getRandomRiskLevel() {
        return riskLevels[ThreadLocalRandom.current().nextInt(riskLevels.length)];
    }

    public static void view(String selectedCity) {
        List<WeatherForecast> forecastsToShow = new ArrayList<>();
        if (selectedCity != null) {
            String[] dates = {
                getFormattedDate(-1), // Yesterday
                getFormattedDate(0),  // Today
                getFormattedDate(1)   // Tomorrow
            };

            for (String date : dates) {
                String condition = getRandomCondition();
                String riskLevel = getRandomRiskLevel();
                forecastsToShow.add(new WeatherForecast(date, selectedCity, condition, riskLevel));
            }
        } else {
            forecastsToShow = FloodAlertandPreparednessSystem.forecasts;
        }

        StringBuilder message = new StringBuilder(selectedCity == null ? "All Forecasts:\n\n" : "Forecasts for " + selectedCity + ":\n\n");
        for (WeatherForecast forecast : forecastsToShow) {
            message.append("City: ").append(forecast.city).append("\n")
                    .append("Date: ").append(forecast.date).append("\n")
                    .append("Condition: ").append(forecast.condition).append("\n")
                    .append("Risk Level: ").append(forecast.riskLevel).append("\n\n");
        }

        JOptionPane.showMessageDialog(null, message.toString(), "Weather Forecasts", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void add() {
        createForecastFromUserInput();
    }

    public static void delete() {
        String date = JOptionPane.showInputDialog("Enter the date of the forecast to delete (yyyy-mm-dd):");
        WeatherForecast toRemove = null;
        for (WeatherForecast forecast : FloodAlertandPreparednessSystem.forecasts) {
            if (forecast.date.equals(date)) {
                toRemove = forecast;
                break;
            }
        }
        if (toRemove != null) {
            FloodAlertandPreparednessSystem.forecasts.remove(toRemove);
            JOptionPane.showMessageDialog(null, "Forecast deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No forecast found for the given date.");
        }
    }

    public static void update() {
        String date = JOptionPane.showInputDialog("Enter the date of the forecast to update (yyyy-mm-dd):");
        WeatherForecast toUpdate = null;
        for (WeatherForecast forecast : FloodAlertandPreparednessSystem.forecasts) {
            if (forecast.date.equals(date)) {
                toUpdate = forecast;
                break;
            }
        }
        if (toUpdate != null) {
            String newCity = (String) JOptionPane.showInputDialog(null,
                    "Select a city:", "Select City",
                    JOptionPane.QUESTION_MESSAGE, null,
                    malaysianCities, malaysianCities[0]);

            if (newCity != null) {
                String newCondition = getRandomCondition();
                String newRiskLevel = getRandomRiskLevel();

                toUpdate.city = newCity;
                toUpdate.condition = newCondition;
                toUpdate.riskLevel = newRiskLevel;

                JOptionPane.showMessageDialog(null, "Forecast updated successfully.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "No forecast found for the given date.");
        }
    }
}
