package command;

import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;
import user.InactivityTimer;

public class UndoCommand extends BaseCommand {
    private Inflow inflow;
    private Outflow outflow;
    private Reminder reminder;
    private String action;
    private boolean canUndo = false;
    private InactivityTimer timer;

    public UndoCommand(String[] commandParts) {
        super(false, commandParts);
        this.timer = new InactivityTimer();
        action = commandParts[0];
    }

    public void setInflow(Inflow inflow) {
        this.inflow = inflow;
        this.outflow = null;
        this.reminder = null;
    }
    public void setOutflow(Outflow outflow) {
        this.inflow = null;
        this.outflow = outflow;
        this.reminder = null;
    }
    public void setReminder(Reminder reminder) {
        this.inflow = null;
        this.outflow = null;
        this.reminder = reminder;
    }

    public String execute(TransactionManager manager) throws Exception {
        if (!timer.canUndo()) {
            return ("Sorry, no longer able to undo the last action as 10 seconds have passed.");
        }
        if (commandParts == null) {
            return ("Sorry, there is no action to undo.");
        }
        switch (action) {
        case "delete-inflow":
            canUndo = true;
            int inflowIndex = Integer.parseInt(commandParts[1].substring(2));
            Inflow inflowToRemove = manager.getNthInflowFromList(inflowIndex);
            manager.addTransaction(inflowToRemove);
            break;
        case "delete-outflow":
            canUndo = true;
            int outflowIndex = Integer.parseInt(commandParts[1].substring(2));
            Outflow outflowToRemove = manager.getNthOutflowFromList(outflowIndex);
            manager.addTransaction(outflowToRemove);
            break;
        case "delete-reminder":
            canUndo = true;
            int reminderIndex = Integer.parseInt(commandParts[1].substring(2));
            Reminder reminderToRemove = manager.getNthReminderFromList(reminderIndex);
            manager.addTransaction(reminderToRemove);
            break;
        case "add-inflow":
            canUndo = true;
            manager.removeTransaction(inflow);
            break;
        case "add-outflow":
            canUndo = true;
            manager.removeTransaction(outflow);
            break;
        case "add-reminder":
            canUndo = true;
            manager.removeTransaction(reminder);
            break;
        default:
            return "Sorry, unable to undo action.";
        }
        if (canUndo) {
            return "Ok. " + action + " has been undone.";
        }
        return "Sorry, unable to undo action.";
    }
}
