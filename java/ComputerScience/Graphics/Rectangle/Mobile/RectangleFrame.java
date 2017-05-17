package ComputerScience.Graphics.Rectangle.Mobile;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * This frame contains a moving rectangle.
 *
 * @Author Logan Traffas
 * @Date 5/12/2017.
 * @Version 1.0.0
 * @Assignment GR10.9--Processing Timer Events
 */
public class RectangleFrame extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;

    private RectangleComponent scene1;
    private RectangleComponent scene2;

    class TimerListener implements ActionListener {
        private Util.Point<Integer> moveBy;

        public void actionPerformed(ActionEvent event) {
            scene1.moveRectangleBy(moveBy.getX(), moveBy.getY());
            scene2.moveRectangleBy(moveBy.getX(), moveBy.getY());
        }

        public TimerListener(){
            this(1,1);
        }

        public TimerListener(int a, int b){
            this.moveBy = new Util.Point(a,b);
        }
    }

    public RectangleFrame() {
        scene1 = new RectangleComponent();
        add(scene1 );

        setSize(FRAME_WIDTH, FRAME_HEIGHT);

        ActionListener listener = new TimerListener();

        final int DELAY = 10; // Milliseconds between timer ticks
        Timer t = new Timer(DELAY, listener);
        t.start();
    }
}
