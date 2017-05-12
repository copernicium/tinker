package ComputerScience.Graphics.InvestmentButtons.VersionOne;

/**
 * A bank account has a balance that can be changed by deposits and withdrawals.
 *
 * @Author Logan Traffas
 * @Date 5/11/2017.
 * @Version 1.0.0
 * @Assignment GR10.7--Event Handling
 */
public class BankAccount {
    private double balance;

    /**
     Constructs a bank account with a zero balance.
     */
    public BankAccount(){
        balance = 0;
    }

    /**
     Constructs a bank account with a given balance.
     @param initialBalance the initial balance
     */
    public BankAccount(double initialBalance){
        balance = initialBalance;
    }

    /**
     Deposits money into the bank account.
     @param amount the amount to deposit
     */
    public void deposit(double amount){
        double newBalance = balance + amount;
        balance = newBalance;
    }

    /**
     Withdraws money from the bank account.
     @param amount the amount to withdraw
     */
    public void withdraw(double amount){
        double newBalance = balance - amount;
        balance = newBalance;
    }

    /**
     Gets the current balance of the bank account.
     @return the current balance
     */
    public double getBalance(){
        return balance;
    }
}

