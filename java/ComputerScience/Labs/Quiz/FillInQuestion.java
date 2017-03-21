package ComputerScience.Labs.Quiz;

/**
 * @Author Logan Traffas
 * @Date 3/16/2017.
 * @Version 1.0.0
 * @Assignment Ch 9 Lab: Quiz Maker
 */
public class FillInQuestion extends Question{
	/**
	 * Displays the question, auto-replacing "||" with the number of blanks of the answer
	 */
	@Override
	public void display(){
		String out = "";
		for(int i = 0; i < this.text.length(); i++){
			if(i < this.text.length() - 1 && this.text.charAt(i) == '|' && this.text.charAt(i + 1) == '|'){
				for(int j = 0; j < this.answer.length(); j++){
					out += '_';
				}
				i++;
			} else {
				out += this.text.charAt(i);
			}
		}
		System.out.print(out);
	}
	public FillInQuestion(){
		super();
	}
}
