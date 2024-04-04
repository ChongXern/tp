package command;

import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;

public abstract class BaseCommand {
    protected boolean canExecute = true;
    public boolean isExit;
    protected Inflow inflow;
    protected Outflow outflow;
    protected Reminder reminder;
    String[] commandParts;
    public BaseCommand(Boolean isExit, String[] commandParts){
        this.isExit = isExit;
        this.commandParts = commandParts;
    }
    public void setCanExecuteToFalse() {
        this.canExecute = false;
    }
    public abstract String execute(TransactionManager manager) throws Exception;

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
}
