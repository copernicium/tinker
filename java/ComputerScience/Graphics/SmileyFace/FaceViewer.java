package ComputerScience.Graphics.SmileyFace;

import javax.swing.JFrame;

/**
 * @Author Logan Traffas
 * @Date 5/3/2017.
 * @Version 1.0.0
 * @Assignment GR1.2--SmileyFace
 */
public class FaceViewer {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(150, 250);
        frame.setTitle("An Alien Face");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        FaceComponent component = new FaceComponent();
        frame.add(component);

        frame.setVisible(true);
    }
}
