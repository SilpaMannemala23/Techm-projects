import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private final String accountNumber;
    private double balance;
    private final ReentrantLock lock = new ReentrantLock();

    public BankAccount(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Synchronized method to deposit money
    public void deposit(double amount) {
        balance += amount;
    }

    // Synchronized method to withdraw money
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds in account " + accountNumber);
        }
    }

    public ReentrantLock getLock() {
        return lock;
    }

    @Override
    public String toString() {
        return "Account: " + accountNumber + ", Balance: " + balance;
    }
}
