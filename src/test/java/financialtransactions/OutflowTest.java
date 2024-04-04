package financialtransactions;

import customexceptions.CategoryNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OutflowTest {
    @Test
    public void testSetCategory() {
        Outflow outflow = new Outflow("2024 Sem 2 School Fees", 999999.99, "2024-02-01");
        try {
            outflow.setCategory(Outflow.Category.EDUCATION);
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(Outflow.Category.EDUCATION, outflow.getCategory());
        assertEquals(-999999.99, outflow.getAmount());

    }
}
