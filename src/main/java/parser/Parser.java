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
import command.GenerateReportCommand;
import customexceptions.IncompletePromptException;
import userinterface.UI;

import financialtransactions.TransactionManager;

public class Parser {
    UI ui;

    // For undo functionality
    String[] lastCommandParts;
    BaseCommand lastCommand;
    UndoCommand undoCommand = new UndoCommand(new String[]{"undo", "command"});
    String lastAction;
    TransactionManager manager; // Fetches transactions based on indexes only.
    public Parser(UI ui) {
        this.ui = ui;
    }

    public void setManager(TransactionManager manager) {
        this.manager = manager;
        undoCommand.setManager(manager);
    }

    public BaseCommand parseCommand(String command) throws Exception {
        String[] commandParts = command.split("\\s+");
        String action = commandParts[0];
        switch (action) {
        case "help":
            undoCommand.setCanUndo(false, null);
            lastAction = null;
            return new HelpCommand(commandParts);
        case "add-inflow":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            lastCommand = new AddInflowCommand(commandParts);
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);
            undoCommand.setInflow(lastCommand.getInflow());
            return lastCommand;
        case "add-outflow":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            lastCommand = new AddOutflowCommand(commandParts);
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);
            undoCommand.setOutflow(lastCommand.getOutflow());
            return lastCommand;
        case "add-reminder":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            lastCommand = new AddReminderCommand(commandParts);
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);
            undoCommand.setReminder(lastCommand.getReminder());
            return lastCommand;
        case "delete-inflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);

            lastCommand = new DeleteInflowCommand(commandParts);
            lastCommand.setManager(manager);
            lastCommand.createTransaction();
            undoCommand.setInflow(lastCommand.getInflow());
            return lastCommand;
        case "delete-outflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            lastCommand = new DeleteOutflowCommand(commandParts);
            lastCommand.setManager(manager);
            lastCommand.createTransaction();
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);
            undoCommand.setOutflow(lastCommand.getOutflow());
            return lastCommand;
        case "delete-reminder":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            lastCommand = new DeleteReminderCommand(commandParts);
            lastCommand.setManager(manager);
            lastCommand.createTransaction();
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);
            undoCommand.setReminder(lastCommand.getReminder());
            return lastCommand;
        case "edit-inflow":
            if (commandParts.length < 7) {
                throw new IncompletePromptException(command);
            }
            lastCommand = new EditInflowCommand(commandParts);
            lastCommand.setManager(manager);
            lastCommand.createTransaction();
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);
            undoCommand.setInflow(lastCommand.getInflow());
            return lastCommand;
        case "edit-outflow":
            if (commandParts.length < 7) {
                throw new IncompletePromptException(command);
            }
            lastCommand = new EditOutflowCommand(commandParts);
            lastCommand.setManager(manager);
            lastCommand.createTransaction();
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);
            undoCommand.setOutflow(lastCommand.getOutflow());
            return lastCommand;
        case "edit-reminder":
            if (commandParts.length < 7) {
                throw new IncompletePromptException(command);
            }
            lastCommand = new EditReminderCommand(commandParts);
            lastCommand.setManager(manager);
            lastCommand.createTransaction();
            lastAction = action;
            lastCommandParts = commandParts;
            undoCommand.setCanUndo(true, commandParts);
            undoCommand.setReminder(lastCommand.getReminder());
            return lastCommand;
        case "set-budget":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            lastAction = null;
            undoCommand.setCanUndo(false, null);
            return new SetBudgetCommand(commandParts);
        case "view-history":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            lastAction = null;
            return new ViewHistoryCommand(commandParts);
        case "generate-report":
            if (commandParts.length < 3) {
                throw new IncompletePromptException(command);
            }
            undoCommand.setCanUndo(false, null);
            lastAction = null;
            return new GenerateReportCommand(commandParts);
        case "undo":
            undoCommand.allowExecute(lastAction);
            lastAction = null;
            return undoCommand;
        case "quit":
            return new ExitCommand(commandParts);
        default:
            throw new IncompletePromptException(command);
        }
    }
}