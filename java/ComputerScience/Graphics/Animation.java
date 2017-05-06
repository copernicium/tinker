package ComputerScience.Graphics;

import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;

/**
 * @Author Logan Traffas
 * @Date 5/5/2017.
 * @Version 1.0.0
 * @Assignment
 */
public class Animation {
    public static void main(String[] args){
        Dimension fullscreen = Util.Graphics.generateFullscreenDimension(true);
        double ratio = (double)fullscreen.width / (double)fullscreen.height;
        final Color color = Color.black;
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(color);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for(int i = 0; i < fullscreen.height; i++){
            Dimension size = new Dimension((int)(ratio * (double)i),i);
            frame.setSize(size);
            frame.getContentPane().setPreferredSize(new Dimension(size));
        }
    }
}
