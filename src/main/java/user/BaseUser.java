package user;

import storage.Storage;
import userinterface.UI;

public class BaseUser {
    String username;
    Authentication auth;
    UI ui;

    public BaseUser(UI ui, Storage storage) {
        ui.printMessage("username:");
        String pw = null;
        do {
            username = ui.readInput().replace(" ", "_");
            pw = storage.loadPassword(username);
            if (pw == null) {
                ui.printMessage(String.format("User does not exist, do you want to create a new user with " +
                        "username %s? Yes or No", username));
                if (ui.readInput().equals("Yes")) {
                    ui.printMessage("Please enter a new password:");
                    pw = ui.readInput();
                    storage.addNewUser(username, pw);
                    ui.printMessage("User successfully created, enter your password again to login");
                } else {
                    ui.printMessage("Please enter another username:");
                }
            }
        } while (pw == null);
        this.auth = new Authentication(pw, username, ui);
        this.ui = ui;
    }
    
    public BaseUser(String username, String pw, UI ui, Storage storage) {
        this.username = username;
        this.auth = new Authentication(pw, username, ui);
        this.ui = ui;
    }

    public String getUsername() {
        return username;
    }

    public Authentication getAuthentication(){
        return this.auth;
    }
}
