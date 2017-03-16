package ComputerScience.Chapter3;
/**
 * tests the door class
 * 
 * @author Logan Traffas
 * @version 10/3/16
 * assignment: Lab 3.1 Door class
 */
public class DoorTest
{
    public static void main(String[] args){
        Door door1 = new Door();
        Door door2 = new Door("front","closed");
        door1.setState("open");
        door1.setName("side");
        System.out.println(door1.getName() + " is " + door1.getState());
        System.out.println(door2.getName() + " is " + door2.getState());
        door1.close();
        door2.open();
        System.out.println(door1.getName() + " is " + door1.getState());
        System.out.println(door2.getName() + " is " + door2.getState());
    }
}
