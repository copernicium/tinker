public class Simple_maze{
	static final int X_LIM=10;
	static final int Y_LIM=10;
	
	public static class Location{
		private int x, y;
		public void set(int a,int b){
			assert(a>=0);
			assert(a<X_LIM);
			x=a;
			assert(b>=0);
			assert(b<Y_LIM);
			y=b;
		}
		public int getx(){
			return x;
		}
		public int gety(){
			return y;
		}
		@Override public String toString(){
			return "(" + x + "," + y + ")";
		}
		@Override public boolean equals(Object b){
			if(!(b instanceof Location)) return false;
			Location test=(Location)b;
			if(this.x!=test.x) return false;
			return this.y==test.y;
		}
		public Location(){
			x=0;
			y=0;
		}
		public Location(int a,int b){
			x=a;
			y=b;
		}
	}
	public Location loc;
	public Location target;
	
	public boolean done(){
		return this.loc.equals(this.target);
	}
	
	public static void main(String[] args){
		Location a = new Location();
		Location b = new Location(9,9);
		
		System.out.println(a.toString());
		System.out.println(b.toString());
		
		System.out.println(a.equals(b));
		
		a.set(2,2);
		
		System.out.println(a.getx());
		System.out.println(a.gety());
		
		System.out.println(b.getx());
		System.out.println(b.gety());
		
		System.out.println("End");
	}
	
	public Simple_maze(){		
		loc = new Location();
		target = new Location();
	}
}