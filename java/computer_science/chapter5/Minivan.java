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
			MySystem.myAssert(in.length() == 1 || in.length() == 2,MySystem.getFileName(),MySystem.getLineNumber());
			switch(MySystem.stringToUpperCase(in)){
				case "P": return GearShiftSetting.P;
				case "N": return GearShiftSetting.N;
				case "D": return GearShiftSetting.D;
				case "1":
				case "_1": return GearShiftSetting._1;
				case "2":
				case "_2": return GearShiftSetting._2;
				case "3":
				case "_3": return GearShiftSetting._3;
				case "R": return GearShiftSetting.R;
				default:
					MySystem.println("\"" + in + "\"", MySystem.getFileName(),MySystem.getLineNumber());
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

	private static Side parseSide(String s){
		s = MySystem.stringToUpperCase(s);
		switch(s){
			case "LEFT": return Side.LEFT;
			case "RIGHT": return Side.RIGHT;
			default:
				MySystem.nyi(MySystem.getFileName(),MySystem.getLineNumber());
 		}
 		return Side.LEFT;//will never reach this line
	}

	private static int getBooleanLength(String s){
		if(s.length() == 0) return 0;
		if(s.charAt(0) == '1' || s.charAt(0) == '0') return 1;
		final String true1 = "true", false1 = "false";
		if(s.length() < true1.length()) return 0;
		if(s.substring(0,true1.length()).equals(true1)) return  true1.length();
		if(s.length() < false1.length()) return 0;
		if(s.substring(0,false1.length()).equals(false1)) return false1.length();
		return 0;
	}

	public void parseMinivan(String data){
		String d = data;
		this.dashSwitches.set(Side.LEFT,MySystem.parseBoolean(d.substring(0,getBooleanLength(d))));
		d = d.substring(getBooleanLength(d));
		this.dashSwitches.set(Side.RIGHT,MySystem.parseBoolean(d.substring(0,getBooleanLength(d))));
		d = d.substring(getBooleanLength(d));
		this.childLock = MySystem.parseBoolean(d.substring(0,getBooleanLength(d)));
		d = d.substring(getBooleanLength(d));
		this.masterUnlock = MySystem.parseBoolean(d.substring(0,getBooleanLength(d)));
		d = d.substring(getBooleanLength(d));
		this.insideHandels.set(Side.LEFT,MySystem.parseBoolean(d.substring(0,getBooleanLength(d))));
		d = d.substring(getBooleanLength(d));
		this.insideHandels.set(Side.RIGHT,MySystem.parseBoolean(d.substring(0,getBooleanLength(d))));
		d = d.substring(getBooleanLength(d));
		this.outsideHandles.set(Side.LEFT,MySystem.parseBoolean(d.substring(0,getBooleanLength(d))));
		d = d.substring(getBooleanLength(d));
		this.outsideHandles.set(Side.RIGHT,MySystem.parseBoolean(d.substring(0,getBooleanLength(d))));
		d = d.substring(getBooleanLength(d));
		this.gear = GearShiftSetting.parseGear(d);
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
		{//test input
			Minivan minivan = new Minivan();
			minivan.setData();
			Scanner input = new Scanner(System.in);
			System.out.print("Input the side of the door: ");
			String side = input.next().trim();
			minivan.open(Minivan.parseSide(side));
			System.out.println("result: " + minivan.toString());
		}
		{//test all meaningful situations
			Minivan minivan = new Minivan();
			String data = "10010000P";
			minivan.parseMinivan(data);
			minivan.open(Side.LEFT);
			System.out.println(data + " - Should have opened left door.");
			data = "0 0 0 1 0 1 0 0 P";
			minivan.parseMinivan(data);
			minivan.open(Side.RIGHT);
			System.out.println(data + " - Should have opened right door.");
			data = "0 0 0 1 0 1 0 0 N";
			minivan.parseMinivan(data);
			minivan.open(Side.RIGHT);
			System.out.println(data + " - Should not have opened any door.");
		}
		/*{//test all possibilities
			final boolean[] all = {true,false};
			final GearShiftSetting[] allGears = {GearShiftSetting.P,GearShiftSetting.N,GearShiftSetting.D,GearShiftSetting._1,GearShiftSetting._2,GearShiftSetting._3,GearShiftSetting.R};
			final Side[] allSides = {Side.LEFT,Side.RIGHT};
			for(boolean a: all){
				for(boolean b: all){
					for(boolean c: all){
						for(boolean d: all){
							for(boolean e: all){
								for(boolean f: all){
									for(boolean g: all){
										for(boolean h: all){
											for(GearShiftSetting i: allGears){
												for(Side side: allSides){
													String data = "" + a + b + c + d + e + f + g + h + i;
													System.out.println("Next data set is " + data + " for " + side + " side");
													Minivan minivan = new Minivan();
													minivan.parseMinivan(data);
													minivan.open(side);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}*/
	}
}
