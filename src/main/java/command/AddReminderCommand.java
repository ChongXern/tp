package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

public class AddReminderCommand extends BaseCommand {
    public AddReminderCommand(String[] commandParts) {
        super(false, commandParts);
        try {
            createReminder();
        } catch (CategoryNotFoundException | IncorrectCommandSyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createReminder() throws CategoryNotFoundException, IncorrectCommandSyntaxException {
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
        reminder.setCategory(Reminder.Category.valueOf(reminderCategory.toUpperCase()));
    }

    public String execute(TransactionManager manager) {
        manager.addTransaction(reminder);
        return "Ok. Added reminder";
    }
}
