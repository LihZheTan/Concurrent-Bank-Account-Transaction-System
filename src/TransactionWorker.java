public class TransactionWorker implements Runnable {
    private BankAccount account;
    private Transaction transaction;

    // The worker needs to know WHICH account to update, and WHAT transaction to run
    public TransactionWorker(BankAccount account, Transaction transaction) {
        this.account = account;
        this.transaction = transaction;
    }

    @Override
    public void run() {
        // Here you will write the logic to check the transaction.type 
        // and call the correct method on the 'account' object.
        // You'll also add your Thread.sleep() calls here to simulate processing time.
    }
}