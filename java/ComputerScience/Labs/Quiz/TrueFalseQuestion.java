package ComputerScience.Labs.Quiz;

/**
 * @Author Logan Traffas
 * @Date 3/16/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Quiz Maker
 */
public class TrueFalseQuestion extends Question{
	private String text;
	private boolean answer;

	public void setAnswer(boolean answer){
		this.answer = answer;
	}


	public boolean checkAnswer(boolean response){
		return this.answer == response;
	}

	public TrueFalseQuestion(){
		super();
	}
}
