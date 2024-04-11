package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;
import user.InactivityTimer;

public class AddReminderCommand extends BaseCommand {
    public AddReminderCommand(String[] commandParts) throws IncorrectCommandSyntaxException, CategoryNotFoundException {
        super(false, commandParts);
        timer = new InactivityTimer();
        try {
            createReminder();
        } catch (IncorrectCommandSyntaxException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createReminder() throws IncorrectCommandSyntaxException, CategoryNotFoundException {
        String reminderName = null;
        double reminderAmount = 0.0;
        String reminderDate = null;
        String reminderTime = null;
        String reminderCategory = null;

        /* Iterates through the parts of the original command string that checks and updates
        relevant reminder information. */
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("n/")) {
                reminderName = part.substring(2);
            } else if (part.startsWith("a/")) {
                reminderAmount = Double.parseDouble(part.substring(2));
                if (reminderAmount <= 0) {
                    throw new IllegalArgumentException("Sorry, inflow amount must be positive.");
                }
            } else if (part.startsWith("d/")) {
                reminderDate = part.substring(2);
            } else if (part.startsWith("t/")) {
                reminderTime = part.substring(2);
            } else if (part.startsWith("c/")) {
                reminderCategory = part.substring(2);
            } else {
                throw new IncorrectCommandSyntaxException(commandParts[0]);
            }
        }
        String reminderDateTime = reminderDate + " " + reminderTime;
        reminder = new Reminder(reminderName, reminderAmount, reminderDateTime);
        assert reminderCategory != null;
        reminder.setCategory(reminderCategory.toUpperCase());
    }

    public String execute(TransactionManager manager) {
        if (!canExecute) {
            return "Sorry, reminder not added.";
        }
        manager.addTransaction(reminder);
        return "Ok. Added reminder";
    }
}
