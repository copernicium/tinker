package ComputerScience.labs.magpie;
/**
 * A program to carry on conversations with a human user.
 * This is the initial version that:
 * <ul><li>
 *       Uses indexOf to find strings
 * </li><li>
 * 		    Handles responding to simple words and phrases
 * </li></ul>
 * This version uses a nested if to handle default responses.
 * @author Laurie White
 * @version April 2012
 */
public class Magpie2
{
	/**
	 * Get a default greeting
	 * @return a greeting
	 */
	public String getGreeting()
	{
		return "Hello, let's talk.";
	}

	/**
	 * Gives a response to a user statement
	 *
	 * @param statement the user statement
	 * @return a response based on the rules given
	 */
	public String getResponse(String statement)
	{
		if(statement.length() == 0){
			return "Say something, please. I'm lonely.";
		} else if(statement.contains("no") ){
			return "Why so negative?";
		} else if(statement.contains("mother") || statement.contains("father") || statement.contains("sister") || statement.contains("brother")){
			return "Tell me more about your family.";
		} else if(statement.contains("dog") || statement.contains("cat")){
			return  "Tell me more about your pets.";
		} else if(statement.contains("Ms.") || statement.contains("Mrs.")){
			return "She sounds like a good teacher.";
		} else if(statement.contains("Mr.")) {
			return "He sounds like a good teacher.";
		} else if(statement.contains("election")){
			return "The election? Do you mean that dumpster fire in 2016?";
		} else if(statement.contains("")){

		} else if(statement.contains("")){

		}
		return getRandomResponse();
	}

	/**
	 * Pick a random non-committal response to use if the user's input doesn't contain any of the keywords.
	 * @return a non-committal response
	 */
	private String getRandomResponse()
	{

		final String[] RANDOM_RESPONSES = {"Interesting, tell me more.","Hmmm.","Do you really think so?","You don't say.","If you say so.","Okay."};
		int random = (int)(Math.random() * RANDOM_RESPONSES.length);
		return RANDOM_RESPONSES[random];

		/*final int NUMBER_OF_RESPONSES = 4;
		int whichResponse = (int)(Math.random() * NUMBER_OF_RESPONSES);

		switch(whichResponse){
			case 0:
				return "Interesting, tell me more.";
			case 1:
				return "Hmmm.";
			case 2:
				return "Do you really think so?";
			case 3:
				return "You don't say.";
			default:
				return "";
		}*/
	}
}
