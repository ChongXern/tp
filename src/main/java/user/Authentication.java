package user;

import customexceptions.ExceededAttemptsException;
import userinterface.UI;

public class Authentication {
    private static int attemptsLimit = 3;
    String username;
    UI ui;
    private String password;

    public Authentication(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean checkPassword(String username, String password) {
        System.out.println(password + " and " + this.password);
        System.out.println(username + " and " + this.username);
        boolean isMatch = this.password.equals(password) && this.username.equals(username);
        return isMatch;
    }

    public boolean changePassword(String username, String oldPassword, String newPassword)
            throws SecurityException, ExceededAttemptsException {
        if (!checkPassword(username, oldPassword)) {
            return false;
        }
        this.password = newPassword;
        return true;
    }

    public static boolean authenticateUser(BaseUser user, UI ui) throws ExceededAttemptsException {
        Authentication auth = user.getAuthentication();
        String passwordInput;
        for (int i = 0; i < Authentication.attemptsLimit; i++) {
            ui.printMessage("Password: ");
            passwordInput = ui.readInput();
            if (auth.checkPassword(user.getUsername(), passwordInput)) {
                return true;
            } else {
                ui.printMessage("Wrong password");
            }
        }
        throw new ExceededAttemptsException();
    }
}
