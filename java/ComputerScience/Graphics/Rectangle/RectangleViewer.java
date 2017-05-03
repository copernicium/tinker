package ComputerScience.Graphics.Rectangle;

import javax.swing.JFrame;
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
        Scanner in = new Scanner(System.in);
        JFrame frame = new JFrame();

        frame.setSize(500,500);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setTitle("Two rectangles");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        RectangleComponent component = new RectangleComponent(frame);

        frame.add(component);

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
