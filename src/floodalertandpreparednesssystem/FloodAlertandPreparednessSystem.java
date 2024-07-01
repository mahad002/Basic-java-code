package floodalertandpreparednesssystem;

import javax.swing.JOptionPane;
import java.util.*;

public class FloodAlertandPreparednessSystem {

    // Array List
    static List<User> users = new ArrayList<>();
    static List<FloodAlert> floodAlert = new ArrayList<>();
    static List<WeatherForecast> forecasts = new ArrayList<>();
    static List<FloodGuide> floodGuides = new ArrayList<>();
    static List<EmergencyContact> contacts = new ArrayList<>();
    static List<Report> reports = new ArrayList<>();

    // Enumerate Type, It is a constant in this code
    public enum Role {
        ADMIN, GUEST
    }

    // Main Code
    public static void main(String[] args) {

        // Dummy Data users
        users.add(new User("admin", "Admin123", Role.ADMIN));
        users.add(new User("guest", "Guest123", Role.GUEST));

        // Dummy Data Flood Alerts
        FloodAlert alert1 = new FloodAlert("Kuala Lumpur", "High", new Date());
        FloodAlert alert2 = new FloodAlert("Johor Bahru", "Low", new Date());
        FloodAlert alert3 = new FloodAlert("Johor Bahru", "Low", new Date());
        floodAlert.add(alert1);
        floodAlert.add(alert2);
        floodAlert.add(alert3);

        // Update the dummy data forecasts
        WeatherForecast forecast1 = new WeatherForecast("2024-06-26", "Kuala Lumpur", "Sunny", "Low");
        WeatherForecast forecast2 = new WeatherForecast("2024-06-27", "Kuala Lumpur", "Rainy", "High");
        forecasts.add(forecast1);
        forecasts.add(forecast2);

        // Dummy Data Emergency Contacts and Shelters
        EmergencyContact shelter1 = new EmergencyContact("Selangor, Petaling Jaya", "Klinik Alam Medic", "03-5636 2659", "Sekolah Menengah Alam");
        EmergencyContact shelter2 = new EmergencyContact("Selangor, Subang Jaya", "Klinik Medic Bestari", "03-5638 4137", "International School Bestari");
        EmergencyContact shelter3 = new EmergencyContact("Kuala Lumpur, Kuala Lumpur", "Klinik Hope", "012-541 1692", "Dewan Serbaguna");
        EmergencyContact shelter4 = new EmergencyContact("Penang, Bayan Lepas", "Klinik Medilife", "04-611 5111", "Dewan Serbaguna");
        contacts.add(shelter1);
        contacts.add(shelter2);
        contacts.add(shelter3);
        contacts.add(shelter4);

        // Log in Menu
        boolean run = true;
        do {
            String[] options = {"Login", "Sign Up", "Exit"};
            String input = (String) JOptionPane.showInputDialog(
                    null,
                    "Welcome to the Flood Alert and Preparedness System!\nPlease choose an option:",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (input == null) {
                run = false;
                break;
            }

            switch (input) {
                case "Login":
                    loginUser();
                    break;
                case "Sign Up":
                    signUpUser();
                    break;
                case "Exit":
                    run = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Try Again.");
                    break;
            }
        } while (run);
    }

    public static void loginUser() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        User currentUser = Login.authenticate(username, password, users);

        if (currentUser == null) {
            JOptionPane.showMessageDialog(null, "Invalid login credentials.");
            return;
        }

        boolean run = true;

        // Main Menu
        do {
            String[] options = {
                "Flood Alerts", "Weather Forecast", "Flood Guide",
                "Emergency Contacts and Shelters", "User Reports and Feedback", "Exit"
            };

            String input = (String) JOptionPane.showInputDialog(
                    null,
                    "Welcome to the Flood Alert and Preparedness System!\nPlease choose an option:",
                    "Menu",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (input == null) {
                run = false;
                break;
            }

            switch (input) {
                case "Flood Alerts":
                    handleFloodAlerts(currentUser);
                    break;

                case "Weather Forecast":
                    handleWeatherForecast(currentUser);
                    break;

                case "Flood Guide":
                    handleFloodGuide(currentUser);
                    break;

                case "Emergency Contacts and Shelters":
                    handleEmergencyContactsAndShelters(currentUser);
                    break;

                case "User Reports and Feedback":
                    handleUserReportsAndFeedback(currentUser);
                    break;

                case "Exit":
                    run = false;
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Invalid option. Try Again.");
                    break;
            }
        } while (run);
    }

    private static void signUpUser() {
        String username = JOptionPane.showInputDialog("Enter username:");
        String password = JOptionPane.showInputDialog("Enter password:");
        String[] roles = {"ADMIN", "GUEST"};
        String roleStr = (String) JOptionPane.showInputDialog(
                null,
                "Select user role:",
                "Sign Up",
                JOptionPane.QUESTION_MESSAGE,
                null,
                roles,
                roles[0]
        );

        if (roleStr == null) {
            return;
        }

        Role role = Role.valueOf(roleStr);

        boolean success = Login.signUpUser(username, password, role, users);
        if (success) {
            JOptionPane.showMessageDialog(null, "Sign up successful! You can now log in.");
        }
    }

    // Flood Alert Menu
    private static void handleFloodAlerts(User currentUser) {
        String[] optionsFA;
        if (currentUser.role == Role.ADMIN) {
            optionsFA = new String[]{"View Alerts", "Add Alert", "Delete Alert", "Update Alert"};
        } else {
            optionsFA = new String[]{"View Alerts"};
        }

        String inputFA = (String) JOptionPane.showInputDialog(
                null,
                "Select an action:\n",
                "Flood Alerts",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsFA,
                optionsFA[0]
        );

        if (inputFA != null) {
            switch (inputFA) {
                case "View Alerts":
                    FloodAlert.viewAlerts();
                    break;
                case "Add Alert":
                    FloodAlert.addAlert();
                    break;
                case "Delete Alert":
                    FloodAlert.deleteAlert();
                    break;
                case "Update Alert":
                    FloodAlert.updateAlert();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option for Flood Alerts.");
                    break;
            }
        }
    }

    // Weather Forecast Menu
    private static void handleWeatherForecast(User currentUser) {
        String[] optionsWF;
        if (currentUser.role == Role.ADMIN) {
            optionsWF = new String[]{"View Forecast", "Add Forecast", "Delete Forecast", "Update Forecast"};
        } else {
            optionsWF = new String[]{"View Forecast"};
        }

        String inputWF = (String) JOptionPane.showInputDialog(
                null,
                "Select an action:\n",
                "Weather Forecast",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsWF,
                optionsWF[0]
        );

        if (inputWF != null) {
            switch (inputWF) {
                case "View Forecast":
                    String[] cities = new String[WeatherForecast.malaysianCities.length + 1];
                    cities[0] = "All Cities";
                    System.arraycopy(WeatherForecast.malaysianCities, 0, cities, 1, WeatherForecast.malaysianCities.length);

                    String selectedCity = (String) JOptionPane.showInputDialog(
                            null,
                            "Select a city to view forecasts:",
                            "Select City",
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            cities,
                            cities[0]
                    );

                    WeatherForecast.view(selectedCity.equals("All Cities") ? null : selectedCity);
                    break;
                case "Add Forecast":
                    WeatherForecast.add();
                    break;
                case "Delete Forecast":
                    WeatherForecast.delete();
                    break;
                case "Update Forecast":
                    WeatherForecast.update();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option for Weather Forecast.");
                    break;
            }
        }
    }

    // Flood Guide Menu
    private static void handleFloodGuide(User currentUser) {
        String[] optionsFG;
        if (currentUser.role == Role.ADMIN) {
            optionsFG = new String[]{"View Guide", "Add Guide", "Delete Guide", "Update Guide"};
        } else {
            optionsFG = new String[]{"View Guide"};
        }

        String inputFG = (String) JOptionPane.showInputDialog(
                null,
                "Select an action:\n",
                "Flood Guide",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsFG,
                optionsFG[0]
        );

        if (inputFG != null) {
            switch (inputFG) {
                case "View Guide":
                    FloodGuide.view();
                    break;
                case "Add Guide":
                    FloodGuide.add();
                    break;
                case "Delete Guide":
                    FloodGuide.delete();
                    break;
                case "Update Guide":
                    FloodGuide.update();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option for Flood Guide.");
                    break;
            }
        }
    }

    // Emergency Contacts and Shelters Menu
    private static void handleEmergencyContactsAndShelters(User currentUser) {
        String[] optionsECS;
        if (currentUser.role == Role.ADMIN) {
            optionsECS = new String[]{"View Emergency Contacts and Shelters", "Add Emergency Contacts and Shelters", "Delete Emergency Contacts and Shelters", "Update Emergency Contacts and Shelters"};
        } else {
            optionsECS = new String[]{"View Emergency Contacts and Shelters", "Search by State, City"};
        }

        String inputECS = (String) JOptionPane.showInputDialog(
                null,
                "Select an action:\n",
                "Emergency Contacts and Shelters",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsECS,
                optionsECS[0]
        );

        if (inputECS != null) {
            switch (inputECS) {
                case "View Emergency Contacts and Shelters":
                    EmergencyContact.view();
                    break;
                case "Add Emergency Contacts and Shelters":
                    EmergencyContact.add();
                    break;
                case "Delete Emergency Contacts and Shelters":
                    EmergencyContact.delete();
                    break;
                case "Update Emergency Contacts and Shelters":
                    EmergencyContact.update();
                    break;
                case "Search by State, City":
                    EmergencyContact.search();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option for Emergency Contacts and Shelters.");
                    break;
            }
        }
    }

    // User Reports and Feedback Menu
    private static void handleUserReportsAndFeedback(User currentUser) {
        String[] optionsURF;
        if (currentUser.role == Role.ADMIN) {
            optionsURF = new String[]{"View Reports", "Add Report", "Delete Report", "Update Report"};
        } else {
            optionsURF = new String[]{"View Reports", "Add Report"};
        }

        String inputURF = (String) JOptionPane.showInputDialog(
                null,
                "Select an action:\n",
                "User Reports and Feedback",
                JOptionPane.QUESTION_MESSAGE,
                null,
                optionsURF,
                optionsURF[0]
        );

        if (inputURF != null) {
            switch (inputURF) {
                case "View Reports":
                    Report.view();
                    break;
                case "Add Report":
                    Report.add();
                    break;
                case "Delete Report":
                    Report.delete();
                    break;
                case "Update Report":
                    Report.update();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid option for User Reports and Feedback.");
                    break;
            }
        }
    }
}
