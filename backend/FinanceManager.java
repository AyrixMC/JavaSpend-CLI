package backend;

import java.util.*;
import java.util.stream.Collectors;

public class FinanceManager {
    private List<Transaction> history = new ArrayList<>();

    public void addEntry(Transaction t) {
        history.add(t);
    }

    public double calculateTotal() {
        // This will now work perfectly because getAmount() is in Transaction.java
        return history.stream().mapToDouble(t -> t.getAmount()).sum();
    }

    public List<Transaction> filterByNote(String keyword) {
        return history.stream()
            .filter(t -> t.getSummary().contains(keyword))
            .collect(Collectors.toList());
    }
}