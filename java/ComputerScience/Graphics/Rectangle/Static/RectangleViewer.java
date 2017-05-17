package ComputerScience.Graphics.Rectangle.Static;

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

        {//set the size of the framea
            boolean fullscreen = true;
            Dimension size = fullscreen ? Util.Graphics.generateFullscreenDimension(handleTaskbar) : new Dimension(500, 500);

            frame = Util.Graphics.createJFrameOfSize(frame,size);

        }

        frame.add(new RectangleComponent(frame, handleTaskbar));

        Scanner in = new Scanner(System.in);
        while(true) {
            System.out.print("Display frame?(y/n/quit) ");
            String input = in.next().trim();
            if(input.equals("quit")){
                break;
            }
            frame.setVisible(input.equals("y"));
        }
        frame.setVisible(false);
        frame.dispose();
    }
}
