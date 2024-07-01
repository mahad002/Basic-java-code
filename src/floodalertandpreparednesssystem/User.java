package floodalertandpreparednesssystem;

public class User {
    String username;
    String password;
    FloodAlertandPreparednessSystem.Role role;

    public User(String username, String password, FloodAlertandPreparednessSystem.Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}