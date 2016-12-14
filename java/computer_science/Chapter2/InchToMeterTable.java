package computer_science.Chapter2;
/**
 * This program displays a conversion table of inches to meters. 
 * 
 * @author Logan Traffas
 * @version 9/21/2016
 * assignment inches to meters
 */
// meters = inches/39.37  
class InchToMeterTable {  
    public static void main(String args[]) {  
        double inches=0.0, meters=0.0; 
        int lineCounter=0; 

        for(inches = 1.0; inches <= 144.0; inches+=1.0) 
        { 
            meters = inches/39.37;
            System.out.println("Inches: " + inches + "\tMeters: " + meters); // convert to meters
                        
            lineCounter++;            
            if(lineCounter%12==0) System.out.print("\n");
        } 
    } 
}