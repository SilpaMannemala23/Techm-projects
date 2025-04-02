import java.util.concurrent.locks.Lock;

public class TransactionTask implements Runnable {
    private final BankAccount fromAccount;
    private final BankAccount toAccount;
    private final double amount;

    public TransactionTask(BankAccount fromAccount, BankAccount toAccount, double amount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
    }

    @Override
    public void run() {
        // Lock the accounts in a fixed order to prevent deadlock
        Lock firstLock = fromAccount.getLock().hashCode() < toAccount.getLock().hashCode() ? fromAccount.getLock() : toAccount.getLock();
        Lock secondLock = firstLock == fromAccount.getLock() ? toAccount.getLock() : fromAccount.getLock();

        // Acquire locks in a fixed order
        firstLock.lock();
        try {
            secondLock.lock();
            try {
                // Perform the transaction if there are enough funds
                if (fromAccount.getBalance() >= amount) {
                    fromAccount.withdraw(amount);
                    toAccount.deposit(amount);
                    System.out.println("Transferred " + amount + " from " + fromAccount.getAccountNumber() +
                            " to " + toAccount.getAccountNumber());
                } else {
                    System.out.println("Insufficient funds for transaction.");
                }
            } finally {
                secondLock.unlock();
            }
        } finally {
            firstLock.unlock();
        }
    }
}
