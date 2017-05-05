package ComputerScience.Graphics.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.util.Scanner;

/**
 * @Author Logan Traffas
 * @Date 5/3/2017.
 * @Version 1.0.0
 * @Assignment Gr1.1--Graphics1--Getting Started
 */
public class RectangleViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("Rectangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        {//set the size of the frame
            Dimension size = new Dimension(500, 500);
            size = new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
            {
                size = new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (frame.getInsets().left + frame.getInsets().right),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (frame.getInsets().top + frame.getInsets().bottom) - 40);

            }

            frame.setSize(size);
            frame.getContentPane().setPreferredSize(new Dimension(size));//fill frame
            frame.pack();
         //   frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        }

        frame.add(new RectangleComponent(frame, true));

        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("Display frame?(y/n/quit) ");
            String input = in.next().trim();
            if(input.equals("quit")){
                frame.setVisible(false);
                frame.dispose();
                break;
            }
            frame.setVisible(input.equals("y"));
        }
    }
}
