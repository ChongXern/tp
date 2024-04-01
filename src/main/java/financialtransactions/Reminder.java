package financialtransactions;

public class Reminder extends Transaction<Reminder.Category> {
    public enum Category {
        INSTALLMENT, CREDITCARD, UTILITIES
    }

    public Reminder(String name, double amount, String date) {
        super(name, -1.00 * amount, date);
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toSave() {
        return super.toSave() + "|R\n";
    }
}
