package computer_science.chapter5;

import MySystem.MySystem;

import java.util.Scanner;

/**
 * Simulated the minivan door opening mechanisms
 *
 * @author Logan Traffas
 * @version 12/9/2016
 * assignment: Chapter 05--Decisions--P5.14 Minivan Sliding Doors
 */
public class Minivan {
	public enum Side{LEFT,RIGHT}

	public static class SidePair<T>{
		T left;
		T right;
		public T get(Side side){
			switch(side){
				case LEFT:
					return this.left;
				case RIGHT:
					return this.right;
				default:
					MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
			}
			return this.left;//should never get to this line
		}
		public void set(Side side, T value){
			switch(side){
				case LEFT:
					this.left = value;
					return;
			case RIGHT:
				this.right = value;
				return;
			default:
				MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
			}
		}

		public String toString(){
			return "SidePair(left:" + this.left.toString() + " right:" + this.right.toString()+ ")";
		}

		public SidePair(T left,T right){
			this.left = left;
			this.right = right;
		}
	}
	public enum GearShiftSetting{
		P,N,D,_1,_2,_3,R;
		public static GearShiftSetting parseGear(String in){
			MySystem.myAssert(in.length() == 1,MySystem.getFileName(),MySystem.getLineNumber());
			switch(Character.toUpperCase(in.charAt(0))){
				case 'P': return GearShiftSetting.P;
				case 'N': return GearShiftSetting.N;
				case 'D': return GearShiftSetting.D;
				case '1': return GearShiftSetting._1;
				case '2': return GearShiftSetting._2;
				case '3': return GearShiftSetting._3;
				case 'R': return GearShiftSetting.R;
				default:
					MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
			}
			return GearShiftSetting._1;//will never reach this line
		}
	}
	public enum Door{OPEN,CLOSED}

	private SidePair<Door> doors;
	private GearShiftSetting gear;
	private boolean childLock;
	private boolean masterUnlock;
	private SidePair<Boolean> dashSwitches;
	private SidePair<Boolean> insideHandels;
	private SidePair<Boolean> outsideHandles;
	/*
		"A minivan has two sliding doors. Each door can be opened by
		either a dashboard switch, its inside handle, or its outside
		handle. However, the inside handles do not work if a child
		lock switch is activated. In order for the sliding doors to open,
		the gear shift must be in park, and the master unlock switch
		 must be activated. (This book’s author is the long­suffering
		owner of just such a vehicle.)

		Your task is to simulate a portion of the control software for
		the vehicle. The input is a sequence of values for the switches
		and the gear shift, in the following order:
		•	Dashboard switches for left and right sliding door,
		child lock, and master unlock (0 for off or 1 for activated)
		•	Inside and outside handles on the left and right sliding doors (0 or 1)
		• Thegearshiftsetting(oneofPND123R). Atypicalinputwouldbe 0 0 0 1 0 1 0 0 P.

		Print “left door opens” and/or “right door opens” as appropriate. If neither door opens, print “both doors stay closed”."
		 (See Horstmann p234-235)

		Submit your code AND a sample run of your program showing all possible inputs/outputs
	 */

	public void setData(){
		Scanner input = new Scanner(System.in);
		System.out.print("Enter data set: ");
		String longData = input.nextLine().trim();
		String data = "";
		for(char a: longData.toCharArray()){
			if(a == ' ') continue;
			data += a;
		}
		this.parseMinivan(data);
	}

	public void parseMinivan(String data){
		this.dashSwitches.set(Side.LEFT,MySystem.parseBoolean(data.substring(0,1)));
		this.dashSwitches.set(Side.RIGHT,MySystem.parseBoolean(data.substring(1,2)));
		this.childLock = MySystem.parseBoolean(data.substring(2,3));
		this.masterUnlock = MySystem.parseBoolean(data.substring(3,4));
		this.insideHandels.set(Side.LEFT,MySystem.parseBoolean(data.substring(4,5)));
		this.insideHandels.set(Side.RIGHT,MySystem.parseBoolean(data.substring(5,6)));
		this.outsideHandles.set(Side.LEFT,MySystem.parseBoolean(data.substring(6,7)));
		this.outsideHandles.set(Side.RIGHT,MySystem.parseBoolean(data.substring(7,8)));
		this.gear = GearShiftSetting.parseGear(data.substring(8,9));
	}

	public String toString(){
		String s = "Minivan(";
		s += this.dashSwitches.get(Side.LEFT) + " ";
		s += this.dashSwitches.get(Side.RIGHT) + " ";
		s += this.childLock + " ";
		s += this.masterUnlock + " ";
		s += this.insideHandels.get(Side.LEFT) + " ";
		s += this.insideHandels.get(Side.RIGHT) + " ";
		s += this.outsideHandles.get(Side.LEFT) + " ";
		s += this.outsideHandles.get(Side.RIGHT) + " ";
		s += this.gear.toString() + " ";
		s += this.doors.get(Side.LEFT).toString() + " ";
		s += this.doors.get(Side.RIGHT).toString() + ")";
		return s;
	}

	public void open(Side side){
		if(this.insideHandels.get(side) || this.outsideHandles.get(side) || this.dashSwitches.get(side)){
			if(this.masterUnlock && !this.childLock && this.gear == GearShiftSetting.P) {
				this.doors.set(side,Door.OPEN);
				System.out.println(side + " door opens.");
				return;
			}
		}
		System.out.println(side + " door stays closed.");
	}

	public Minivan(){
		this.doors = new SidePair<>(Door.CLOSED,Door.CLOSED);;
		this.gear = GearShiftSetting.P;
		this.childLock = false;
		this.masterUnlock = false;
		this.dashSwitches = new SidePair<>(false,false);
		this.insideHandels = new SidePair<>(false,false);
		this.outsideHandles = new SidePair<>(false,false);
	}

	public static void main(String[] args){
		Minivan minivan = new Minivan();
		minivan.setData();
		minivan.open(Side.LEFT);
		System.out.println("result: " + minivan.toString());
	}
}
