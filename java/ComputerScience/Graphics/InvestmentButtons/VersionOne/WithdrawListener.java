package ComputerScience.Graphics.InvestmentButtons.VersionOne;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author Logan Traffas
 * @Date 5/11/2017.
 * @Version 1.0.0
 * @Assignment GR10.7--Event Handling
 */
class WithdrawListener implements ActionListener {
    private BankAccount account;
    private double decrement;

    public void actionPerformed(ActionEvent event) {
        account.withdraw(decrement);
        System.out.println("Balance: $" + account.getBalance());
    }

    public WithdrawListener(){
        this(new BankAccount(), 100.00);
    }

    public WithdrawListener(BankAccount account){
        this(account,100.00);
    }

    public WithdrawListener(BankAccount account, double decrement){
        this.account = account;
        this.decrement = decrement;
    }
}
