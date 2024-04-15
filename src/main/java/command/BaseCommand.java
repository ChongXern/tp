package command;

import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

//@@author dylansiew
public abstract class BaseCommand {
    //@@author dylansiew
    public boolean isExit;
    protected Inflow inflow;
    protected Outflow outflow;
    protected Reminder reminder;
    protected boolean canExecute = true;
    protected TransactionManager manager;
    String[] commandParts;

    public BaseCommand(boolean isExit, String[] commandParts) {
        //@@author dylansiew
        this.isExit = isExit;
        this.commandParts = commandParts;
    }

    public void setCanExecuteToFalse() {
        this.canExecute = false;
    }

    public abstract String execute(TransactionManager manager) throws Exception;

    public abstract void createTransaction() throws Exception;

    public boolean isExit() {
        return this.isExit;
    }

    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    public Inflow getInflow() {
        return inflow;
    }

    public Outflow getOutflow() {
        return outflow;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public void setManager(TransactionManager manager) {
        this.manager = manager;
    }
}
