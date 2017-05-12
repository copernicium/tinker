package ComputerScience.Graphics.ButtonTesting;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * An action listener that prints a message.
 *
 * @Author Logan Traffas
 * @Date 5/11/2017.
 * @Version 1.0.0
 * @Assignment GR10.7--Event Handling
 */
public class ClickListener implements ActionListener {
    private double toSend;
    private double increment;
    private boolean sent;

    @Override
    public void actionPerformed(ActionEvent event){
        toSend += increment;
        sent = false;
    }

    public double get(){
        if(!sent){
            sent = true;
            double a = toSend;
            toSend = 0;
            return a;
        }
        return 0;
    }

    public ClickListener(double increment){
        this.increment = increment;
        this.toSend = 0;
        this.sent = false;
    }
}
