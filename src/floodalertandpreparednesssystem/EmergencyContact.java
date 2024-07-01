package floodalertandpreparednesssystem;

import javax.swing.JOptionPane;
import java.util.*;

public class EmergencyContact {
    
    private String state;
    private String clinicName;
    private String clinicContact;
    private String shelterName;
    
    public EmergencyContact(String state, String clinicName, String clinicContact, String shelterName) {
        this.state = state;
        this.clinicName = clinicName;
        this.clinicContact = clinicContact; 
        this.shelterName = shelterName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }
    
    public String getClinicContact() {
        return clinicContact;
    }

    public void setClinicContact(String clinicContact) {
        this.clinicContact = clinicContact;  
    }
    
    public String getShelterName() {
        return shelterName;
    }

    public void setShelterName(String shelterName) {
        this.shelterName = shelterName;
    }
    
    public static void view() {
        String contactDetails = "";
        for (EmergencyContact contact : FloodAlertandPreparednessSystem.contacts) {
            contactDetails += "State, City: " + contact.getState() + "\n"
                            + "Clinic Name: " + contact.getClinicName() + "\n"
                            + "Contact Number: " + contact.getClinicContact() + "\n"
                            + "Nearby Shelter: " + contact.getShelterName() + "\n\n";
        }
        JOptionPane.showMessageDialog(null, contactDetails, "Emergency Contacts and Shelters", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void add() {
        String state = JOptionPane.showInputDialog("Enter state, city: ");
        String clinicName = JOptionPane.showInputDialog("Enter clinic name: ");
        String clinicContact = JOptionPane.showInputDialog("Enter clinic contact: ");
        String shelterName = JOptionPane.showInputDialog("Enter shelter name: ");

        EmergencyContact contact = new EmergencyContact(state, clinicName, clinicContact, shelterName);
        FloodAlertandPreparednessSystem.contacts.add(contact);
        JOptionPane.showMessageDialog(null, "Shelter added successfully.");
    }

    public static void delete() {
        String state = JOptionPane.showInputDialog("Enter the state, city of the shelter to delete: ");
        String clinicName = JOptionPane.showInputDialog("Enter the name of the clinic to delete: ");
        
        EmergencyContact toRemove = null;
        for (EmergencyContact contact : FloodAlertandPreparednessSystem.contacts) {
            if (contact.getState().equals(state) && contact.getClinicName().equals(clinicName)) {
                toRemove = contact;
                break;
            }
        }
        if (toRemove != null) {
            FloodAlertandPreparednessSystem.contacts.remove(toRemove);
            JOptionPane.showMessageDialog(null, "Shelter deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No shelter found for the given state and name.");
        }
    }

    public static void update() {
        String state = JOptionPane.showInputDialog("Enter the state, city of the shelter to update:");
        String clinicName = JOptionPane.showInputDialog("Enter the clinic name to update:");
        
        EmergencyContact toUpdate = null;
        for (EmergencyContact contact : FloodAlertandPreparednessSystem.contacts) {
            if (contact.getState().equals(state) && contact.getClinicName().equals(clinicName)) {
                toUpdate = contact;
                break;
            }
        }
        if (toUpdate != null) {
            String newState = JOptionPane.showInputDialog("Enter new state, city:", toUpdate.getState());
            String newClinicName = JOptionPane.showInputDialog("Enter new clinic name:", toUpdate.getClinicName());
            String newClinicContact = JOptionPane.showInputDialog("Enter new clinic contact:", toUpdate.getClinicContact());
            String newShelterName = JOptionPane.showInputDialog("Enter new shelter name:", toUpdate.getShelterName());
            toUpdate.setState(newState);
            toUpdate.setClinicName(newClinicName);
            toUpdate.setClinicContact(newClinicContact);
            toUpdate.setShelterName(newShelterName);
            JOptionPane.showMessageDialog(null, "Shelter updated successfully.");
        } else {
            JOptionPane.showMessageDialog(null, "No shelter found for the given state and name.");
        }
    }
    
    public static void search() {
        String state = JOptionPane.showInputDialog("Enter the state, city to search for:");
        String contactDetails = "";
        for (EmergencyContact contact : FloodAlertandPreparednessSystem.contacts) {
            if (contact.getState().toLowerCase().contains(state.toLowerCase())) {
                contactDetails += "State, City: " + contact.getState() + "\n"
                                + "Clinic Name: " + contact.getClinicName() + "\n"
                                + "Contact Number: " + contact.getClinicContact() + "\n"
                                + "Nearby Shelter: " + contact.getShelterName() + "\n\n";
            }
        }
        if (contactDetails.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No shelters found for the given state.", "Search Results", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, contactDetails, "Search Results", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
