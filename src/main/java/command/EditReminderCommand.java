package command;

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

        for (String part : commandParts) {
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
            }
        }

        String reminderDateTime = reminderDate + " " + reminderTime;
        Reminder updatedReminder = new Reminder(reminderName, reminderAmount, reminderDateTime);
        assert reminderCategory != null : "reminderCategory should not be null";
        updatedReminder.setCategory(Reminder.Category.valueOf(reminderCategory.toUpperCase()));
        manager.editOutflow(reminderIndex, updatedReminder);
        return "Ok. Edited reminder";
    }
}
