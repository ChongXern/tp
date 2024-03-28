package financialtransactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransactionManagerTest {
    @Test
    public void toSaveTest() {
        TransactionManager managerTest = new TransactionManager();

        Inflow income = new Inflow("Salary payment", 400.00, "23/05/2022 1900");
        income.setCategory(Inflow.Category.INCOME);
        managerTest.addTransaction(income);

        Outflow shopping = new Outflow("Shopping", 200, "23/05/2022 2000");
        shopping.setCategory(Outflow.Category.SHOPPING);
        managerTest.addTransaction(shopping);

        assertEquals("Salary payment|400.00|May 23 2022 07:00PM|INCOME\n" +
                "Shopping|-200.00|May 23 2022 08:00PM|SHOPPING\n", managerTest.toSave());
    }
}
