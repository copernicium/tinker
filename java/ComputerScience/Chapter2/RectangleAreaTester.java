package ComputerScience.Chapter2;
import java.awt.Rectangle;
/**
 * Write a description of class RectangleAreaTester here.
 * 
 * @author Logan Traffas
 * @version 9/21/2016
 * assignment: E2.1
 */
public class RectangleAreaTester
{
    public static void main(String[] args){
        Rectangle rectangle = new Rectangle(0,0,20,10);
        double area = rectangle.getWidth()*rectangle.getHeight();
        System.out.println(area);
    }
}
