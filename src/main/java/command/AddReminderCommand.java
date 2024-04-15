package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

public class AddReminderCommand extends BaseCommand {
    String reminderName = null;
    double reminderAmount = 0.0;
    String reminderDate = null;
    String reminderTime = null;
    String reminderCategory = null;

    public AddReminderCommand(String[] commandParts) throws CategoryNotFoundException {
        super(false, commandParts);
        try {
            createTransaction();
        } catch (IncorrectCommandSyntaxException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        assert reminderCategory != null : "Reminder should have a valid category";
        reminder.setCategory(reminderCategory.toUpperCase());
    }

    public void createTransaction() throws IncorrectCommandSyntaxException {
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
    }

    public String execute(TransactionManager manager) {
        if (!canExecute) {
            return "Sorry, reminder not added.";
        }
        if (reminder.getDate().getDateTime() == null) {
            return "Please enter a valid date or time";
        }
        manager.addTransaction(reminder);
        return "Ok. Added reminder";
    }
}
