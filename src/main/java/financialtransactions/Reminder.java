package financialtransactions;

import customexceptions.CategoryNotFoundException;

public class Reminder extends Transaction<Reminder.Category> {
    public enum Category {
        INSTALLMENT, CREDITCARD, UTILITIES, OTHER
    }

    public Reminder(String name, double amount, String date) {
        super(name, -1.00 * amount, date);
    }

    public void setCategory(String category) throws CategoryNotFoundException {
        if (!isValidCategory(category)) {
            throw new CategoryNotFoundException(Category.values());
        }
        this.category = Category.valueOf(category.toUpperCase());
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
        return super.toSave() + "|R\n";
    }
}
