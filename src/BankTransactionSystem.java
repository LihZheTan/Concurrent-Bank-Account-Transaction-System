public class BankTransactionSystem {
    //Class Methods
    private static void runSequentialProcessing() {
        //code here
    }

    private static void runConcurrentProcessing() throws InterruptedException {
        //code here
    }

    private static void runRaceConditionUnsafe() throws InterruptedException {
        //code here
    }

    private static void runSynchronizationSafe() throws InterruptedException {
        //code here
    }

    //8 Transactions to be used assigned to an array
    Transaction[] transactions = {
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