package command;

import financialtransactions.TransactionManager;

public class GenerateReportCommand extends BaseCommand {

    public GenerateReportCommand(String[] commandParts) {
        super(false,commandParts);
    }

    public String execute(TransactionManager manager) throws Exception{
        String monthString = "";
        int month = 0;
        int year = 0;
        if (commandParts[1].startsWith("m/")) {
            monthString = commandParts[1].substring(2);
        } 
        switch (monthString) {
        case "JAN":
            month = 1;
            break;
        case "FEB":
            month = 2;
            break;
        case "MAR":
            month = 3;
            break;
        case "APR":
            month = 4;
            break;
        case "MAY":
            month = 5;
            break;
        case "JUN":
            month = 6;
            break;
        case "JUL":
            month = 7;
            break;
        case "AUG":
            month = 8;
            break;
        case "SEP":
            month = 9;
            break;
        case "OCT":
            month = 10;
            break;
        case "NOV":
            month = 11;
            break;
        case "DEC":
            month = 12;
            break;
        default:
            return "Invalid month format. Month must be in the form of MMM";
        }
        if (commandParts[2].startsWith("y/")) {
            year = Integer.parseInt(commandParts[2].substring(2));
            if (year <= 0) {
                return "Year must be a valid year";
            }
        }
        return manager.generateFullReport(monthString, month, year);
    }

    @Override
    public void createTransaction() {
    }

}
