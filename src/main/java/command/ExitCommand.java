package command;

import financialtransactions.TransactionManager;

public class ExitCommand extends BaseCommand {

    public ExitCommand(String[] commandParts) {
        super(true,commandParts);
    }

    public String execute(TransactionManager manager) {
        return "Exiting application";
    }

    @Override
    public void createTransaction() {
    }
}
