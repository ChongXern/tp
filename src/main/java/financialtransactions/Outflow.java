package financialtransactions;

import customexceptions.CategoryNotFoundException;

import java.util.EnumSet;

public class Outflow extends Transaction<Outflow.Category> {
    public enum Category {
        FOOD, RENT, DEBT, SHOPPING, TREAT, EDUCATION, TAX, OTHER
    }

    public Outflow(String name, double amount, String date) {
        super(name, -1.00 * amount, date);
    }

    public void setCategory(Category category) throws CategoryNotFoundException {
        if (!isValidCategory(category)) {
            throw new CategoryNotFoundException(Category.values());
        }
        this.category = category;
    }
    public boolean isValidCategory(Category category) {
        EnumSet<Category> categories = EnumSet.allOf(Category.class);
        return categories.contains(category);
    }

    @Override
    public String toSave() {
        return super.toSave() + "|O\n";
    }
}
