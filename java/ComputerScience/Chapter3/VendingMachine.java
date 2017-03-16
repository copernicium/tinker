package ComputerScience.Chapter3;
/**
 * Class for simulating a vending machine
 * 
 * @author Logan Traffas
 * @version 10/2/16
 * assignment: Lab 3.2--Vending Machine
 */
public class VendingMachine
{
    private int canCount;
    private int tokenCount;
    
   /**
    *   Adds a given number of cans to the machine
    *   @param cans Number of cans to add to the vending machine
    */
    public void fillUp(int cans){
        canCount += cans;
    }
    
    /**
    *   Adds a token to the vending machine if there are available cans and then removes one can. If no cans are available, it prints a notice
    */
    public void insertToken(){
        if(canCount > 0){
            tokenCount++;
            canCount--;
        } else {
            System.out.print("\nVending machine is out of cans.\n");
        }
    }
    
    /**
    *   Fetches the current number of tokens in the vending machine
    *   @return The current number of tokens in the vending machine
    */
    public int getTokenCount(){
        return tokenCount;
    }
    
    /**
    *   Fetches the current number of cans in the vending machine
    *   @return The current amount of cans in the vending machine
    */
    public int getCanCount(){
        return canCount;
    }
    
    public VendingMachine(){
        canCount = 0;
        tokenCount = 0;
    }
    
    public VendingMachine(int cans){
        canCount = cans;
        tokenCount = 0;
    }
}