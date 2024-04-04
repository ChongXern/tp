package command;

import org.junit.jupiter.api.Test;

import financialtransactions.TransactionManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddInflowCommandTest {
    @Test
    public void testAddInflow() {
        String arg = "add-inflow n/Salary a/400.00 d/23/05/2022 t/1900 c/income";
        String[] splitCommand = arg.split(" ");
        AddInflowCommand command;
        try{
            command = new AddInflowCommand(splitCommand);
        } catch (Exception e){
            System.out.println(e.getMessage());
            return;
        }


        TransactionManager manager = new TransactionManager();
        assertEquals(command.execute(manager), "Ok. Added inflow");
    }
}
