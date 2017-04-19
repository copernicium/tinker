package ComputerScience.Labs.Quiz;

import java.util.ArrayList;

/**
 * @Author Logan Traffas
 * @Date 3/16/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Quiz Maker
 */
public class FreeResponseQuestion extends Question{
	private ArrayList<String> answers;

	/**
	 Sets the answers for this question to the given keywords (separated by spaces).
	 @param correctResponse the answer
	 */
	@Override
	public void setAnswer(String correctResponse){
		String s = "";
		for(char c: correctResponse.toCharArray()){
			s += c;
			if(c == ' '){
				this.answers.add(s);
				s = "";
			}
		}
	}

	/**
	 Checks a given response for correctness.
	 @param response the response to check
	 @return true if the response was correct, false otherwise
	 */
	@Override
	public boolean checkAnswer(String response){
		ArrayList<String> responses = new ArrayList<>();
		String s = "";
		for(char c: response.toCharArray()){
			s += c;
			if(c == ' '){
				responses.add(s);
				s = "";
			}
		}
		for(String a: this.answers){
			for(String b: responses){
				if(a.equals(b)) return true;
			}
		}
		return false;
	}

	/**
	 Displays this question.
	 */
	@Override
	public void display(){
		System.out.print(text + "\n");
	}

	public FreeResponseQuestion(){
		super();
		this.answers = new ArrayList<>();
	}
}
