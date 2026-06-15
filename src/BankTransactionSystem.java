public class BankTransactionSystem {
    //Class Methods
    private static void runSequentialProcessing() {

        BankAccount sequentialAccount = new BankAccount(1000.0);

        for (int i = 0; i < transactions.length; i++) {

            // Current Transaction object from List
            Transaction currentTransaction = transactions[i];
            int transactionNumber = i + 1;

            // Format the amount for printing
            String amountText = currentTransaction.getAmount() > 0 ? " RM" + currentTransaction.getAmount() : "";

            System.out.println("Starting Transaction " + transactionNumber + ": " + currentTransaction.getType() + amountText);

            // Simulate processing time
            try {
                Thread.sleep(500);
            }catch (InterruptedException e) {
                System.out.println(e.getMessage());
            }

            // Look at transaction list and apply the correct amount
            switch (currentTransaction.getType()) {
                case "Deposit":
                    sequentialAccount.deposit(currentTransaction.getAmount());
                    break;

                case "Withdraw":
                    sequentialAccount.withdraw(currentTransaction.getAmount());
                    break;

                case "Service Charge":
                    sequentialAccount.applyServiceCharge(currentTransaction.getAmount());
                    break;

                case "Balance Check":
                    break;
            }

            // Display output completion and print new balance
            System.out.println("Finished Transaction " + transactionNumber);
            System.out.println("Current Balance: RM " + sequentialAccount.getBalance() + "\n");
        }
    }

    private static void runConcurrentProcessing() throws InterruptedException {
        //Creates a BankAccount object to be used in the function with 1000 initial credits
        BankAccount sharedAccount = new BankAccount(1000.0);

        //Creates an array to store each thread later on
        Thread[] threads = new Thread[transactions.length];

        // Loops through the transactions. Creates a worker and a thread for each transaction
        for (int i = 0; i < transactions.length; i++) {
            // Creates a few runnable for each transaction
            TransactionWorker worker = new TransactionWorker(sharedAccount, transactions[i], i + 1);

            // Creates a new thread with its runnable and assigns its appropriate name starting from 1
            threads[i] = new Thread(worker, "Thread-" + (i + 1));

            // Starts each thread into its runnable state
            threads[i].start();
        }

        //Join threads once they are done
        for (Thread t : threads) {
            t.join();
        }
    }

    private static void runRaceConditionUnsafe() throws InterruptedException {
        //Create BankAccount objects for shared transactions and initial balance.
        BankAccount sharedAccount = new BankAccount(1000.0);
        BankAccount initialBalance = new BankAccount(1000.0);

        //Variable for the number for times incremented.
        int counts = 100000;

        //Create two threads
        Thread t1 = new Thread( () -> {
           for ( int i = 0; i < 100000; i++ ) {
               sharedAccount.deposit( 1 );
           }
        });

        Thread t2 = new Thread(() -> {
            for ( int i = 0; i < 100000; i++ ) {
                sharedAccount.deposit( 1 );
            }
        });

        //Start each thread concurrently
        t1.start();
        t2.start();

        //Join threads once complete
        t1.join();
        t2.join();

        //Print output comparing expected to actual balance
        System.out.println( "Expected Balance = " + (initialBalance.getBalance() + 100000 * 2 ) );
        System.out.println( "Actual Balance: " + sharedAccount.getBalance());
    }

    private static void runSynchronizationSafe() throws InterruptedException {
        //Create BankAccount objects for shared transactions and initial balance.
        BankAccount sharedAccount = new BankAccount(1000.0);
        BankAccount initialBalance = new BankAccount(1000.0);

        //Variable for the number for times incremented.
        int counts = 100000;

        //Create two threads
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < counts; i++) {
                sharedAccount.safeDeposit(1);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < counts; i++) {
                sharedAccount.safeDeposit(1);
            }
        });

        //Start each thread concurrently
        t1.start();
        t2.start();

        //Join threads once complete
        t1.join();
        t2.join();

        //Print output comparing expected to actual balance
        System.out.println( "Expected Balance = " + ( initialBalance.getBalance() + counts * 2 ) );
        System.out.println( "Actual Balance: " + sharedAccount.getBalance() );
    }

    //8 Transactions to be used assigned to an array
    static Transaction[] transactions = {
            new Transaction("Deposit", 10.00),
            new Transaction("Withdraw", 110.00),
            new Transaction("Service Charge", 1.00),
            new Transaction("Balance Check", 0.00),
            new Transaction("Withdraw", 30.00),
            new Transaction("Balance Check", 0.00),
            new Transaction("Deposit", 100.00),
            new Transaction("Withdraw", 50.00)
    };

    //Main Code
    public static void main(String[] args) throws InterruptedException {
        System.out.println("======================================================");
        System.out.println("   CSC2044: Concurrent Bank Account Transaction System");
        System.out.println("======================================================\n");

        // Task 2: Sequential Processing
        System.out.println("\nStarting Task 2: SEQUENTIAL PROCESSING");
        runSequentialProcessing();
        Thread.sleep(1000);

        // Task 3: Concurrent Processing
        System.out.println("\nStarting Task 3: CONCURRENT PROCESSING");
        runConcurrentProcessing();
        Thread.sleep(1000);

        // Task 5: Race Condition Demonstration
        System.out.println("\nStarting Task: RACE CONDITION (UNSAFE)");
        runRaceConditionUnsafe();
        Thread.sleep(1000);

        // Task 6: Synchronization Solution
        System.out.println("\nStarting Task: SYNCHRONIZATION (SAFE)");
        runSynchronizationSafe();
    }
}