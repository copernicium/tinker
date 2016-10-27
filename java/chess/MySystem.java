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
}