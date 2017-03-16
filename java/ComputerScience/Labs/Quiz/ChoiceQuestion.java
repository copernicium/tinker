package ComputerScience.Labs.Quiz;

import java.util.ArrayList;

/**
 * @Author Logan Traffas
 * @Date 3/16/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Quiz Maker
 */
public class ChoiceQuestion extends Question{
	private ArrayList<String> choices;

	public void addChoice(String choice, boolean correct){
		this.choices.add(choice);
		if(correct){
			setAnswer("" + this.choices.size());
		}
	}

	@Override
	public void display(){
		super.display();
		for(int i = 0; i < this.choices.size(); i++){
			System.out.println(i + 1 + " " + this.choices.get(i));
		}
	}

	public ChoiceQuestion(){
		super();
		this.choices = new ArrayList<>();
	}
}
