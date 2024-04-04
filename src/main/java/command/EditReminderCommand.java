package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

public class EditReminderCommand extends BaseCommand {
    public EditReminderCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        int reminderIndex = -1;
        String reminderName = null;
        double reminderAmount = 0.0;
        String reminderDate = null;
        String reminderTime = null;
        String reminderCategory = null;

        /* Iterates through the parts of the original command string that checks and updates
        relevant reminder information. */
        for (int i = 1; i < commandParts.length; i++) {
            String part = commandParts[i];
            if (part.startsWith("i/")) {
                reminderIndex = Integer.parseInt(part.substring(2));
            } else if (part.startsWith("n/")) {
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
        Reminder updatedReminder = new Reminder(reminderName, reminderAmount, reminderDateTime);
        assert reminderCategory != null : "reminderCategory should not be null";
        try {
            updatedReminder.setCategory(reminderCategory.toUpperCase());
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
            e.disableExecute(this);
        }
        if (!canExecute) {
            return "Sorry, reminder not edited.";
        }
        manager.editOutflow(reminderIndex, updatedReminder);
        return "Ok. Edited reminder";
    }
}
