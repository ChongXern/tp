package parser;

import command.BaseCommand;
import financialtransactions.TransactionManager;
import org.junit.jupiter.api.Test;

import command.AddInflowCommand;
import command.AddOutflowCommand;
import userinterface.UI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

public class ParserTest {
    Parser parser = new Parser(new UI());
    TransactionManager manager = new TransactionManager();

    @Test
    public void addInflow_success() throws Exception {
        String command = "add-inflow n/Stocks a/700 d/28/07/2019 t/1300 c/investment";
        BaseCommand test1 = parser.parseCommand(command);
        assertEquals("Ok. Added inflow", test1.execute(manager));
    }

    @Test
    public void addOutflow_success() throws Exception {
        String command = "add-outflow n/Rent a/2000 d/29/09/2021 t/1100 c/rent";
        BaseCommand test1 = parser.parseCommand(command);
        assertEquals("Ok. Added outflow", test1.execute(manager));
    }

    @Test
    public void sampleTest() throws Exception{
        UI ui = new UI();
        Parser parser = new Parser(ui);
        String assert1 = "add-inflow n/Salary a/400.00 d/23/05/2022 t/1900 c/income";
        String assert2 = "add-outflow n/Rent a/1500.00 d/23/06/2023 t/1800 c/rent\n";
        assertInstanceOf(AddInflowCommand.class, parser.parseCommand(assert1));
        assertInstanceOf(AddOutflowCommand.class, parser.parseCommand(assert2));
    }
}
