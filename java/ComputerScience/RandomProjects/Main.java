package ComputerScience.RandomProjects;
import java.util.Vector;
import java.lang.Math;
/**
 * Contains all of the code needed to construct and solve a maze
 */
public class Main{
    /**
     * Stores all of the moves the solver can make
     */
    public enum Move{LEFT,RIGHT,UP,DOWN};
    
    /**
     * A class for storing a location on a Cartesian plane
     */
    public static class Location{
        private int x;
        private int y;
        /**
         * Fetches the x coordinate
         * @return returns the x coordinate
         */
        public int getX(){
            return x;
        }
        /**
         * Fetches the y coordinate
         * @return returns the y coordinate
         */
        public int getY(){
            return y;
        }
        /**
         * Moves the location one to the left
         */
        public void left(){
            x--;
        }
        /**
         * Moves the location one to the right
         */
        public void right(){
            x++;
        }
        /**
         * Moves the location one to the up
         */
        public void up(){
            y--;
        }
        /**
         * Moves the location one to the down
         */
        public void down(){
            y++;
        }
        /**
         * Checks if two locations are equal
         * @param b The variable to compare
         * @return If the two variables are equal
         */
        public boolean equals(final Location b){
            return this.x == b.x && this.y == b.y;
        }
        /**
         * Moves the location based on a Move paramter
         * @param move the direction to move
         */
        public void move(Move move){
           switch(move){
               case UP:
                    this.up();
                    break;
               case DOWN:
                    this.down();
                    break;
               case LEFT:
                    this.left();
                    break;
               case RIGHT:
                    this.right();
                    break;
               default: 
                    assert(false);
            }
        }
        /**
         * Returns a move that moves the solver in the opposite direction
         * @param a The move that the opposite of is requested
         * @return The opposite move from the parameter
         */
        public static Move oppositeMove(final Move a){
        	if(a==Move.UP)return Move.DOWN;
        	else if(a==Move.DOWN)return Move.UP;
        	else if(a==Move.RIGHT)return Move.LEFT;
        	return Move.RIGHT;
        }

        public Location(){
            x = 0;
            y = 0;
        }
        /**
         * Allows for print of the location
         * @return a string containing the x and y coordinates  of the location
         */
        public String toString(){
            return "(" + x + "," + y + ")";
        }
        public Location(int a, int b){
            x = a;
            y = b;
        }
    }
    
    /**
     * Stores all of the relevant information for the maze solver
     */
    public static class Maze{
        private Location solver;
        private Location target;
        private int xLimit, yLimit; // assuming 0 is the other limit of the maze
        private Vector<Location> blocks;
        /**
         * Finds all of the valid moves the solver can make without leaving the maze or occupying the same location as a wall
         * @return all of the valid moves
         */
        public Vector<Move> getPossibleMoves(final Location loc,final Vector<Location> visited, boolean goBack){
            Move[] moves = {Move.UP,Move.DOWN,Move.LEFT,Move.RIGHT};
            Vector<Move> possibleMoves = new Vector<Move>(0);
            for(Move move: moves){
                Location tester = loc;
                tester.move(move);
                if(!goBack){
                    if(!this.blocks.contains(tester) && tester.getY()>=0 && tester.getX()>=0 && tester.getY()<this.yLimit && tester.getX()<this.xLimit) possibleMoves.add(move);
                }
                else {
                    if(!this.blocks.contains(tester) && !visited.contains(tester) && tester.getY()>=0 && tester.getX()>=0 && tester.getY()<this.yLimit && tester.getX()<this.xLimit) possibleMoves.add(move);
                }
            }
            return possibleMoves;
        }
        public int weight(final Maze a,Vector<Move> possibleMoves){
        	int priority=0; 
        	Maze m=a;
        	for(int i=0; i<possibleMoves.size(); i++){
        		Maze b=a;
        		b.solver.move(possibleMoves.get(i));
        		double test = Math.pow((Math.pow((double)(b.solver.getX() - a.target.getX()) , 2.0) + Math.pow((double)(b.solver.getY() - a.target.getY()) , 2.00)) , 1/2);
        		double compare = Math.pow((Math.pow((double)(a.solver.getX() - a.target.getX()),2.0)+Math.pow((double)(a.solver.getY() - a.target.getY()),2.0)),1/2);
        		if(test<compare){
        			priority=i;	
        			m=b;
        		}
        	}
        	return priority;
        }
        /**
         * TODO: add documentation
         */
        public Vector<Move> getPath(){
            boolean found;
            Vector<Move> path = new Vector<Move>(0);
            Vector<Location> visited = new Vector<Location>(0);
            visited.add(this.solver);
            while(true){
                Vector<Move> possibleMoves = getPossibleMoves(this.solver,visited,false);
                
                if(possibleMoves.size()>0){
            		path.add(possibleMoves.get(weight(this,possibleMoves)));
            		this.solver.move(path.get(path.size()-1));
            		visited.add(this.solver);
            	}
            	else{
            		this.solver.move(Location.oppositeMove(path.elementAt(path.size()-1)));
            		if(path.size()>0)path.removeElementAt(path.size()-1);
            		else break;//failed no possible moves remaining
            	}
            	found=this.solver.equals(this.target);
            	if(found)break;
            	System.out.println(path.toString());
            }
            return path;//TODO: Remove this
        }
        /**
         * Set the solver to a new location
         */
        public void setSolver(final Location solver){
            this.solver = solver;
        }
         /**
         * Retrieve the solver's location
         * @return the location of the sovler
         */
        public Location getSolver(){
            return solver;
        }
         /**
         * Set the target to a new location
         */
        public void setTarget(final Location target){
            this.target = target;
        }
         /**
         * Retrieve the target's location
         * @return the location of the target
         */
        public Location getTarget(){
            return target;
        }
        /**
         * Checks if the maze has been solved
         * @return if the maze has been solved
         */
        public boolean finished(){
            return solver==target;
        }
        /**
         * Generates all of the walls for a given maze
         * @return a vector of walls for the maze
         */
        public Vector<Location> generateWalls(final int xLimit,final int yLimit){
            return new Vector<Location>(0);
        }
        /**
         * Generates a maze given starting arguments
         * @return a new, random maze
         */
        public Maze generate(final int xLimit, final int yLimit){
            Maze maze = new Maze(xLimit,yLimit);
            
            return maze;
        }
        /**
         * Allows for printing of the maze
         * @return a string that contains a maze graphic
         */
        public String toString(){
            String[][] lines = new String[xLimit][yLimit];
            return lines.toString();
        }
        public Maze(){
            solver = new Location(0,0);
            target = new Location(10,0);
            xLimit = 10;
            yLimit = 10;
            blocks = new Vector<Location>(0);
        }
        public Maze(int xLim,int yLim){
            solver = new Location(0,0);
            target = new Location(10,0);
            xLimit = xLim;
            yLimit = yLim;
            blocks = new Vector<Location>(0);
        }
    }
    
    public static void main(String[] args){
        {
            Location loc = new Location(0,0);
            System.out.println(loc.toString());
            loc.up();
            System.out.println(loc.toString());
            
            Maze maze = new Maze();
            System.out.println(maze.finished());
            maze.solver = maze.target;
            System.out.println(maze.finished());
        }
        {
            Maze maze = new Maze();
            Vector<Move> moves = maze.getPossibleMoves(new Location(0,0),new Vector<Location>(0), false);
            System.out.println(moves.toString());
            Vector<Move> path = maze.getPath();
            System.out.println(path.toString());
        }
    }
    
    public Main(){
    }
}
