package computer_science.chapter2;
/**
 * Write a description of class ConcatDemo here.
 * 
 * @author Logan Traffas
 * @version 9/19/2016
 * assignment: play with String's + operator Chapter 2
 */
public class ConcatDemo2
{
   public static void main(String[] args)
   {
      String animal1 = "quick brown fox";
      String animal2 = "lazy dog";
 
      String article = "the";
      String action = "jumps over";
 
      String message = article + " " + animal1 + " " + action + " " + article + " " + animal2;
      System.out.println(message);
   }
}