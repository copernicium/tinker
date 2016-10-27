package computer_science.BankAccount;
/**
 * A tester for the BankAccount class
 * 
 * @author Logan Traffas
 * @version 10/12/2016
 * assignment: bank account
 */
public class BankAccountTester
{
    public static void main(String[] args){
        BankAccount momsSavings = new BankAccount(1000);
        BankAccount harrysChecking = new BankAccount(0);
        double transferAmount = 500;
        momsSavings.withdraw(transferAmount);
        harrysChecking.deposit(transferAmount);
        System.out.println("Mom's balance:" + momsSavings.getBalance() + " Expected:500");
        System.out.println("Harry's balance:" + harrysChecking.getBalance() + " Expected:500");
        
        double interestRate = 5;//percent interest
        double interestAmount = momsSavings.getBalance() * interestRate / 100;
        momsSavings.deposit(interestAmount);
        System.out.println("(Manually handling interest) Mom's balance:" + momsSavings.getBalance() + " Expected:525");
        
        harrysChecking.addInterest(interestRate);
        System.out.println("(Using addInterest() Harry's balance:" + harrysChecking.getBalance() + " Expected:525");
    }
}
