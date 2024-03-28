package financialtransactions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutflowTest {
    @Test
    public void testSetCategory() {
        Outflow outflow = new Outflow("2024 Sem 2 School Fees", 999999.99, "2024-02-01");
        outflow.setCategory(Outflow.Category.EDUCATION);
        assertEquals(Outflow.Category.EDUCATION, outflow.getCategory());
        assertEquals(-999999.99, outflow.getAmount());

    }
}
