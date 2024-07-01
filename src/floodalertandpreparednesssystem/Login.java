package floodalertandpreparednesssystem;

import javax.swing.JOptionPane;
import java.util.List;

public class Login {

    public static User authenticate(String username, String password, List<User> users) {
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                return user;
            }
        }
        return null;
    }

    public static boolean signUpUser(String username, String password, FloodAlertandPreparednessSystem.Role role, List<User> users) {
        if (username.length() < 5) {
            JOptionPane.showMessageDialog(null, "Username must be at least 5 characters long.");
            return false;
        }

        if (!isValidPassword(password)) {
            JOptionPane.showMessageDialog(null, "Password must be at least 6 characters long with at least one uppercase letter, one lowercase letter, and a number.");
            return false;
        }

        for (User user : users) {
            if (user.username.equals(username)) {
                JOptionPane.showMessageDialog(null, "Username already exists. Please try a different username.");
                return false;
            }
        }

        users.add(new User(username, password, role));
        return true;
    }

    private static boolean isValidPassword(String password) {
        if (password.length() < 6) {
            return false;
        }

        boolean hasUppercase = false;
        boolean hasLowercase = false;
        boolean hasNumber = false;

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasUppercase = true;
            } else if (Character.isLowerCase(c)) {
                hasLowercase = true;
            } else if (Character.isDigit(c)) {
                hasNumber = true;
            }
        }

        return hasUppercase && hasLowercase && hasNumber;
    }
}
