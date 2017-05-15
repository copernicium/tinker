package ComputerScience.Graphics.InvestmentButtons.VersionTwo;

/**
 *  A bank account has a balance that can be changed by deposits and withdrawals.
 *
 * @Author Logan Traffas
 * @Date 5/12/2017.
 * @Version 1.0.0
 * @Assignment GR10.8--Building Applications with Buttons
 */
public class BankAccount {
    private double initial;
    private double balance;
    private int transactions;

    /**
     Constructs a bank account with a zero balance.
     */
    public BankAccount() {
        balance = 0;
        initial = new Double(balance);
        transactions = 0;
    }

    /**
     Constructs a bank account with a given balance.
     @param initialBalance the initial balance
     */
    public BankAccount(double initialBalance) {
        balance = initialBalance;
        initial = balance;
        transactions = 0;
    }

    /**
     Deposits money into the bank account.
     @param amount the amount to deposit
     */
    public void deposit(double amount) {
        double newBalance = balance + amount;
        balance = newBalance;
        transactions++;
    }

    /**
     Withdraws money from the bank account.
     @param amount the amount to withdraw
     */
    public void withdraw(double amount) {
        double newBalance = balance - amount;
        balance = newBalance;
        transactions++;
    }

    /**
     Gets the current balance of the bank account.
     @return the current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Gets the number of transactions of the bank account.
     * @return the total number of transactions
     */
    public int getTransactions() {
        return transactions;
    }

    public void reset(){
        balance = initial;
        transactions = 0;
    }
}
