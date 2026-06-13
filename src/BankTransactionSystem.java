public class BankTransactionSystem {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("======================================================");
        System.out.println("   CSC2044: Concurrent Bank Account Transaction System");
        System.out.println("======================================================\n");

        // Task 2: Sequential Processing
        System.out.println("\nStarting Task 2: SEQUENTIAL PROCESSING");
        runSequential();
        Thread.sleep(1000); // Pause for a second to separate the output

        // Task 3: Concurrent Processing
        System.out.println("\nStarting Task 3: CONCURRENT PROCESSING");
        runConcurrentBasic();
        Thread.sleep(1000);

        // Task 5: Race Condition Demonstration
        System.out.println("\nStarting Task: RACE CONDITION (UNSAFE)");
        runRaceConditionUnsafe();
        Thread.sleep(1000);

        // Task 6: Synchronization Solution
        System.out.println("\nStarting Task: SYNCHRONIZATION (SAFE)");
        runSynchronizationSafe();
    }

    // ---------------------------------------------------------
    // Methods for each task
    // ---------------------------------------------------------

    private static void runSequential() {
        //code here
    }

    private static void runConcurrentBasic() throws InterruptedException {
        //code here
    }

    private static void runRaceConditionUnsafe() throws InterruptedException {
        //code here
    }

    private static void runSynchronizationSafe() throws InterruptedException {
        //code here
    }

    // 8 Transactions to be used
    Transaction Transaction_1 =  new Transaction("Deposit",10.00);
    Transaction Transaction_2 =  new Transaction("Withdraw",110.00);
    Transaction Transaction_3 =  new Transaction("Service Charge",1.00);
    Transaction Transaction_4 =  new Transaction("Balance Check",0.00);
    Transaction Transaction_5 =  new Transaction("Withdraw",30.00);
    Transaction Transaction_6 =  new Transaction("Balance Check",0.00);
    Transaction Transaction_7 =  new Transaction("Deposit",100.00);
    Transaction Transaction_8 =  new Transaction("Withdraw",50.00);
}