package command;

import customexceptions.CategoryNotFoundException;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

public class AddReminderCommand extends BaseCommand {
    public AddReminderCommand(String[] commandParts) throws CategoryNotFoundException{
        super(false, commandParts);
        createReminder();
    }
    
    private void createReminder() throws CategoryNotFoundException {
        String reminderName = null;
        double reminderAmount = 0.0;
        String reminderDate = null;
        String reminderTime = null;
        String reminderCategory = null;

        for (String part : commandParts) {
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
