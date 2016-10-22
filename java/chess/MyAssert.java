public class MyAssert{
	public static void myAssert(boolean a){
		if(a) return;
		System.err.println("Asserting via MyAssert.");
		System.exit(1);
	}
}