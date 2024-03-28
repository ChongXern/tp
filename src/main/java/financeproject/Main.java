package financeproject;

import command.BaseCommand;
import customexceptions.ExceededAttemptsException;
import customexceptions.InactivityTimeoutException;
import customexceptions.IncompletePromptException;
import financialtransactions.TransactionManager;
import parser.Parser;
import storage.Storage;
import user.Authentication;
import user.BaseUser;
import user.InactivityTimer;
import userinterface.UI;

public class Main {
    public static void main(String[] args) throws SecurityException {
        Storage storage = new Storage("./data");
        TransactionManager manager = new TransactionManager();

        UI ui = new UI();
        ui.printMessage("Welcome. Enter your username and password to login.");

        Parser parser = new Parser(ui);
        BaseCommand baseCommand = null;
        String response = "";

        BaseUser tempUser = new BaseUser("Bob", ui);
        Authentication auth = tempUser.getAuthentication();
        InactivityTimer inactivityTimer = new InactivityTimer();
        boolean isAuthenticated = false;

        while (!isAuthenticated && auth.getWrongAttempts() < 3) {
            try {
                if (!auth.authenticate()) {
                    ui.printMessage("Authentication error");
                } else {
                    ui.printMessage("Password is correct. You are now logged in");
                    manager = storage.loadFile();
                    isAuthenticated = true;
                }
            } catch (ExceededAttemptsException e) {
                if (e.isCanTryAgain()) {
                    ui.printMessage("Incorrect username or password. Please try again.");
                } else {
                    ui.printMessage("Too many incorrect attempts. Authentication failed.");
                }
            }
        }

        if (!isAuthenticated) {
            ui.closeScanner();
            return;
        }

        do {
            String command = ui.readInput();
            try {
                baseCommand = parser.parseCommand(command);
                response = baseCommand.execute(manager);
                ui.printMessage(response);
                inactivityTimer.resetTimer();
            } catch (IncompletePromptException e) {
                if (e.getIsTypo()) {
                    System.out.println("Please prompt again with correct spelling.");
                } else if (e.getIsIncomplete()) {
                    System.out.println("Sorry, your prompt appears incomplete. Could you finish your sentence?");
                } else if (e.getIsUnknown()) {
                    System.out.println("Sorry, prompt inputted is unknown. ");
                }
            } catch (Exception e) {
                System.out.println("Uh-oh, something went wrong: " + e.getMessage());
            }

            storage.saveFile(manager);

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
