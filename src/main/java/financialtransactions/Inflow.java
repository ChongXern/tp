package financialtransactions;

import customexceptions.CategoryNotFoundException;

import java.util.EnumSet;

public class Inflow extends Transaction<Inflow.Category> {
    public enum Category {
        INCOME, INVESTMENT, GIFT, LOAN, REFUND, OTHER
    }

    public Inflow(String name, double amount, String date) {
        super(name, amount, date);
    }
    public void setCategory(Category category) throws CategoryNotFoundException {
        if (!isValidCategory(category)) {
            throw new CategoryNotFoundException(Category.values());
        }
        super.category = category;
    }

    public boolean isValidCategory(Category category) {
        EnumSet<Category> categories = EnumSet.allOf(Category.class);
        return categories.contains(category);
    }
    
    @Override
    public String toSave() {
        return super.toSave() + "|I\n";
    }
}
