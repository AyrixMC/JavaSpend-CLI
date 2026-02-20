import java.util.*;
import java.io.*;

public class ExpenseTracker {
    private static final String FILE_NAME = "expenses.txt";
    private static List<Expense> expenses = new ArrayList<>();
    private static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        loadExistingData();
        
        while (true) {
            printMenu();
            String choice = input.nextLine();

            if (choice.equals("1")) {
                addNewExpense();
            } else if (choice.equals("2")) {
                viewAllExpenses();
            } else if (choice.equals("3")) {
                saveAndExit();
                break;
            } else {
                System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- PERSONAL FINANCE TRACKER ---");
        System.out.println("1. Add New Expense");
        System.out.println("2. View History & Total");
        System.out.println("3. Save and Exit");
        System.out.print("Select an option: ");
    }

    private static void addNewExpense() {
        System.out.print("What did you buy? ");
        String title = input.nextLine();

        System.out.print("How much did it cost? ");
        double amount = Double.parseDouble(input.nextLine());

        System.out.print("Category (Food/Tech/Travel): ");
        String category = input.nextLine();

        expenses.add(new Expense(title, amount, category));
        System.out.println("Success! Expense recorded.");
    }

    private static void viewAllExpenses() {
        if (expenses.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        double total = 0;
        System.out.println("\nCategory        | Title      | Amount");
        System.out.println("---------------------------------------");
        for (Expense e : expenses) {
            System.out.println(e);
            total += e.getAmount();
        }
        System.out.println("---------------------------------------");
        System.out.printf("TOTAL SPENT: $%.2f\n", total);
    }

    private static void saveAndExit() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Expense e : expenses) {
                writer.println(e.toFileFormat());
            }
            System.out.println("Data saved to " + FILE_NAME + ". Goodbye!");
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    private static void loadExistingData() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (Scanner fileScanner = new Scanner(file)) {
            while (fileScanner.hasNextLine()) {
                String[] parts = fileScanner.nextLine().split(",");
                if (parts.length == 3) {
                    expenses.add(new Expense(parts[1], Double.parseDouble(parts[2]), parts[0]));
                }
            }
        } catch (Exception e) {
            System.out.println("Started with a fresh database.");
        }
    }
}