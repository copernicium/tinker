package ComputerScience.Labs.Quiz;

/**
 * @Author Logan Traffas
 * @Date 3/16/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Quiz Maker
 */
public class TrueFalseQuestion extends Question{

	/**
	 Displays this question.
	 */
	@Override
	public void display(){
		System.out.print(text + " \"True\" or \"False\": ");
	}


	public TrueFalseQuestion(){
		super();
	}
}
