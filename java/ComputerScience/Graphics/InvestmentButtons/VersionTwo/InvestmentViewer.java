package ComputerScience.Graphics.InvestmentButtons.VersionTwo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This program displays the growth of an investment.
 *
 * @Author Logan Traffas
 * @Date 5/12/2017.
 * @Version 1.0.0
 * @Assignment GR10.8--Building Applications with Buttons
 */
public class InvestmentViewer {
    private static final int FRAME_WIDTH = 250;
    private static final int FRAME_HEIGHT = 100;

    private static final double INTEREST_RATE = 10;
    private static final double INITIAL_BALANCE = 1000;

    public static void main(String[] args) {
        JFrame frame = new JFrame();

        // The button to trigger the calculation
        JButton interestButton = new JButton("Add Interest");
        JButton resetButton = new JButton("Reset");

        // The application adds interest to this bank account
        final BankAccount account = new BankAccount(INITIAL_BALANCE);

        // The label for displaying the results
        final JLabel balanceLabel = new JLabel("balance: " + account.getBalance());

        final JLabel transactionsLabel = new JLabel("transactions: " + account.getTransactions());

        // The panel that holds the user interface components
        JPanel panel = new JPanel();
        final int GRID_ROWS = 2, GRID_COLUMNS = 2;
        GridLayout gridLayout = new GridLayout(GRID_ROWS,GRID_COLUMNS);
        panel.setLayout(gridLayout);

        panel.add(interestButton);
        panel.add(resetButton);
        panel.add(balanceLabel);
        panel.add(transactionsLabel);
        frame.add(panel);

        class AddInterestListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                double interest = account.getBalance() * INTEREST_RATE / 100;
                account.deposit(interest);
                balanceLabel.setText("balance: " + account.getBalance());
                transactionsLabel.setText("transactions: " + account.getTransactions());
            }
        }

        class ResetListener implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                account.reset();
                balanceLabel.setText("balance: " + account.getBalance());
                transactionsLabel.setText("transactions: " + account.getTransactions());
            }
        }

        interestButton.addActionListener(new AddInterestListener());
        resetButton.addActionListener(new ResetListener());

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}