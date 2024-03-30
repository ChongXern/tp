package parser;

import customexceptions.IncompletePromptException;
import command.AddInflowCommand;
import command.AddOutflowCommand;
import command.AddReminderCommand;
import command.BaseCommand;
import command.DeleteOutflowCommand;
import command.ExitCommand;
import command.ViewHistoryCommand;
import command.EditInflowCommand;
import command.HelpCommand;
import command.DeleteInflowCommand;
import command.EditOutflowCommand;
import command.DeleteReminderCommand;
import command.EditReminderCommand;
import command.SetBudgetCommand;

import userinterface.UI;

public class Parser {
    UI ui;

    public Parser(UI ui) {
        this.ui = ui;
    }


    public BaseCommand parseCommand(String command) throws IncompletePromptException, Exception {
        String[] commandParts = command.split("\\s+");
        String action = commandParts[0];
        switch (action) {
        case "help":
            return new HelpCommand(commandParts);
        case "add-inflow":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            return new AddInflowCommand(commandParts);
        case "add-outflow":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            return new AddOutflowCommand(commandParts);
        case "add-reminder":
            if (commandParts.length < 6) {
                throw new IncompletePromptException(command);
            }
            return new AddReminderCommand(commandParts);
        case "delete-inflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new DeleteInflowCommand(commandParts);
        case "delete-outflow":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new DeleteOutflowCommand(commandParts);
        case "delete-reminder":
            if (commandParts.length < 2) {
                throw new IncompletePromptException(command);
            }
            return new DeleteReminderCommand(commandParts);
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
        case "quit":
            return new ExitCommand(commandParts);
        default:
            throw new IncompletePromptException(command);
            // throw new Exception("Invalid command");
        }
    }
}
