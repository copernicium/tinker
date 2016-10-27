package computer_science.chapter2;
/**
 * Write a description of class TrimDemo here.
 * 
 * @author Logan Traffas 
 * @version 9/19/2016
 * assignment: 2.2.4
 */
public class TrimDemo
{
   public static void main(String[] args)
   {
      String sentence1 = "      abc   ";
      String sentence2 = "   def";
      String sentence3 = "ghi         ";
       
      /* Your work goes here */
      String message = sentence1.trim() + sentence2.trim() + sentence3.trim();
      System.out.println(message);
   }
}