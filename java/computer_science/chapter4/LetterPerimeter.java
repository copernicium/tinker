package computer_science.chapter4;
/**
 * A class which calculates the perimeter of a piece of paper and the length of its diagonal
 * 
 * @author Logan Traffas
 * @version 10/24/2016
 * assignment: Chapter 04--Fundamental Data Types-E4.2
 */
public class LetterPerimeter
{
    public static void main(String[] args){
        final double LETTERWIDTH = 8.5;//inches
        final double LETTERHEIGHT = 11.0;//inches
        double perimeter = LETTERWIDTH*2 + LETTERHEIGHT*2;//inches
        double diagonal = Math.pow(Math.pow(LETTERWIDTH,2)+Math.pow(LETTERHEIGHT,2),.5);//inches
        System.out.println("The permiter of a letter is " + perimeter + " inches. Its diagonal is " + diagonal + " inches.");
        
    }
}
