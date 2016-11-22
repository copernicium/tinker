package chess;

import java.util.Vector;

public class MySystem{
	public static boolean myContains(Vector<ChessPosition> all, ChessPosition a){
		for(ChessPosition b: all){
			if(a.equals(b)) return true;
		}
		return false;
	}

	/**
	 * Get the line number of the line where this method is used
	 * @return the line number of where the method is called
	 */
	public static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}

	/**
	 * Get the name of the file this method is used in
	 * @return the name of the file where this method is called from
	 */
	public static String getFileName(){
		return Thread.currentThread().getStackTrace()[2].getFileName();
	}

	/**
	 * Exit the process if the a argument evaluates to flalse
	 * @param a the argument to check
	 * @param fileName the name of the file calling the assert function
	 * @param lineNumber the line number this function is called on
	 */
	public static void myAssert(boolean a,String fileName,int lineNumber){
		if(a) return;
		System.err.println(fileName + ":" + lineNumber + ": Asserting via MyAssert");
		System.exit(1);
	}
	/**
	 * Exits the process and prints a given error message
	 * @param message the message to print to the screen
	 * @param fileName the name of the file calling the assert function
	 * @param lineNumber the line number this function is called on
	 */
	public static void error(String message, String fileName,int lineNumber){
		System.err.println(fileName + ":" + lineNumber + ": " + message);
		System.exit(1);
	}
	/**
	 * Prints a given string like System.out.println along with the file name and line number it is called from
	 * @param STRING the string to print
	 * @param fileName the file it's called from
	 * @param lineNumber the line number it's called from
	 */
	public static void println(final String STRING, String fileName,int lineNumber){
		String front = "";
		int startAt = 0;
		for(int i = 0; i < STRING.length(); i++){
			if(STRING.charAt(i) == '\t') front += '\t';
			else if(STRING.charAt(i) == '\n') front += '\n';
			else{
				startAt = i;
				break;
			}
		}
		String back = STRING.substring(startAt);
		System.out.println(front + fileName + ":" + lineNumber + ":" + back);
	}
	/**
	 * Prints a given string like System.out.print along with the file name and line number it is called from
	 * @param STRING the string to print
	 * @param fileName the file it's called from
	 * @param lineNumber the line number it's called from
	 */
	public static void print(String STRING, String fileName,int lineNumber){
		String front = "";
		int startAt = 0;
		for(int i = 0; i < STRING.length(); i++){
			if(STRING.charAt(i) == '\t') front += '\t';
			else if(STRING.charAt(i) == '\n') front += '\n';
			else{
				startAt = i;
				break;
			}
		}
		String back = STRING.substring(startAt);
		System.out.print(front + fileName + ":" + lineNumber + ":" + back);
	}
	/**
	 * Exits the process
	 * @param fileName the name of the file calling the assert function
	 * @param lineNumber the line number this function is called on
	 */
	public static void nyi(String fileName,int lineNumber){
		System.err.println("NYI " + fileName + ":" + lineNumber);
		System.exit(1);
	}
}