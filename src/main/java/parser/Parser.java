package parser;

import command.AddInflowCommand;
import command.AddOutflowCommand;
import command.AddReminderCommand;
import command.DeleteInflowCommand;
import command.DeleteOutflowCommand;
import command.DeleteReminderCommand;
import command.SetBudgetCommand;
import command.EditInflowCommand;
import command.EditOutflowCommand;
import command.EditReminderCommand;
import command.UndoCommand;
import command.ExitCommand;
import command.ViewHistoryCommand;
import command.BaseCommand;
import command.HelpCommand;
import customexceptions.IncompletePromptException;
import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.Reminder;
import userinterface.UI;

public class Parser {
    UI ui;

    // For undo functionality
    String[] lastCommandParts = null;
    BaseCommand lastCommand;
    Inflow lastInflow;
    Outflow lastOutflow;
    Reminder lastReminder;

    public Parser(UI ui) {
        this.ui = ui;
    }


    public BaseCommand parseCommand(String command) throws Exception {
        String[] commandParts = command.split("\\s+");
        String action = commandParts[0];
        switch (action) {
        case "help":
            return new HelpCommand(commandParts);
        case "add-inflow":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            lastCommandParts = commandParts;
            lastCommand = new AddInflowCommand(commandParts);
            lastInflow = lastCommand.getInflow();
            return lastCommand;
        case "add-outflow":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            lastCommandParts = commandParts;
            lastCommand = new AddOutflowCommand(commandParts);
            lastOutflow = lastCommand.getOutflow();
            return lastCommand;
        case "add-reminder":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            lastCommandParts = commandParts;
            lastCommand = new AddReminderCommand(commandParts);
            lastReminder = lastCommand.getReminder();
            return lastCommand;
        case "delete-inflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            lastCommandParts = commandParts;
            lastCommand = new DeleteInflowCommand(commandParts);
            lastInflow = lastCommand.getInflow();
            if (lastInflow == null) {
                System.out.println("Last inflow is null");
            }
            return lastCommand;
        case "delete-outflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            lastCommandParts = commandParts;
            lastCommand = new DeleteOutflowCommand(commandParts);
            lastOutflow = lastCommand.getOutflow();
            return lastCommand;
        case "delete-reminder":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            lastCommandParts = commandParts;
            lastCommand = new DeleteReminderCommand(commandParts);
            lastReminder = lastCommand.getReminder();
            return lastCommand;
        case "edit-inflow":
            if (commandParts.length < 7) {
                throw new IncompletePromptException(command);
            }
            return new EditInflowCommand(commandParts);
        case "edit-outflow":
            if (commandParts.length < 7) {
                throw new IncompletePromptException(command);
            }
            return new EditOutflowCommand(commandParts);
        case "edit-reminder":
            if (commandParts.length < 7) {
                throw new IncompletePromptException(command);
            }
            return new EditReminderCommand(commandParts);
        case "set-budget":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new SetBudgetCommand(commandParts);
        case "view-history":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new ViewHistoryCommand(commandParts);
        case "undo":
            UndoCommand undoCommand = new UndoCommand(lastCommandParts);
            if (lastCommandParts[0].contains("inflow")) {
                undoCommand.setInflow(lastInflow);
            } else if (lastCommandParts[0].contains("outflow")) {
                undoCommand.setOutflow(lastOutflow);
            } else if (lastCommandParts[0].contains("reminder")) {
                undoCommand.setReminder(lastReminder);
            }
            lastCommandParts = null;
            return undoCommand;
        case "quit":
            return new ExitCommand(commandParts);
        default:
            throw new IncompletePromptException(command);
            // throw new Exception("Invalid command");
        }
    }
}
