import java.io.Serializable;

public class Expense implements Serializable {
    private String title;
    private double amount;
    private String category;

    public Expense(String title, double amount, String category) {
        this.title = title;
        this.amount = amount;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return String.format("%-15s | %-10s | $%.2f", category, title, amount);
    }

    public String toFileFormat() {
        return category + "," + title + "," + amount;
    }
}