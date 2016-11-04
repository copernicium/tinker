package computer_science.chapter4;
import java.util.Map;
import java.util.Scanner;
/**
 * A class which helps calculates all of the information about a triangle given three points
 *
 * @author Logan Traffas
 * @version 11/3/2016
 * assignment: Chapter 04--Fundamental Data Types-P4.3
 */
public class Triangle {
	public static class Point{
		private double x;
		private double y;
		public double getX(){
			return  x;
		}
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

	public Point inputPoint(){
		Scanner in = new Scanner(System.in);
		System.out.print("Input x coordinate: ");
		double x = in.nextDouble();
		System.out.print("Input y coordinate: ");
		double y = in.nextDouble();
		return new Point(x,y);
	}

	public double calculateLength(Point pt1, Point pt2){
		return Math.sqrt(Math.pow((pt2.x-pt1.x),2) + Math.pow((pt2.y-pt2.y),2));
	}

	public void setLengthA(){
		this.a = calculateLength(this.one,this.two);
	}

	public void setLengthB(){
		this.b = calculateLength(this.two,this.three);
	}

	public void setLengthC(){
		this.c = calculateLength(this.three,this.one);
	}

	public double calculatePermiter(){
		return this.a + this.b + this.c;
	}

	public double calculateArea(){
		double s = (a + b + c)/2.0;
		return Math.sqrt(s*(s - a)*(s - b)*(s - c));
	}

	public void setPointOne(Point one){
		this.one = one;
	}

	public void setPointTwo(Point two){
		this.two = two;
	}


	public void setPointThree(Point three){
		this.three = three;
	}

	public static void main(String[] args){
		Triangle triangle = new Triangle();
	}
}
