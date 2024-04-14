package financialtransactions;


import storage.BarChart;

import java.time.LocalDateTime;

public class TransactionManager {
    private TransactionList<Transaction<?>> transactionList;
    private TransactionList<Inflow> inflows;
    private TransactionList<Outflow> outflows;
    private TransactionList<Reminder> reminders;
    
    private double budget = 0.00;

    public TransactionManager() {
        this.transactionList = new TransactionList<>();
        this.inflows = new TransactionList<>();
        this.outflows = new TransactionList<>();
        this.reminders = new TransactionList<>();
    }
    
    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String addTransaction(Transaction<?> transaction) {
        transactionList.addTransaction(transaction);
        transactionList.sortTransactions();
        if (transaction instanceof Inflow) {
            Inflow inflow = (Inflow) transaction;
            transactionList.setTransactionsType("Inflow");
            inflows.addTransaction(inflow);
            return String.format("Added: %s", inflow.toString());
        } else if (transaction instanceof Outflow) {
            Outflow outflow = (Outflow) transaction;
            transactionList.setTransactionsType("Outflow");
            outflows.addTransaction(outflow);
            return String.format("Added: %s", outflow.toString());
        } else if (transaction instanceof Reminder) {
            Reminder reminder = (Reminder) transaction;
            transactionList.setTransactionsType("Reminder");
            reminders.addTransaction(reminder);
            return String.format("Added: %s", reminder.toString());
        }
        return "Invalid transaction type.";
    }

    public String removeTransaction(Transaction<?> transaction) {
        int index = transactionList.getIndexOfParticularTransaction(transaction);
        transactionList.removeTransactionIndex(index);
        if (transaction instanceof Inflow) {
            Inflow inflow = (Inflow) transaction;
            int inflowIndex = inflows.getIndexOfParticularTransaction(inflow);
            inflows.removeTransactionIndex(inflowIndex);
            return String.format("Removed: %s", inflow.toString());
        } else if (transaction instanceof Outflow) {
            Outflow outflow = (Outflow) transaction;
            int outflowIndex = outflows.getIndexOfParticularTransaction(outflow);
            outflows.removeTransactionIndex(outflowIndex);
            return String.format("Removed: %s", outflow.toString());
        } else if (transaction instanceof Reminder) {
            Reminder reminder = (Reminder) transaction;
            int reminderIndex = reminders.getIndexOfParticularTransaction(reminder);
            reminders.removeTransactionIndex(reminderIndex);
            return String.format("Removed: %s", reminder.toString());
        }
        return "Invalid transaction type.";
    }

