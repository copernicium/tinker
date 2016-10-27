package computer_science.chapter2;
import java.awt.Rectangle;
/**
 * Write a description of class FourRectanglePrinter here.
 * 
 * @author Logan Traffas
 * @version 9/25/2016
 * assignment: Chapter 2 individual assignment
 */
public class FourRectanglePrinter
{
   public static void main(String[] args)
   {
      Rectangle rectangle = new Rectangle(0, 0, 50, 100);
      System.out.println(rectangle);
	  rectangle.setLocation(50,0);
      System.out.println(rectangle);
	  rectangle.setLocation(50,100);
      System.out.println(rectangle);
	  rectangle.setLocation(0,100);
      System.out.println(rectangle);
   }
}