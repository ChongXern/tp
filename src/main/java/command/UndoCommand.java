package command;

import customexceptions.UndoNotPermittedException;
import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

//@@author ChongXern
public class UndoCommand extends BaseCommand {
    private static final int PERMITTED_UNDO_TIME = 10_000;
    private int index;
    private Inflow inflow;
    private Outflow outflow;
    private Reminder reminder;
    private String action;
    private boolean canUndo = false;
    private boolean canExecute;
    private long startTime;
    private String[] lastCommandParts;

    public UndoCommand(String[] commandParts) {
        super(false, commandParts);
        if (commandParts[0].contains("flow") || commandParts[0].contains("reminder")) {
            lastCommandParts = commandParts;
        }
        System.out.println("NEW RHIQ");
        action = commandParts[0];
        System.out.println("ACTION IS " + action);
        canExecute = false;
    }

    public void setInflow(Inflow inflow) {
        this.inflow = inflow;
        System.out.println("THIS SET INFLOW IS " + this.inflow.getName());
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

    public void setCanUndo(boolean canUndo, String[] lastCommandParts) {
        this.canUndo = canUndo;
        this.lastCommandParts = lastCommandParts;
        startTime = System.currentTimeMillis();
    }

    private boolean didUndoTimerRunout() {
        long timeDifference = System.currentTimeMillis() - startTime;
        return timeDifference < PERMITTED_UNDO_TIME;
    }

    public void allowExecute(String lastAction) {
        canExecute = (lastAction != null);
        System.out.println("INFLOW IS: " + inflow);
    }

    public String execute(TransactionManager manager) throws Exception {
        if (!canExecute) {
            System.out.println("CANNOT EXECUTE UNDO");
            throw new UndoNotPermittedException(true, true);
        }
        System.out.println("EXECUTING COMMAND UNDO");
        switch (lastCommandParts[0]) { // Compute how to undo the command to be undone
        case "delete-inflow":
            System.out.println("ADDING BACK INFLOW");
            canUndo = true;
            index = Integer.parseInt(lastCommandParts[1].substring(2));
            System.out.println(index);
            Inflow inflowToRemove = manager.getNthInflowFromList(index);
            return manager.addTransaction(inflowToRemove);
        case "delete-outflow":
            canUndo = true;
            index = Integer.parseInt(lastCommandParts[1].substring(2));
            Outflow outflowToRemove = manager.getNthOutflowFromList(index);
            return manager.addTransaction(outflowToRemove);
        case "delete-reminder":
            canUndo = true;
            index = Integer.parseInt(lastCommandParts[1].substring(2));
            Reminder reminderToRemove = manager.getNthReminderFromList(index);
            return manager.addTransaction(reminderToRemove);
        case "add-inflow":
            System.out.println("DELETING PREVIOUS INFLOW");
            canUndo = true;
            System.out.println("THIS INFLOW TO BE DELETED IS " + inflow);
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
        case "edit-inflow":
            canUndo = true;
            index = Integer.parseInt(lastCommandParts[1].substring(2));
            manager.editInflow(index, inflow);
            break;
        case "edit-outflow":
            canUndo = true;
            index = Integer.parseInt(lastCommandParts[1].substring(2));
            manager.editOutflow(index, outflow);
            break;
        case "edit-reminder":
            canUndo = true;
            index = Integer.parseInt(lastCommandParts[1].substring(2));
            manager.editReminder(index, reminder);
            break;
        default:
            throw new UndoNotPermittedException(didUndoTimerRunout(), true);
        }
        if (canUndo) {
            canUndo = false;
            return "Ok. " + action + " has been undone.";
        }
        throw new UndoNotPermittedException(didUndoTimerRunout(), true);
    }

    @Override
    public void createTransaction() {
    }
}
