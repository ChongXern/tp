package financeproject;

import command.BaseCommand;
import customexceptions.ExceededAttemptsException;
import customexceptions.InactivityTimeoutException;
import customexceptions.IncompletePromptException;
import customexceptions.UserNotFoundExcption;
import financialtransactions.TransactionManager;
import parser.Parser;
import storage.Storage;
import user.Authentication;
import user.BaseUser;
import user.InactivityTimer;
import userinterface.UI;

public class Main {
    public static void main(String[] args) throws SecurityException, ExceededAttemptsException {
        Storage storage = new Storage("./data");
        TransactionManager manager = new TransactionManager();

        UI ui = new UI();
        ui.printMessage("Welcome. Enter your username and password to login.");

        Parser parser = new Parser(ui);
        BaseCommand baseCommand = null;
        String response = "";

        // Authenticating user
        BaseUser user = null;
        InactivityTimer inactivityTimer = new InactivityTimer();
        try {
            ui.printMessage("Username: ");
            response = ui.readInput();
            user = storage.loadUser(response);
            Authentication.authenticateUser(user, ui);
        } catch (UserNotFoundExcption e) {
            ui.printMessage(e.getMessage());
            return;
        } catch (ExceededAttemptsException e) {
            ui.printMessage(e.getMessage());
            return;
        }
        if (user != null) {
            ui.printMessage("User has been authenticated. Starting program...");
        } else {
            return;
        }

        // Main program flow
        do {
            response = ui.readInput();
            try {
                baseCommand = parser.parseCommand(response);
                response = baseCommand.execute(manager);
                ui.printMessage(response);
                inactivityTimer.resetTimer();
            } catch (IncompletePromptException e) {
                ui.printMessage(e.getMessage());
            } catch (Exception e) {
                ui.printMessage("Uh-oh, something went wrong: " + e.getMessage());
            }

            storage.saveFile(user.getUsername(), manager);

            try {
                inactivityTimer.checkTimeElapsed();
            } catch (InactivityTimeoutException e) {
                if (e.isTimeOut()) {
                    assert baseCommand != null;
                    baseCommand.setIsExit(true);
                } else if (e.isGracePeriod()) {
                    ui.printMessage("Some time has passed. Would you still like to continue: ");
                    String wantToContinue = ui.readInput();
                    if (wantToContinue.equalsIgnoreCase("y") ||
                            wantToContinue.equalsIgnoreCase("yes")) {
                        System.out.println("Session continued.");
                        inactivityTimer.resetTimer();
                    } else if (wantToContinue.equalsIgnoreCase("n") ||
                            wantToContinue.equalsIgnoreCase("no")) {
                        System.out.println("Session ended. ");
                        assert baseCommand != null;
                        baseCommand.setIsExit(true);
                    }
                }
            }
        } while (baseCommand == null || !baseCommand.isExit());
        ui.closeScanner();
    }
}
