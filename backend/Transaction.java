package backend;

public class Transaction {
    private String id;
    private String note;
    private double amount;
    private Type type;

    public enum Type { EXPENSE, INCOME }

    public Transaction(String id, String note, double amount, Type type) {
        this.id = id;
        this.note = note;
        this.amount = amount;
        this.type = type;
    }

    // This is the core fix
    public double getAmount() {
        return amount;
    }

    public String getSummary() {
        return String.format("[%s] %s: $%.2f", type, note, amount);
    }
}