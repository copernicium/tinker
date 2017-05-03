package ComputerScience.Graphics.Rectangle;

import java.awt.*;
import javax.swing.*;

/**
 * A component that draws two rectangles.
 *
 * @Author Logan Traffas
 * @Date 5/3/2017.
 * @Version 1.0.0
 * @Assignment Gr1.1--Graphics1--Getting Started
 */
public class RectangleComponent extends JComponent {
    private int frameWidth, frameHeight;
    private static final int TASKBAR_HEIGHT = 40, WINDOW_HEADER_HEIGHT = 20;

    public RectangleComponent(){
        this(0,0);
    }

    public RectangleComponent(Dimension dimension){
        this();
        this.frameWidth = (int)dimension.getWidth();
        this.frameHeight = (int)dimension.getHeight();
        System.out.println("w:" + this.frameWidth + " h:" + this.frameHeight);
    }

    public RectangleComponent(int width, int height){
        super();
        this.frameWidth = width;
        this.frameHeight = height;
    }

    public RectangleComponent(JFrame frame){
        if(frame.getExtendedState() == Frame.MAXIMIZED_BOTH){
            this.frameWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();
            this.frameHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - TASKBAR_HEIGHT;
        } else if (frame.getExtendedState() == Frame.NORMAL){
            this.frameHeight = frame.getHeight();
            this.frameWidth = frame.getWidth();
        }

    }

    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;// Recover Graphics2D

        {
            /*
            final int PADDING = 10, WIDTH = 20, HEIGHT = 20;

            Rectangle upperLeft = new Rectangle(0 + PADDING, 0 + PADDING, WIDTH, HEIGHT);
            g2.draw(upperLeft);

            Rectangle upperRight = new Rectangle(this.frameWidth - PADDING - WIDTH, 0 + PADDING, WIDTH, HEIGHT);
            g2.draw(upperRight);

            Rectangle lowerLeft = new Rectangle(0 + PADDING, this.frameHeight - PADDING - HEIGHT - TASKBAR_HEIGHT - WINDOW_HEADER_HEIGHT, WIDTH, HEIGHT);
            g2.draw(lowerLeft);

            Rectangle lowerRight = new Rectangle(this.frameWidth - PADDING - WIDTH, this.frameHeight - PADDING - HEIGHT - TASKBAR_HEIGHT - WINDOW_HEADER_HEIGHT, WIDTH, HEIGHT);
            g2.draw(lowerRight);

            Rectangle center = new Rectangle((this.frameWidth - WIDTH) / 2, (this.frameHeight - HEIGHT) / 2, WIDTH, HEIGHT);
            g2.draw(center);
            */
        }
        {
            final int PADDING =2, WIDTH = 10, HEIGHT = 10;
            for(int i = 0; i < this.frameWidth; i += WIDTH + PADDING){
                Rectangle top  = new Rectangle(i, PADDING, WIDTH, HEIGHT);
                g2.draw(top);
                Rectangle bottom = new Rectangle(i, this.frameHeight - HEIGHT - TASKBAR_HEIGHT - WINDOW_HEADER_HEIGHT - PADDING, WIDTH, HEIGHT);
                g2.draw(bottom);
            }
        }
    }
}
