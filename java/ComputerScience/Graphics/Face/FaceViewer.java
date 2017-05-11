package ComputerScience.Graphics.Face;

import javax.swing.JFrame;

/**
 * @Author Logan Traffas
 * @Date 5/8/2017.
 * @Version 1.0.0
 * @Assignment GR1.2--SmileyFace
 */
public class FaceViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(600, 750);
        frame.setTitle("An Alien Face");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FaceComponent component = new FaceComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
