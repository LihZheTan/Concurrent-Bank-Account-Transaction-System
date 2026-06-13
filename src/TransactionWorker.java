public class TransactionWorker implements Runnable {
    private BankAccount account;
    private Transaction transaction;
    private int transactionId;

    public TransactionWorker(BankAccount account, Transaction transaction, int transactionId) {
        this.account = account;
        this.transaction = transaction;
        this.transactionId = transactionId;
    }

    @Override
    public void run() {
        String amountText = transaction.getAmount() > 0 ? " RM" + transaction.getAmount() : "";
        System.out.println("Starting Transaction " + transactionId + ": " + transaction.getType() + amountText);

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }

        switch (transaction.getType()) {
            case "Deposit":
                account.deposit(transaction.getAmount());
                break;
            case "Withdraw":
                account.withdraw(transaction.getAmount());
                break;
            case "Service Charge":
                account.applyServiceCharge(transaction.getAmount());
                break;
            case "Balance Check": //Free of charge 0.00
                break;
        }

        System.out.println("Finished Transaction " + transactionId);
        System.out.println("Current Balance: RM" + account.getBalance() + "\n");
    }
}