package MySystem;

/**
 * Stores and integer and keeps track of whether it has been set or not
 */
public class MaybeInteger{
	private int a;
	private boolean set;

	public int get(){
		if(!this.set){
			MySystem.error("Integer not set",MySystem.getFileName(),MySystem.getLineNumber());
		}
		return this.a;
	}

	public boolean hasBeenSet(){
		return this.set;
	}

	public MaybeInteger(){
		a = 0;
		set = false;
	}

	public void set(int a){
		this.a = a;
		this.set = true;
	}

	public MaybeInteger(int a){
		this.a = a;
		set = true;
	}
}
