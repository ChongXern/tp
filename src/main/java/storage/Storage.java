//@@author chenhowy
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
    
    public void addNewUser(String username, String password) throws IOException{
        FileWriter fw = new FileWriter(filePath + "/passwords.txt", true);
        fw.write(username + "|" + password + "\n");
        fw.close();
    }

    public BaseUser loadMockUser(){
        return new BaseUser("Bob", "password");
    }
    public BaseUser loadUser(String username) throws UserNotFoundException {
        File f = new File(filePath + "/passwords.txt");
        try {
            this.sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                if (line.startsWith(username)) {
                    String password = line.split("\\|")[1];
                    return new BaseUser(username, password);
                }
            }
            throw new UserNotFoundException();
        } catch (FileNotFoundException e) {
            createFileDir();
            return null;
        }
    }
    
    public TransactionManager loadFile(String username) throws CategoryNotFoundException{
        File f = new File(filePath + String.format("/%s.txt", username));
        TransactionManager manager = new TransactionManager();
        try {
            Scanner sc = new Scanner(f);
            double budget = 0.00;
            if(sc.hasNext()){
                budget = Double.parseDouble(sc.nextLine());
            }
            manager.setBudget(budget);
            while (sc.hasNext()) {
                String[] transactionInfo = sc.nextLine().split("\\|");
                assert transactionInfo.length == 5 : "Transaction info should have 5 arguments";
                double amount = Double.parseDouble(transactionInfo[1]);
                if (transactionInfo[4].equals("I")) {
                    Inflow inflow = new Inflow(transactionInfo[0], amount, transactionInfo[2]);
                    inflow.setCategory(Inflow.Category.valueOf(transactionInfo[3]));
                    manager.addTransaction(inflow);
                } else if (transactionInfo[4].equals("O")){
                    Outflow outflow = new Outflow(transactionInfo[0], -amount, transactionInfo[2]);
                    outflow.setCategory(Outflow.Category.valueOf(transactionInfo[3]));
                    manager.addTransaction(outflow);
                } else {
                    Reminder reminder = new Reminder(transactionInfo[0], -amount, transactionInfo[2]);
                    reminder.setCategory(Reminder.Category.valueOf(transactionInfo[3]));
                    manager.addTransaction(reminder);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            createFileDir();
        }
        return manager;
    }

    private void createFileDir() {
        File f = new File(filePath);
        f.mkdir();
    }

    public String saveFile(String username, TransactionManager tm) {
        try {
            FileWriter fw = new FileWriter(filePath + String.format("/%s.txt", username));
            fw.write(tm.toSave());
            fw.close();
            return "File saved...";
        } catch (IOException e) {
            return "Unable to save tasks!";
        }
    }
}
