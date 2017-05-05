package ComputerScience.Graphics;

import javax.swing.*;
import java.awt.*;

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
        for(int i = 0; i < fullscreen.height; i++){
            Dimension size = new Dimension((int)(ratio * (double)i),i);
            JFrame frame = new JFrame();
            frame.setSize(size);
            frame.getContentPane().setPreferredSize(new Dimension(size));
            frame.pack();
            frame.setVisible(true);
            frame.setVisible(false);
        }
    }
}
