package storage;

import financialtransactions.Inflow;
import financialtransactions.Outflow;
import financialtransactions.TransactionManager;
import user.BaseUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import customexceptions.UserNotFoundExcption;

public class Storage {
    private final String filePath;
    private Scanner sc;
    public Storage(String filePath) {
        this.filePath = filePath;
    }
    
    public void addNewUser(String username, String password) {
        try {
            FileWriter fw = new FileWriter(filePath + "/passwords.txt", true);
            fw.write(username + "|" + password + "\n");
            fw.close();
        } catch (IOException e) {
            System.out.println("Could not add user");
        }
    }
    
    public BaseUser loadUser(String username) throws UserNotFoundExcption{
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
            throw new UserNotFoundExcption();
        } catch (FileNotFoundException e) {
            createFileDir();
            return null;
        }
    }
    
    public TransactionManager loadFile(String username) {
        File f = new File(filePath + String.format("/%s.txt", username));
        TransactionManager manager = new TransactionManager();
        try {
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String[] transactionInfo = sc.nextLine().split("\\|");
                assert transactionInfo.length == 4 : "Transaction info should have 4 arguments";
                double amount = Double.parseDouble(transactionInfo[1]);
                if (!transactionInfo[1].startsWith("-")) {
                    Inflow inflow = new Inflow(transactionInfo[0], amount, transactionInfo[2]);
                    inflow.setCategory(Inflow.Category.valueOf(transactionInfo[3]));
                    manager.addTransaction(inflow);
                } else {
                    Outflow outflow = new Outflow(transactionInfo[0], -amount, transactionInfo[2]);
                    outflow.setCategory(Outflow.Category.valueOf(transactionInfo[3]));
                    manager.addTransaction(outflow);
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

    public void saveFile(String username, TransactionManager tm) {
        try {
            FileWriter fw = new FileWriter(filePath + String.format("/%s.txt", username));
            fw.write(tm.toSave());
            fw.close();
        } catch (IOException e) {
            System.out.println("Unable to save tasks!");
        }
    }
}
