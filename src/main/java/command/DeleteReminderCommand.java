package command;

import financialtransactions.TransactionManager;

public class DeleteReminderCommand extends BaseCommand {
    public DeleteReminderCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public String execute(TransactionManager manager) throws Exception {
        String reminderIndex = null;
        for (String part : commandParts) {
            if (part.startsWith("i/")) {
                reminderIndex = part.substring(2);
            }
        }
        assert reminderIndex != null : "reminderIndex should not be null";
        reminder = manager.removeReminder(Integer.parseInt(reminderIndex));
        return "Ok. Inflow " + reminder.getName() + " | " + reminder.getCategory().toString() + " deleted";
    }
}
