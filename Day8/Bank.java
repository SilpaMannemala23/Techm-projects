import java.util.concurrent.ConcurrentHashMap;

public class Bank {
    private final ConcurrentHashMap<String, BankAccount> accounts = new ConcurrentHashMap<>();

    public void addAccount(BankAccount account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public BankAccount getAccount(String accountNumber) {
        return accounts.get(accountNumber);
    }

    @Override
    public String toString() {
        return accounts.toString();
    }
}
