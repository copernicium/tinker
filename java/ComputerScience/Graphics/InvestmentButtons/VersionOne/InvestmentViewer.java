package ComputerScience.Graphics.InvestmentButtons.VersionOne;

import java.awt.*;
import javax.swing.*;

/**
 * This program demonstrates how an action listener can access a variable from a surrounding block.
 *
 * @Author Logan Traffas
 * @Date 5/11/2017.
 * @Version 1.0.0
 * @Assignment GR10.7--Event Handling
 */
public class InvestmentViewer {
    private static final int FRAME_WIDTH = 420;//px
    private static final int FRAME_HEIGHT = 120;//px
    private static final boolean FULLSCREEN = false;
    private static final boolean HANDLE_TASKBAR = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        Dimension size = FULLSCREEN  ? Util.Graphics.generateFullscreenDimension(HANDLE_TASKBAR) : new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
        frame = Util.Graphics.createJFrameOfSize(frame, size);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        final double INITIAL_BALANCE = 1000.00;//dollars
        BankAccount account = new BankAccount(INITIAL_BALANCE);

        final double WITHDRAW_AMOUNT = 100.00, DESPOSIT_AMOUNT = 100.00;//dollars

        JButton withdrawButton = new JButton("Withdraw $" + WITHDRAW_AMOUNT);
        JButton depositButton = new JButton("Deposit $" + DESPOSIT_AMOUNT);

        final Dimension BUTTON_SIZE = new Dimension(200,100);
        withdrawButton.setPreferredSize(BUTTON_SIZE);
        depositButton.setPreferredSize(BUTTON_SIZE);

        withdrawButton.addActionListener(new WithdrawListener(account,WITHDRAW_AMOUNT));
        depositButton.addActionListener(new DepositListener(account,DESPOSIT_AMOUNT));

        JPanel buttonPane = new JPanel();
        buttonPane.add(withdrawButton);
        buttonPane.add(depositButton);

        frame.add(buttonPane);

        System.out.println("Balance: $" + account.getBalance());
    }
}

