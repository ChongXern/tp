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

    @Override
    public void setCategory(String category) throws CategoryNotFoundException {
        if (!isValidCategory(category)) {
            throw new CategoryNotFoundException(Category.values());
        }
        super.category = Category.valueOf(category.toUpperCase());
    }

    public boolean isValidCategory(String category) {
        for (Category enumCategory : Category.values()) {
            if (enumCategory.toString().equalsIgnoreCase(category)) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String toSave() {
        return super.toSave() + "|I\n";
    }
}
