package command;

import customexceptions.CategoryNotFoundException;
import customexceptions.IncorrectCommandSyntaxException;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

public class EditReminderCommand extends BaseCommand {
    private int reminderIndex = -1;
    private String reminderName = null;
    private double reminderAmount = 0.0;
    private String reminderDate = null;
    private String reminderTime = null;
    private String reminderCategory = null;
    private Reminder updatedReminder;
    private TransactionManager manager;

    public EditReminderCommand(String[] commandParts) {
        super(false, commandParts);
    }

    public void setManager(TransactionManager manager) {
        this.manager = manager;
    }

    @Override
    public void createTransaction() throws IncorrectCommandSyntaxException {
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
        assert reminderIndex != -1 : "outflow index should exist.";
        assert reminderCategory != null : "outflow category should not be null";
        try {
            updatedReminder.setCategory(reminderCategory);
            reminder = manager.getNthReminderFromList(reminderIndex);
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Sorry. " + e.getMessage());
        }
        updatedReminder = new Reminder(reminderName, reminderAmount, reminderDate + " " + reminderTime);
    }

    public String execute(TransactionManager manager) throws Exception {
        if (!canExecute) {
            return "Sorry, reminder not edited.";
        }
        manager.editReminder(reminderIndex, updatedReminder);
        return "Ok. Edited reminder";
    }
}
