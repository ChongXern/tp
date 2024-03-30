package financialtransactions;

public class Outflow extends Transaction<Outflow.Category> {
    public enum Category {
        FOOD, RENT, DEBT, SHOPPING, TREAT, EDUCATION, TAX, OTHER
    }

    public Outflow(String name, double amount, String date) {
        super(name, -1.00 * amount, date);
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
