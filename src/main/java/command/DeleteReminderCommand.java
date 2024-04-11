package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;
import user.InactivityTimer;

public class DeleteReminderCommand extends BaseCommand {
    public DeleteReminderCommand(String[] commandParts) {
        super(false, commandParts);
        timer = new InactivityTimer();
    }

    public String execute(TransactionManager manager) throws Exception {
        String reminderIndex = null;
        if (commandParts[1].startsWith("i/")) {
            reminderIndex = commandParts[1].substring(2);
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        assert reminderIndex != null : "reminderIndex should not be null";
        reminder = manager.removeReminder(Integer.parseInt(reminderIndex));
        return "Ok. Inflow " + reminder.getName() + " | " + reminder.getCategory().toString() + " deleted";
    }
}
