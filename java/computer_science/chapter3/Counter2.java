/**
 * Write a description of class Counter2 here.
 * 
 * @author Logan Traffas
 * @version 9/30/2016
 * assignment Chapter 03--Implementing Classes--E3.2
 */
public class Counter2
{
    private int value; 
    private int maximum;
    public void click(){
        value = Math.min(value+1,maximum);
    }
    public int getValue(){
        return value;
    }
    public void undo(){
        value = Math.max(value-1, 0);
    }
    public void setLimit(int newmax){
        maximum = Math.min(newmax, 9999);
    }
    
    public static void main(String[] args){
        Counter2 counter2 = new Counter2();
        counter2.setLimit(1);
        counter2.click();
        System.out.println("Add one (limited to 1): " + counter2.getValue());
        counter2.click();
        System.out.println("Add one (limited to 1): " + counter2.getValue());
        counter2.undo();
        System.out.println("Undo: " + counter2.getValue());
        counter2.undo();
        System.out.println("Undo: " + counter2.getValue());
        counter2.undo();
        System.out.println("Undo: " + counter2.getValue());
    }
}
