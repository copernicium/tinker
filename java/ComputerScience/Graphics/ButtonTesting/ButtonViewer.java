package ComputerScience.Graphics.ButtonTesting;

import javax.swing.*;
import java.awt.*;

/**
 * This program demonstrates how to install an action listener.
 *
 * @Author Logan Traffas
 * @Date 5/11/2017.
 * @Version 1.0.0
 * @Assignment GR10.7--Event Handling
 */
public class ButtonViewer {
    private static final int FRAME_WIDTH = 615;//px
    private static final int FRAME_HEIGHT = 115;//px

    private static final boolean FULLSCREEN = false;
    private static final boolean HANDLE_TASKBAR = true;

    private static final int BUTTON_AMOUNT = 5;

    public static void main(String[] args){
        JFrame frame = new JFrame();
        {
            Dimension size = FULLSCREEN ? Util.Graphics.generateFullscreenDimension(HANDLE_TASKBAR) : new Dimension(FRAME_WIDTH, FRAME_HEIGHT);
            frame = Util.Graphics.setSize(frame, size);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        }

        ClickListener[] listeners = new ClickListener[BUTTON_AMOUNT];
        for(int i = 0; i < listeners.length; i++){
            listeners[i] = new ClickListener((double)i);
        }

        JPanel pane = new JPanel();

        JButton[] buttons = new JButton[BUTTON_AMOUNT];
        {
            final Dimension BUTTON_SIZE = new Dimension(100, 100);
            for (int i = 0; i < buttons.length; i++) {
                buttons[i] = new JButton("+" + i);
                buttons[i].setPreferredSize(BUTTON_SIZE);
                buttons[i].addActionListener(listeners[i]);
                pane.add(buttons[i]);
            }
        }

        JLabel total = new JLabel();

        pane.add(total);
        frame.add(pane);

        double value = 0;

        while(true){
            total.setText("Value: " + value);
            for(ClickListener listener: listeners){
                value += listener.get();
            }
        }
    }
}

