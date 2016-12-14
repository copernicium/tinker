package computer_science.Chapter3;
/**
 * Class for testing the vending machine class
 * 
 * @author Logan Traffas
 * @version 10/2/16
 * assignment: Lab 3.2--Vending Machine
 */
public class VendingMachineTest
{
   public static void main(String[] args)
   {
      VendingMachine machine = new VendingMachine();
      machine.fillUp(10); // Fill up with ten cans
      machine.insertToken();
      machine.insertToken();
      System.out.print("Token count: ");
      System.out.println(machine.getTokenCount());
      System.out.println("Expected: 2");
      System.out.print("Can count: ");
      System.out.println(machine.getCanCount()); 
      System.out.println("Expected: 8");
   } 
}
