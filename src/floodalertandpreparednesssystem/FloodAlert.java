package floodalertandpreparednesssystem;

import javax.swing.JOptionPane;
import java.util.*;

public class FloodAlert {
    private String location;
    private String alertLevel;
    private Date timestamp;

    public FloodAlert(String location, String alertLevel, Date timestamp) {
        this.location = location;
        this.alertLevel = alertLevel;
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAlertLevel() {
        return alertLevel;
    }

    public void setAlertLevel(String alertLevel) {
        this.alertLevel = alertLevel;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    private static List<FloodAlert> alerts = new ArrayList<>();
    
    public static void addAlert(FloodAlert alert) {
        alerts.add(alert);
    }

    public static List<FloodAlert> getAlertsByLocation(String location) {
        List<FloodAlert> result = new ArrayList<>();
        for (FloodAlert alert : alerts) {
            if (alert.getLocation().equals(location)) {
                result.add(alert);
            }
        }
        return result;
    }

    public static void removeAlert(FloodAlert alert) {
        alerts.remove(alert);
    }

    public static void clearAlerts() {
        alerts.clear();
    }

    public static void viewAlerts() {
    String alertDetails = "";
    for (FloodAlert alert : FloodAlertandPreparednessSystem.floodAlert) {
        alertDetails += "Location: " + alert.getLocation() + "\n"
                      + "Alert Level: " + alert.getAlertLevel() + "\n"
                      + "Timestamp: " + alert.getTimestamp() + "\n\n";
    }
    JOptionPane.showMessageDialog(null, alertDetails, "Flood Alerts", JOptionPane.INFORMATION_MESSAGE);
}



    public static void addAlert() {
        String location = JOptionPane.showInputDialog("Enter location:");
        String alertLevel = JOptionPane.showInputDialog("Enter alert level:");
        Date timestamp = new Date();

        FloodAlert alert = new FloodAlert(location, alertLevel, timestamp);
        addAlert(alert);
        JOptionPane.showMessageDialog(null, "Alert added successfully.");
    }

    public static void deleteAlert() {
        String location = JOptionPane.showInputDialog("Enter the location of the alert to delete:");
        FloodAlert toRemove = null;
        for (FloodAlert alert : alerts) {
            if (alert.getLocation().equals(location)) {
                toRemove = alert;
                break;
            }
        }
        if (toRemove != null) {
            removeAlert(toRemove);
            JOptionPane.showMessageDialog(null, "Alert deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No alert found for the given location.");
        }
    }

    public static void updateAlert() {
        String location = JOptionPane.showInputDialog("Enter the location of the alert to update:");
        FloodAlert toUpdate = null;
        for (FloodAlert alert : alerts) {
            if (alert.getLocation().equals(location)) {
                toUpdate = alert;
                break;
            }
        }
        if (toUpdate != null) {
            String newAlertLevel = JOptionPane.showInputDialog("Enter new alert level:", toUpdate.getAlertLevel());
            toUpdate.setAlertLevel(newAlertLevel);
            JOptionPane.showMessageDialog(null, "Alert updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No alert found for the given location.");
        }
    }

    @Override
    public String toString() {
        return "FloodAlert{" +
                "location='" + location + '\'' +
                ", alertLevel='" + alertLevel + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
