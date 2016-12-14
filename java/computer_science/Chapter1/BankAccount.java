package computer_science.Chapter1;
public class BankAccount{
    public static void main(String[] args){
        final double startingBalance=1000;//dollars
        final double numberOfYears = 3;//years
        final double interest = .05;//percent
        double balance = startingBalance;
        for(int i=0; i<numberOfYears; i++){
            balance += balance*interest;
        }
        System.out.println("My balance after three years is $" + balance);
    }
}