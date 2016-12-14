package computer_science.Chapter2;
/**
 * Write a description of class StringUppercase here.
 * 
 * @author Logan Traffas 
 * @version (9/19/2016
 * assignment: make a string and convert each character to lower case
 */
public class StringUppercase
{
    public static void main(String[] args){
        String test = "This is a Test";
        String testLowerCase = test.toLowerCase();
        System.out.println(testLowerCase);
        String bigTestString = testLowerCase.toUpperCase();
        System.out.println(bigTestString);
    }
}
