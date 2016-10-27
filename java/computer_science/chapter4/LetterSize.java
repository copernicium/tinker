package computer_science.chapter4;
/**
 * Write a description of class LetterSize here.
 * 
 * @author Logan Traffas
 * @version 10/24/2016
 * assignment: Chapter 04--Fundamental Data Types-E4.1
 */
public class LetterSize
{
    public static void main(String[] args){
        final double MILLIMETERSPERINCH = 25.4;//number of millimeters in an inch
        final double LETTERWIDTH = 8.5;//inches
        final double LETTERHEIGHT = 11.0;//inches
        double letterWidth = LETTERWIDTH*MILLIMETERSPERINCH;//millimeters
        double letterHeight = LETTERHEIGHT*MILLIMETERSPERINCH;//millimeters
        System.out.println("A letter of dimension " + LETTERWIDTH + " inches by " + LETTERHEIGHT + " inches can be written as " + letterWidth + " mm by " + letterHeight + " mm.");
        
    }
}
