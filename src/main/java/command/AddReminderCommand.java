package command;

import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

public class AddReminderCommand extends BaseCommand {
    public AddReminderCommand(String[] commandParts) {
        super(false, commandParts);
    }
    
    public String execute(TransactionManager manager) {
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
        Reminder reminder = new Reminder(reminderName, reminderAmount, reminderDateTime);
        reminder.setCategory(Reminder.Category.valueOf(reminderCategory.toUpperCase()));
        manager.addTransaction(reminder);
        return "Ok. Added reminder";
    }
}
