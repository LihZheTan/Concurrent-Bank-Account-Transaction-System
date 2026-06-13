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
        System.out.println(Thread.currentThread().getName() + " started " + transaction.getType() + amountText);

        try {
            //Randomly assign Thread.sleep() timings
            int randomProcessingTime = (int) (Math.random() * 900) + 100;
            Thread.sleep(randomProcessingTime);
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

        System.out.println("\n" + Thread.currentThread().getName() + " finished " + transaction.getType() + amountText);
        System.out.println("Current Balance: RM" + account.getBalance());
    }
}