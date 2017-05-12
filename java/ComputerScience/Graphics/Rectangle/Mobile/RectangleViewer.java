package ComputerScience.Graphics.Rectangle.Mobile;

import javax.swing.JFrame;

/**
 * This program moves the rectangle.
 *
 * @Author Logan Traffas
 * @Date 5/12/2017.
 * @Version 1.0.0
 * @Assignment GR10.9--Processing Timer Events
 */
public class RectangleViewer {
    public static void main(String[] args) {
        JFrame frame = new RectangleFrame();
        frame.setTitle("An animated rectangle");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

