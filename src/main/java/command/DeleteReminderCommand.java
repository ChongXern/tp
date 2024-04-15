package command;

import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.TransactionManager;

public class DeleteReminderCommand extends BaseCommand {
    private int reminderIndex = -1;
    private TransactionManager manager;

    public DeleteReminderCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public void createTransaction() throws IncorrectCommandSyntaxException {
        if (commandParts[1].startsWith("i/")) {
            reminderIndex = Integer.parseInt(commandParts[1].substring(2));
        } else {
            throw new IncorrectCommandSyntaxException(commandParts[0]);
        }
        assert reminderIndex != -1 : "The reminderIndex should exist";
        try {
            reminder = manager.getNthReminderFromList(reminderIndex);
        } catch (Exception e) {
            System.out.println("Sorry. " + e.getMessage());
        }
    }

    public void setManager(TransactionManager manager) {
        this.manager = manager;
    }

    public String execute(TransactionManager manager) throws Exception {
        assert reminderIndex != -1 : "reminderIndex should exist";
        manager.removeReminder(reminderIndex);
        return "Ok. Reminder " + reminder.getName() + " | " + reminder.getCategory().toString() + " deleted";
    }
}
