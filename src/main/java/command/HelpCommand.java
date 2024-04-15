package command;

import financialtransactions.TransactionManager;

public class HelpCommand extends BaseCommand {
    public HelpCommand(String[] commandParts) {
        super(false, commandParts);
    }

    @Override
    public String execute(TransactionManager manager) throws Exception {
        String baseString = "";
        baseString += "Here are the available commands: \n";
        baseString += "1) add-inflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "2) add-outflow n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "3) add-reminder n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "4) delete-inflow i/INDEX\n";
        baseString += "5) delete-outflow i/INDEX\n";
        baseString += "6) delete-reminder i/INDEX\n";
        baseString += "7) edit-inflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "8) edit-outflow i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "9) edit-reminder i/INDEX n/NAME a/AMOUNT d/DATE t/TIME c/CATEGORY\n";
        baseString += "10) set-budget a/AMOUNT\n";
        baseString += "11) view-history n/NUM [w/CHART]\n";
        baseString += "12) generate-report m/MONTH y/YEAR\n";
        baseString += "13) quit \n";
        return baseString;
    }

    @Override
    public void createTransaction() {
    }
}
