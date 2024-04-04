package financialtransactions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OutflowTest {
    @Test
    public void testIsValidCategory(){
        Outflow outflow = new Outflow("2024 Sem 2 School Fees", 999999.99, "2024-04-01");
        assertTrue(outflow.isValidCategory("EDUCATION"));
    }
}
