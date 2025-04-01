import java.util.concurrent.*;

public class BankTransactionSystem {
    public static void main(String[] args) throws InterruptedException {
        // Create a bank and add some accounts
        Bank bank = new Bank();
        BankAccount account1 = new BankAccount("A123", 1000);
        BankAccount account2 = new BankAccount("B456", 500);
        BankAccount account3 = new BankAccount("C789", 300);

        bank.addAccount(account1);
        bank.addAccount(account2);
        bank.addAccount(account3);

        // Create a thread pool to simulate multiple transactions
        ExecutorService executor = Executors.newFixedThreadPool(3);

        // Create and submit transaction tasks
        executor.submit(new TransactionTask(account1, account2, 200));
        executor.submit(new TransactionTask(account2, account3, 100));
        executor.submit(new TransactionTask(account1, account3, 50));

        // Allow time for tasks to complete
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        // Print final account balances
        System.out.println("\nFinal account balances:");
        System.out.println(account1);
        System.out.println(account2);
        System.out.println(account3);
    }
}
