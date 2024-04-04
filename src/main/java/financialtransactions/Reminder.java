package financialtransactions;

import customexceptions.CategoryNotFoundException;

import java.util.EnumSet;

public class Reminder extends Transaction<Reminder.Category> {
    public enum Category {
        INSTALLMENT, CREDITCARD, UTILITIES
    }

    public Reminder(String name, double amount, String date) {
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
        return super.toSave() + "|R\n";
    }
}
