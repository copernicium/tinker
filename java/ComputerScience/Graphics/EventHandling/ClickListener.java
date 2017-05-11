package ComputerScience.Graphics.EventHandling;

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
    public void actionPerformed(ActionEvent event){
        System.out.println("I was clicked.");
    }
}
