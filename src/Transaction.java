public class Transaction {
    private String type; // e.g., "DEPOSIT", "WITHDRAW"
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    // Getters for type and amount
}