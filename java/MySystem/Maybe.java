package MySystem;

/**
 * Stores and integer and keeps track of whether it has been set or not
 */
public class Maybe<T>{
	private T a;
	private boolean set;

	public T get(){
		if(!this.set){
			MySystem.error("Integer not set",MySystem.getFileName(),MySystem.getLineNumber());
		}
		return this.a;
	}

	public boolean hasBeenSet(){
		return this.set;
	}

	public Maybe(){
		set = false;
	}

	public void set(T a){
		this.a = a;
		this.set = true;
	}

	public Maybe(T a){
		this.a = a;
		set = true;
	}
}
