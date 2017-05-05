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
    private int frameWidth, frameHeight;//px

    private static int TASKBAR_HEIGHT = 40;//px

    public RectangleComponent(){
        this(0,0);
    }

    public RectangleComponent(Dimension dimension){
        this((int)dimension.getWidth(),(int)dimension.getHeight());
    }

    public RectangleComponent(int width, int height){
        super();
        this.frameWidth = width;
        this.frameHeight = height;
    }

    public RectangleComponent(JFrame frame,boolean handleTaskbar){
        switch(frame.getExtendedState()){
            case Frame.MAXIMIZED_BOTH:
                this.frameWidth = (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth() - (frame.getInsets().left + frame.getInsets().right);
                this.frameHeight = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight() - (frame.getInsets().top + frame.getInsets().bottom);
                if(handleTaskbar) this.frameHeight -= TASKBAR_HEIGHT;
                break;
            case Frame.NORMAL:
                this.frameWidth = frame.getContentPane().getWidth();
                this.frameHeight = frame.getContentPane().getHeight();
                break;
            default:
                break;
        }
        System.out.println(this.toString());
    }

    @Override
    public String toString(){
        return "RectangleComponent(width: " + this.frameWidth + " height: " + this.frameHeight + ")";
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;// Recover Graphics2D

        {
            final int PADDING = 20, WIDTH = 10, HEIGHT = 10;//px

            Rectangle upperLeft = new Rectangle(0 + PADDING, 0 + PADDING, WIDTH, HEIGHT);
            g2.fill(upperLeft);

            Rectangle upperRight = new Rectangle(this.frameWidth - PADDING - WIDTH, 0 + PADDING, WIDTH, HEIGHT);
            g2.fill(upperRight);

            Rectangle lowerLeft = new Rectangle(0 + PADDING, this.frameHeight - PADDING - HEIGHT , WIDTH, HEIGHT);
            g2.fill(lowerLeft);

            Rectangle lowerRight = new Rectangle(this.frameWidth - PADDING - WIDTH, this.frameHeight - PADDING - HEIGHT, WIDTH, HEIGHT);
            g2.fill(lowerRight);

            Rectangle center = new Rectangle((this.frameWidth - WIDTH) / 2, (this.frameHeight - HEIGHT) / 2, WIDTH, HEIGHT);
            g2.fill(center);

        }
        {

            final int PADDING = 20, WIDTH = 10, HEIGHT = 10;//px
            int alignedHeight = this.frameHeight - (this.frameHeight % (PADDING + HEIGHT)), alignedWidth = this.frameWidth - (this.frameWidth % (PADDING + WIDTH));
            for(int i = PADDING; i < alignedWidth; i += WIDTH + PADDING){
                Rectangle top  = new Rectangle(i, PADDING, WIDTH, HEIGHT);
                g2.draw(top);
                Rectangle bottom = new Rectangle(i, alignedHeight - HEIGHT, WIDTH, HEIGHT);
                g2.draw(bottom);
            }
            for(int i = PADDING; i < alignedHeight; i += HEIGHT + PADDING){
                Rectangle left  = new Rectangle(PADDING, i, WIDTH, HEIGHT);
                g2.draw(left);
                Rectangle right = new Rectangle(alignedWidth - WIDTH, i, WIDTH, HEIGHT);
                g2.draw(right);
            }

        }
    }
}
