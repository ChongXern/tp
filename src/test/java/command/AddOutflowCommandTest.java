package command;

import org.junit.jupiter.api.Test;

import financialtransactions.TransactionManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddOutflowCommandTest {
    @Test
    public void testAddOutflowTest(){
        String arg = "add-outflow n/Rent a/1500.00 d/23/06/2023 t/1800 c/rent";
        String[] splitCommand = arg.split(" ");
        try{
            AddOutflowCommand addOutflowCommandcommand = new AddOutflowCommand(splitCommand);
            TransactionManager manager = new TransactionManager();
            assertEquals(addOutflowCommandcommand.execute(manager), "Ok. Added outflow");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
