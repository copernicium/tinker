package ComputerScience.Graphics.Face;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.QuadCurve2D;
import javax.swing.JComponent;

/**
 * A component that draws an alien face
 *
 * @Author Logan Traffas
 * @Date 5/8/2017.
 * @Version 1.0.0
 * @Assignment GR1.2--SmileyFace
 */
public class FaceComponent extends JComponent {
    @Override
    public void paintComponent(Graphics g){
        // Recover Graphics2D
        Graphics2D g2 = (Graphics2D) g;

        // Draw the head
        Ellipse2D.Double head = new Ellipse2D.Double(5, 10, 450, 550);//200, 150
        g2.setColor(Color.LIGHT_GRAY);
        g2.fill(head);
        BasicStroke stroke = new BasicStroke((float)3.0, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(stroke);
        g2.draw(head);

        // Draw the sunglasses (eyes)
        {
            g2.setColor(Color.BLACK);
            Rectangle sunglassesLens = new Rectangle(80, 150, 110, 70);
            g2.fill(sunglassesLens);
            sunglassesLens.translate(200, 0);
            g2.fill(sunglassesLens);
        }
        {
            g2.setStroke(new BasicStroke(20));
            Line2D.Double sunglassesFrame = new Line2D.Double(33, 150, 427, 150);
            g2.draw(sunglassesFrame);
        }

        //New mouth
        Util.Point<Double> start = new Util.Point<Double>(100.0,300.0), end = new Util.Point<Double>(300.0,300.0),control = new Util.Point<Double>((end.getX() - start.getX()) / 2.0 ,start.getY() + 300.0);
        QuadCurve2D mouth = new QuadCurve2D.Double();
        mouth.setCurve(start.getX(), start.getY(), control.getX(), control.getY(), end.getX(), end.getY());
        g2.setColor(Color.RED);
        g2.setStroke(new BasicStroke(30));
        g2.draw(mouth);

        // Draw the greeting
        g2.setFont(new Font("Comic Sans MS", Font.PLAIN, 30));
        g2.setColor(Color.BLACK);
        g2.drawString("hello darkness my old friend", 60, 600);
    }
}
