package user;

import customexceptions.ExceededAttemptsException;
import userinterface.UI;

public class Authentication {
    String username;
    UI ui;
    private String password;
    private int wrongAttempts;
    

    public Authentication(String password, String username, UI ui) {
        this.password = password;
        this.username = username;
        this.ui = ui;
        this.wrongAttempts = 0;
    }

    public String getUsername() {
        return this.username;
    }

    public boolean checkPassword(String username, String password) throws ExceededAttemptsException {
        boolean isMatch = this.password.equals(password) && this.username.equals(username);
        if (!isMatch) {
            wrongAttempts++;
            throw new ExceededAttemptsException(wrongAttempts < 3);
        }
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

    public int getWrongAttempts() {
        return wrongAttempts;
    }

    public boolean authenticate() throws ExceededAttemptsException {
        System.out.println("username: ");
        String inputUsername = this.ui.readInput();
        System.out.println("password: ");
        String inputPassword = this.ui.readInput();
        /*try {
            return this.checkPassword(inputUsername, inputPassword);
        } catch (ExceededAttemptsException e) {
            if (e.isCanTryAgain()) {
                System.out.println("Wrong password or username, please try again.");
            } else {
                System.out.println("Sorry, too many incorrect attempts.");
            }
            return false;
        }*/
        return this.checkPassword(inputUsername, inputPassword);
    }
}
