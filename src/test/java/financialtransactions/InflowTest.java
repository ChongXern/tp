package financialtransactions;

import customexceptions.CategoryNotFoundException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InflowTest {
    @Test
    public void testSetCategory() {
        Inflow inflow = new Inflow("February salary", 20.00, "2024-03-02");
        try {
            inflow.setCategory("Income");
        } catch (CategoryNotFoundException e) {
            System.out.println(e.getMessage());
        }
        assertEquals(Inflow.Category.INCOME, inflow.getCategory());
    }
}
