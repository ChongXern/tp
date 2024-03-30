package command;

import org.junit.jupiter.api.Test;

import financialtransactions.TransactionManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddOutflowCommandTest{
    @Test
    public void testAddOutflowTest(){
        String arg = "add-outflow n/Rent a/1500.00 d/23/06/2023 t/1800 c/rent";
        String[] splitCommand = arg.split(" ");
        AddOutflowCommand command = new AddOutflowCommand(splitCommand);

        TransactionManager manager = new TransactionManager();
        assertEquals(command.execute(manager), "Ok. Added outflow");
    }
}
