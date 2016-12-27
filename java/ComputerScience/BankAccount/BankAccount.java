package ComputerScience.BankAccount;
/**
 * A bank account has a balance that can be changed by 
   deposits and withdrawals.
 * 
 * @author Logan Traffas
 * @version 10/12/2016
 * assignment: bank account
 */
public class BankAccount
{  
    private double balance;
    
    /**
          Constructs a bank account with a zero balance.
    */
    public BankAccount()
    {   
        balance = 0;
    }
    
    /**
        Constructs a bank account with a given balance.
        @param initialBalance the initial balance
    */
    public BankAccount(double initialBalance)
    {   
        balance = initialBalance;
    }
    
    /**
        Deposits money into the bank account.
        @param amount the amount to deposit
    */
    public void deposit(double amount)
    {  
        balance = balance + amount;
    }
    
    /**
        Withdraws money from the bank account.
        @param amount the amount to withdraw
    */
    public void withdraw(double amount)
    {   
          balance = balance - amount;
    }
    
    /**
        Gets the current balance of the bank account.
        @return the current balance
    */
    public double getBalance()
    {   
        return balance;
    }
    
    /**
     * Adds interest to a given account.
     * @param rate The interest rate (percent) to be used to add interest to the account
     */
    public void addInterest(double rate){
        balance += balance*rate/100;
    }
}
