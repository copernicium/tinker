
/**
 * Draw a picture of two people on a green hillside,
 * with the sun to their right.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Hillside
{
    // The hillside.
    private Circle hill;
    private Circle sun;
    // The larger figure.
    private Person person1;
    // The smaller figure.
    private Person person2;
    
    /**
     * Constructor for objects of class Hillside
     */
    public Hillside()
    {
    }
    
    /**
     * Draw two figures on a hillside with the sun.
     */
    public void draw()
    {
        // Only create the objects if it hasn't already been drawn.
        if(hill == null) {
            hill = new Circle();
            hill.changeColor("green");
            hill.changeSize(1000);
            hill.moveHorizontal(-500);
            hill.moveVertical(125);

            sun = new Circle();
            sun.changeColor("yellow");
            sun.changeSize(30);
            sun.moveHorizontal(150);
            sun.moveVertical(50);

            person1 = new Person();
            person1.changeSize(50, 25);
            person1.moveHorizontal(-30);
            person1.moveVertical(-8);

            person2 = new Person();
            person2.changeSize(40, 20);
            person2.moveHorizontal(-3);
            person2.moveVertical(2);
            
            hill.makeVisible();
            sun.makeVisible();
            person1.makeVisible();
            person2.makeVisible();

        }
    }

}
