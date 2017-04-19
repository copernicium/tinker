package ComputerScience.Chapter13.Triangle;

/**
 *  Tests the Triangle class
 */
public class TriangleTester{
	public static void main(String[] args){
		Triangle t = new Triangle(10);
		int area = t.getArea();
		System.out.println("Area: " + area);
		System.out.println("Expected: 55");
	}
}

