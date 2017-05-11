package ComputerScience.Graphics.InvestmentButtons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author Logan Traffas
 * @Date 5/11/2017.
 * @Version 1.0.0
 * @Assignment GR10.7--Event Handling
 */
class AddInterestListener implements ActionListener {
    private BankAccount account;
    private double interestRate;

    public void actionPerformed(ActionEvent event) {
        // The listener method accesses the account variable from the surrounding block
        double interest = account.getBalance() * interestRate / 100;
        account.deposit(interest);
        System.out.println("balance: " + account.getBalance());
    }

    public AddInterestListener(BankAccount account, double interestRate){
        this.account = account;
        this.interestRate = interestRate;
    }
}
