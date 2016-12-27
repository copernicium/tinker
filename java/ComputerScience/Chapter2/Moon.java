package ComputerScience.Chapter2;
/**
 * Write a description of class Moon here.
 * 
 * @author Logan Traffas
 * @version 9/21/2016
 * assignment: moon weight
 */
/*    
   Compute your weight on the moon.  

   0.17 moon pounds = 1 earth pound
*/

class Moon{    
    public static void main(String args[]) {    
        double earthWeight=130.0; // weight on earth  
        double moonWeight=0.0; // weight on moon   
        final double CONVERSIONFACTOR = 0.17; //moon pounds per earth pound
        moonWeight = earthWeight*CONVERSIONFACTOR;//your code goes here 
        
        System.out.println(earthWeight + " earth-pounds is equivalent to " + moonWeight + " moon-pounds.");
    }
}