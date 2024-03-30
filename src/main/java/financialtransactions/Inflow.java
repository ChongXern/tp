package financialtransactions;

public class Inflow extends Transaction<Inflow.Category> {
    public enum Category {
        INCOME, INVESTMENT, GIFT, LOAN, REFUND, OTHER
    }
    public Inflow(String name, double amount, String date) {
        super(name, amount, date);
    }
    public void setCategory(Category category) {
        super.category = category;
    }
    
    @Override
    public String toSave() {
        return super.toSave() + "|I\n";
    }
}
