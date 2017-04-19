package ComputerScience.Labs.Quiz;

import java.util.Scanner;

/**
 * @Author Logan Traffas
 * @Date 3/16/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Quiz Maker
 */
public class QuizDemo {
	public static void demo(Question question){
		Scanner in = new Scanner(System.in);
		question.display();
		boolean correct = question.checkAnswer(in.nextLine().trim());
		System.out.println(correct ? "Correct!" : "You're wrong!");
	}

	public static void main(String[] args){
		{
			Question question = new Question();
			question.setText("Are you human? ");
			question.setAnswer("Yes");
			demo(question);
		}
		{
			ChoiceQuestion choiceQuestion = new ChoiceQuestion();
			choiceQuestion.setText("What is the derivative of sin(x)?\n");
			choiceQuestion.setAnswer("");
			choiceQuestion.addChoice("cos(x)", true);
			choiceQuestion.addChoice("-cos(x)", false);
			choiceQuestion.addChoice("sin(x)tan(x)", false);
			choiceQuestion.addChoice("sin(2x)", false);
			demo(choiceQuestion);
		}
		{
			FillInQuestion fillInQuestion = new FillInQuestion();
			fillInQuestion.setText("Adrian is the ||.\n");
			fillInQuestion.setAnswer("best");
			demo(fillInQuestion);
		}
		{
			TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion();
			trueFalseQuestion.setText("Mr. Hyman is the Queen of England. ");
			trueFalseQuestion.setAnswer("False");
			demo(trueFalseQuestion);
		}
		{
			FreeResponseQuestion freeResponseQuestion = new FreeResponseQuestion();
			freeResponseQuestion.setText("Do you like pi? Why?");
			freeResponseQuestion.setAnswer("math number friend love circle diameter tau like");
			demo(freeResponseQuestion);
		}
		{
			NumericQuestion numericQuestion = new NumericQuestion();
			numericQuestion.setText("5 + 4 = ");
			numericQuestion.setAnswer("9");
			demo(numericQuestion);
		}
	}
}
