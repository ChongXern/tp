package storage;

import customexceptions.CategoryNotFoundException;
import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.Reminder;
import financialtransactions.TransactionManager;
import user.BaseUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import customexceptions.UserNotFoundException;

public class Storage {
    private final String filePath;
    private Scanner sc;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    //@@author chenhowy-unused 
    //This method is not used as TP should not need to support multiple users.
    public void addNewUser(String username, String password) throws Exception {
        try {
            FileWriter fw = new FileWriter(filePath + "/passwords.txt", true);
            fw.write(username + "|" + password + "\n");
            fw.close();
        } catch (IOException e) {
            throw new Exception("Error adding user");
        }
    }

    public BaseUser loadUser(String username) throws UserNotFoundException {
        File f = new File(filePath + "/passwords.txt");
        try {
            this.sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.startsWith(username)) {
                    String password = line.split("\\|")[1];
                    BaseUser newUser = new BaseUser(username, password);
                    return newUser;
                }
            }
            throw new UserNotFoundException();
        } catch (FileNotFoundException e) {
            createFileDir();
            throw new UserNotFoundException();
        }
    }
    //@@author
    public BaseUser loadMockUser(){
        return new BaseUser("Bob", "password");
    }
    
    //@@author chenhowy
    /**
     * Returns a transaction manager object containing all the previous transactions that were in the save file
     * The method will search for file "username.txt" in the ./data directory.
     * If the file does not exist, it will create a ./data directory if it does not already exist.
     * Otherwise, an empty transaction manager will be returned
     * 
     * @param username the username for the file to be loaded
     * @return Transaction Manager object with previous transactions loaded 
     * @throws CategoryNotFoundException If category does not exist.
     */
    public TransactionManager loadFile(String username) throws CategoryNotFoundException {
        File f = new File(filePath + String.format("/%s.txt", username));
        TransactionManager manager = new TransactionManager();
        Scanner sc;
        try {
            sc = new Scanner(f);
            double budget = 0.00;
            if (sc.hasNextLine()){
                budget = Double.parseDouble(sc.nextLine());
            }
            manager.setBudget(budget);
            while (sc.hasNext()) {
                String[] transactionInfo = sc.nextLine().split("\\|");
                assert transactionInfo.length == 5 : "Transaction info should have 5 arguments";
                double amount = Double.parseDouble(transactionInfo[1]);
                if (transactionInfo[4].equals("I")) {
                    Inflow inflow = new Inflow(transactionInfo[0], amount, transactionInfo[2]);
                    inflow.setCategory(transactionInfo[3]);
                    manager.addTransaction(inflow);
                } else if (transactionInfo[4].equals("O")) {
                    Outflow outflow = new Outflow(transactionInfo[0], -amount, transactionInfo[2]);
                    outflow.setCategory(transactionInfo[3]);
                    manager.addTransaction(outflow);
                } else {
                    Reminder reminder = new Reminder(transactionInfo[0], -amount, transactionInfo[2]);
                    reminder.setCategory(transactionInfo[3]);
                    manager.addTransaction(reminder);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            createFileDir();
        }
        return manager;
    }

    /**
     * Creates a file directory ./data
     * 
     * @return True if directory was created.
     */
    private boolean createFileDir() {
        File f = new File(filePath);
        return f.mkdir();
    }

    /**
     * Returns a string if file was saved successfully.
     * The method calls toSave of transaction manager which generates a string containing all past transactions.
     * This string will then be written to the file "username.txt".
     * 
     * @param username The username of the current user
     * @param tm The transaction manager of the current instance that contains the transactions
     * @return A string if the file was saved
     * @throws Exception If the file was unable to be saved.
     */
    public String saveFile(String username, TransactionManager tm) throws Exception {
        try {
            FileWriter fw = new FileWriter(filePath + String.format("/%s.txt", username));
            fw.write(tm.toSave());
            fw.close();
            return "File saved...";
        } catch (IOException e) {
            throw new Exception("Error saving file");
        }
    }
}
