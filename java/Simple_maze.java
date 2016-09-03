public class Simple_maze{
	static final int X_LIM=10;
	static final int Y_LIM=10;
	
	public static class Location{
		private int x, y;
		public void set(int a,int b){
			assert(a>=0);
			assert(b>=0);
			assert(a<X_LIM);
			assert(b<Y_LIM);
			x=a;
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
			if(x!=test.x) return false;
			return y==test.y;
		}
		public Location(){
			x=0;
			y=0;
		}
	}
	public Location loc;
	public Location target;
	
	public boolean done(){
		return this.loc.equals(this.target);
	}
	
	public static void main(String[] args){
		Simple_maze maze=new Simple_maze();
		Location l = new Location();
		System.out.println(l.toString());
		l.set(9,9);
		System.out.println(l.toString());
		
		while(!maze.done()){
			
		}
		System.out.println("End");
	}
	
	public Simple_maze(){		
		loc = new Location();
		target = new Location();
	}
}