package ComputerScience.Chapter4;
import java.util.Scanner;
/**
 * A class which helps calculates all of the information about a triangle given three points
 *
 * @author Logan Traffas
 * @version 11/3/2016
 * assignment: Chapter 04--Fundamental Data Types-P4.3
 */
public class Triangle {
	/**
	 * Stores the data representing a point on a Cartesian plane
	 */
	public static class Point{
		private double x;
		private double y;

		/**
		 * Fetches the x coordinate
		 * @return the x coordinate
		 */
		public double getX(){
			return  x;
		}

		/**
		 * Fetches the y coordinate
		 * @return the y coordinate
		 */
		public double getY(){
			return y;
		}
		public Point(){
			x = 0;
			y = 0;
		}
		public Point(double x,double y){
			this.x = x;
			this.y = y;
		}
	}
	private Point one, two, three;//points
	private double a, b, c;//sides
	private double A, B, C;//angles

	public Triangle(){
		one = new Point();
		two = new Point();
		three = new Point();
		a = 0;
		b = 0;
		c = 0;
		A = 0;
		B = 0;
		C = 0;
	}

	/**
	 * Prompts the user to input the coordinates of a point
	 * @return a point constructed from user input
	 */
	public static Point inputPoint(){
		Scanner in = new Scanner(System.in);
		System.out.print("Input x coordinate: ");
		double x = in.nextDouble();
		System.out.print("Input y coordinate: ");
		double y = in.nextDouble();
		return new Point(x,y);
	}

	/**
	 * Calculates the length of a side given two points
	 * @param pt1 the starting point of the side
	 * @param pt2 the ending point of the side
	 * @return the length of the side
	 */
	private static double calculateLength(Point pt1, Point pt2){
		return Math.sqrt(Math.pow((pt2.x-pt1.x),2) + Math.pow((pt2.y-pt1.y),2));
	}

	/**
	 * Calculates the length of side a
	 */
	private void setLengthA(){
		this.a = calculateLength(this.one,this.two);
	}
	/**
	 * Calculates the length of side b
	 */
	private void setLengthB(){
		this.b = calculateLength(this.two,this.three);
	}
	/**
	 * Calculates the length of side c
	 */
	private void setLengthC(){
		this.c = calculateLength(this.three,this.one);
	}

	/**
	 * Sets all of the side lengths
	 */
	public void setLengths(){
		this.setLengthA();
		this.setLengthB();
		this.setLengthC();
	}

	/**
	 * Fetches side length a
	 * @return side length a
	 */
	public double getLengthA(){
		return this.a;
	}
	/**
	 * Fetches side length b
	 * @return side length b
	 */
	public double getLengthB(){
		return this.b;
	}
	/**
	 * Fetches side length c
	 * @return side length c
	 */
	public double getLengthC(){
		return this.c;
	}

	/**
	 * Calculates the perimeter of the triangle given its side lengths
	 * @return the perimeter of the triangle
	 */
	public double calculatePermiter(){
		return this.a + this.b + this.c;
	}

	/**
	 * Calculates the area of the triangle given its side lengths
	 * @return the area of the triangle
	 */
	public double calculateArea(){
		double s = (a + b + c)/2.0;
		return Math.sqrt(s*(s - a)*(s - b)*(s - c));
	}

	/**
	 * Sets the first point of the triangle
	 * @param one the value to set the point to
	 */
	private void setPointOne(Point one){
		this.one = one;
	}

	/**
	 * Sets the second point of the triangle
	 * @param two the value to set the point to
	 */
	private void setPointTwo(Point two){
		this.two = two;
	}

	/**
	 * Sets the third point of the triangle
	 * @param three the value to set the point to
	 */
	private void setPointThree(Point three){
		this.three = three;
	}

	/**
	 * Calculates a given angle corresponding to the side length parameter a
	 * @param a the first side length
	 * @param b the second side length
	 * @param c the third side length
	 * @return the angle corresponding to side length parameter a
	 */
	private static double calculateAngle(double a, double b, double c){
		double A = Math.acos((Math.pow(b,2) + Math.pow(c,2) - Math.pow(a,2))/(2*b*c));
		return A;
	}

	/**
	 * Sets angle A
	 */
	private void setAngleA(){
		this.A = calculateAngle(this.a,this.b,this.c);
	}
	/**
	 * Sets angle B
	 */
	private void setAngleB(){
		this.B = calculateAngle(this.b,this.c,this.a);
	}
	/**
	 * Sets angle C
	 */
	private void setAngleC(){
		this.C = calculateAngle(this.c,this.a,this.b);
	}

	/**
	 * Fetches angle A
	 * @return angle A
	 */
	public double getAngleA(){
		return this.A;
	}
	/**
	 * Fetches angle B
	 * @return angle B
	 */
	public double getAngleB(){
		return this.B;
	}
	/**
	 * Fetches angle C
	 * @return angle C
	 */
	public double getAngleC(){
		return this.C;
	}

	/**
	 * Sets all of the angles at once
	 */
	public void setAngles(){
		this.setAngleA();
		this.setAngleB();
		this.setAngleC();
	}

	public static void main(String[] args){
		Triangle triangle = new Triangle();
		System.out.println("For point one");
		triangle.setPointOne(Triangle.inputPoint());
		System.out.println("For point two");
		triangle.setPointTwo(Triangle.inputPoint());
		System.out.println("For point three");
		triangle.setPointThree(Triangle.inputPoint());
		triangle.setLengths();
		System.out.println("The side lengths are   a: " + triangle.getLengthA() + "  b: " + triangle.getLengthB() + "  c: " + triangle.getLengthC());
		System.out.println("The perimeter is: " + triangle.calculatePermiter());
		System.out.println("The area is: " + triangle.calculateArea());
		triangle.setAngles();
		System.out.println("The angles are   A: " + triangle.getAngleA() + " B: " + triangle.getAngleB() + "  C: " + triangle.getAngleC());
	}
}