    public Inflow removeInflow(int index) throws Exception {
        int numOfInflows = inflows.getTransactionListSize();
        Inflow transactionRemoved = (Inflow) inflows.getNthTransaction(numOfInflows - index);
        transactionList.removeTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionRemoved));
        transactionList.sortTransactions();

        inflows.removeTransactionIndex(numOfInflows - index);
        inflows.sortTransactions();
        return transactionRemoved;
    }

    public Outflow removeOutflow(int index) throws Exception {
        int numOfOutflows = outflows.getTransactionListSize();
        Outflow transactionRemoved = (Outflow) outflows.getNthTransaction(numOfOutflows - index);
        transactionList.removeTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionRemoved));
        transactionList.sortTransactions();

        outflows.removeTransactionIndex(numOfOutflows - index);
        outflows.sortTransactions();
        return transactionRemoved;
    }

    public Reminder removeReminder(int index) throws Exception {
        int numOfReminders = reminders.getTransactionListSize();
        Reminder transactionRemoved = (Reminder) reminders.getNthTransaction(numOfReminders - index);
        transactionList.removeTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionRemoved));
        reminders.removeTransactionIndex(numOfReminders - index);
        return transactionRemoved;
    }

    public Inflow editInflow(int index, Transaction<?> updatedTransaction) throws Exception {
        int numOfInflows = inflows.getTransactionListSize();
        Inflow transactionEdited = (Inflow) inflows.getNthTransaction(numOfInflows - index);
        transactionList.editTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionEdited),
                updatedTransaction);
        transactionList.sortTransactions();

        inflows.editTransactionIndex(numOfInflows - index, (Inflow) updatedTransaction);
        inflows.sortTransactions();
        return transactionEdited;
    }

    public Outflow editOutflow(int index, Transaction<?> updatedTransaction) throws Exception {
        int numOfOutflows = outflows.getTransactionListSize();
        Transaction<?> transactionEdited = outflows.getNthTransaction(numOfOutflows - index);
        transactionList.editTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionEdited),
                updatedTransaction);
        transactionList.sortTransactions();

        outflows.editTransactionIndex(numOfOutflows - index, (Outflow) updatedTransaction);
        outflows.sortTransactions();
        return (Outflow) transactionEdited;
    }

    public boolean editReminder(int index, Transaction<?> updatedTransaction) throws Exception {
        int numOfReminders = reminders.getTransactionListSize();
        Transaction<?> transactionEdited = reminders.getNthTransaction(numOfReminders - index);
        transactionList.editTransactionIndex(transactionList.getIndexOfParticularTransaction(transactionEdited),
                updatedTransaction);
        return reminders.editTransactionIndex(numOfReminders - index, (Reminder) updatedTransaction);
    }

    public double getTotalBalance() {
        double inflowBalance = inflows.getBalance();
        double outflowBalance = outflows.getBalance();
        return inflowBalance + outflowBalance;
    }

    @Override
    public String toString() {
        return "Inflows:\n" + inflows.toString() + "\nOutflows:\n" + outflows.toString();
    }

    public String showLastNTransactions(int n, boolean isIncludeBarChart) throws Exception {
        int listSize = transactionList.getTransactionListSize();
        if (n > listSize) {
            throw new Exception("Invalid index");
        }
        int index = 1;
        String returnedText = "Inflows:\nTransactions:\n";
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Inflow) {
                returnedText += String.format("%d)  %s\n", index, transactionList.getNthTransaction(i).toString());
                index++;
            }
        }

        index = 1;
        returnedText += "\nOutflows:\nTransactions:\n";
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Outflow) {
                returnedText += String.format("%d)  %s\n", index, transactionList.getNthTransaction(i).toString());
                index++;
            }
        }

        index = 1;
        returnedText += "\nReminders:\nTransactions:\n";
        for (int i = listSize - 1; i > listSize - n - 1; i--) {
            Transaction<?> transaction = transactionList.getNthTransaction(i);
            if (transaction instanceof Reminder) {
                returnedText += String.format("%d)  %s\n", index, transactionList.getNthTransaction(i).toString());
                index++;
            }
        }

        if (isIncludeBarChart) {
            BarChart<Transaction<?>> barChart = new BarChart<>(transactionList);
            returnedText = barChart.generateBarChart() + returnedText;
        }
        
        return returnedText;
    }

    public String toSave() {
        return String.format("%.2f\n", budget) + transactionList.toSave();
    }
    
    //@@author chenhowy

    /**
     * Returns a string that contains some basic information.
     * This string is to be displayed on launch of the app.
     * It contains amount spent in the current month, budget left and reminders.
     * 
     * @return A string of basic information to show to the user on launch.
     */
    public String generateQuickReport() {
        String baseString = "";
        baseString += String.format("You have spent " +
                "%.2f in the current month.\n", outflows.totalSpentInCurrentMonth());
        baseString += String.format("With a budget of " +
                "%.2f, you have %.2f left to spend.\n", budget, budget - outflows.totalSpentInCurrentMonth() - 
                reminders.totalSpentInCurrentMonth());
        baseString += String.format("You have " +
                "%d upcoming payments that require your attention", reminders.getRemindersAfterToday());
        return baseString;
    }

    /**
     * Returns a string that contains information about a certain month that has passed.
     * It first checks if the month has already passed.
     * It will tell the user their income, spending and savings from that month.
     * 
     * @param monthString A string of the certain month.
     * @param month An integer value of the certain month.
     * @param year An integer value of the certain year.
     * @return A string that contains information about the month.
     */
    public String generateFullReport(String monthString, int month, int year) {
        if (!isBefore(month, year)) {
            return "Please enter a month that is before the current month";
        }
        String baseString = "";
        baseString += String.format("In the month of %s %s, " +
                "you had an income of $%.2f.\n", monthString, year, inflows.getTotalSpentInMonth(month, year));
        baseString += String.format("You spent $%.2f.\n", outflows.getTotalSpentInMonth(month, year) + 
                reminders.getTotalSpentInMonth(month, year));
        baseString += String.format("You managed to save $%.2f!", 
                inflows.getTotalSpentInMonth(month, year) - outflows.getTotalSpentInMonth(month, year) - 
                        reminders.getTotalSpentInMonth(month, year));
        return baseString;
    }

    /**
     * Returns true if the month is before the current month.
     * If the year is the same as the current year, the month must be before the current month.
     * If the year is before the current year, the month does not matter.
     * 
     * @param month Integer value of the month in question.
     * @param year Integer value of the year in question.
     * @return True if the month is before the current month.
     */
    public boolean isBefore(int month, int year) {
        LocalDateTime today = LocalDateTime.now();
        int todayMonth = today.getMonthValue();
        int todayYear = today.getYear();
        if (year < todayYear) {
            return true;
        } else if (year == todayYear && month < todayMonth) {
            return true;
        } else {
            return false;
        }
    }
    //@@author    
    public int getTransactionListSize() {
        return transactionList.getTransactionListSize();
    }

    public int getNumOfInflows() {
        return inflows.getTransactionListSize();
    }

    public int getNumOfOutflows() {
        return outflows.getTransactionListSize();
    }

    public int getNumOfReminders() {
        return reminders.getTransactionListSize();
    }

    public int findTransactionIndex(Transaction<?> transaction) {
        return transactionList.getIndexOfParticularTransaction(transaction);
    }

    public Inflow getNthInflowFromList(int n) throws Exception {
        return (Inflow) inflows.getNthTransaction(n - 1);
    }

    public Outflow getNthOutflowFromList(int n) throws Exception {
        return (Outflow) outflows.getNthTransaction(n - 1);
    }

    public Reminder getNthReminderFromList(int n) throws Exception {
        return (Reminder) reminders.getNthTransaction(n -1);
    }
}
