package ComputerScience.Chapter3;
/**
 * Write a description of class Counter here.
 * 
 * @author Logan Traffas
 * @version 9/30/2016
 * assignment Chapter 03--Implementing Classes--E3.1
 */
public class Counter
{
    private int value; 
    public void click(){
        value++;
    }
    public int getValue(){
        return value;
    }
    public void undo(){
        value = Math.max(--value, 0);
    }
    
    public static void main(String[] args){
        Counter counter = new Counter();
        counter.click();
        System.out.println("Add one: " + counter.getValue());
        counter.click();
        System.out.println("Add one: " + counter.getValue());
        counter.undo();
        System.out.println("Undo: " + counter.getValue());
        counter.undo();
        System.out.println("Undo: " + counter.getValue());
        counter.undo();
        System.out.println("Undo: " + counter.getValue());
    }
}
