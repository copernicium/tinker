package ComputerScience.Graphics.InvestmentButtons.VersionOne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author Logan Traffas
 * @Date 5/11/2017.
 * @Version 1.0.0
 * @Assignment GR10.7--Event Handling
 */
class DepositListener implements ActionListener {
    private BankAccount account;
    private double increment;

    public void actionPerformed(ActionEvent event) {
        account.deposit(increment);
        System.out.println("Balance: $" + account.getBalance());
    }

    public DepositListener(){
        this(new BankAccount(), 100.00);
    }

    public DepositListener(BankAccount account){
        this(account,100.00);
    }

    public DepositListener(BankAccount account,double increment){
        this.account = account;
        this.increment = increment;
    }
}
