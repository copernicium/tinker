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

        boolean handleTaskbar = true;

        {//set the size of the frame
            boolean fullscreen = false;
            Dimension size = fullscreen ? Util.Graphics.generateFullscreenDimension(handleTaskbar) : new Dimension(500, 500);

            frame.setSize(size);
            frame.getContentPane().setPreferredSize(new Dimension(size));//fill frame
            frame.pack();
        }

            frame.add(new RectangleComponent(frame, handleTaskbar));

        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("Display frame?(y/n/quit) ");
            String input = in.next().trim();
            if(true || input.equals("quit")){
                frame.setVisible(false);
                frame.dispose();
                break;
            }
            frame.setVisible(true /*input.equals("y")*/);
        }
    }
}
