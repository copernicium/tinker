package chess;
public class MySystem{
	public static int getLineNumber() {
		return Thread.currentThread().getStackTrace()[2].getLineNumber();
	}
	public static String getFileName(){
		return Thread.currentThread().getStackTrace()[2].getFileName();
	}
	public static void myAssert(boolean a,String fileName,int lineNumber){
		if(a) return;
		System.err.println(fileName + ":" + lineNumber + ": Asserting via MyAssert");
		System.exit(1);
	}
	public static void error(String message, String fileName,int lineNumber){
		System.err.println(fileName + ":" + lineNumber + ": " + message);
		System.exit(1);
	}
	public static void nyi(String fileName,int lineNumber){
		System.err.println("NYI " + fileName + ":" + lineNumber);
		System.exit(1);
	}
}