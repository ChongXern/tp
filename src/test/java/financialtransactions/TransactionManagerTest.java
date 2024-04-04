package financialtransactions;

import customexceptions.CategoryNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionManagerTest {
    @Test
    public void toSaveTest() {
        TransactionManager managerTest = new TransactionManager();

        Inflow income = new Inflow("Salary payment", 400.00, "23/05/2022 1900");
        try {
            income.setCategory("INCOME");
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
        managerTest.addTransaction(income);

        Outflow shopping = new Outflow("Shopping", 200, "23/05/2022 2000");
        try {
            shopping.setCategory("Shopping");
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
        managerTest.addTransaction(shopping);

        Reminder bill = new Reminder("Water bills", 64.30, "25/06/2025 1500");
        try {
            bill.setCategory("utilities");
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
        managerTest.addTransaction(bill);

        managerTest.setBudget(1500);
        
        assertEquals("1500.00\n" +
                "Salary payment|400.00|May 23 2022 07:00PM|INCOME|I\n" +
                "Shopping|-200.00|May 23 2022 08:00PM|SHOPPING|O\n" +
                "Water bills|-64.30|Jun 25 2025 03:00PM|UTILITIES|R\n", managerTest.toSave());
    }
}
