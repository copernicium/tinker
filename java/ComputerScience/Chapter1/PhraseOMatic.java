/**
 * @author logan traffas
 * @version 9/15/16
 * assignment: phrase 'omatic
 */
public class PhraseOMatic{
    public static void buildSentence(){//Create arrays of the necessary phrases for the sentence
       final String[] subjects = {"Clara", "Dalek", "Amelia", "stupidface", "wolf", "screwdriver", "K-9", "crack in my wall", "bow tie", "time lord", "Earth", "Rory", "neutron flow"};
       final String[] adjectives1= {"bad", "sonic", "cool", "fantastic", "beautiful","blue", "bad", "in-space", "Sontarian", "human", "clever","pink and fleshy", "thick", "Gallifreyan", "new new new new new new new new new new new new new new new"};
       final String[] adjectives2= {"Shakespearian", "timey wimey", "wibbly wobbly", "Enlgish", "dead","fast", "silurian", "gird-locked", "mad", "boring", "fatal", "victorious"};
       final String[] verbs= {"stole", "sonic-ed", "flew", "trapped", "regenerated","exploded", "exterminated", "upgraded", "fixed", "defeated", "rescued", "saved", "tinkered","reversed the polarity of"};
       final String[] objects= {"lost moon of Poosh", "TARDIS", "Rose", "Cybermen", "time","paradox", "Missy", "ATMOS System", "rebel flesh", "psychic paper", "Me", "spoilers"};
                
       //pick random words for use in the sentence
       String subject = subjects[(int) (Math.random() * subjects.length)];
       String object = objects[(int) (Math.random() * objects.length)];
       String verb = verbs[(int) (Math.random() * verbs.length)];
       String adjective1 = adjectives1[(int) (Math.random() * adjectives1.length)];
       String adjective2 = adjectives2[(int) (Math.random() * adjectives2.length)];
        
        //the following is code to add the word "the" to nouns when needed
        boolean addThe1 = false;
        {//check if the subject needs a "the"
            String[] subjectThes={"Dalek", "stupidface",
                "wolf", "screwdriver", "crack in my wall", "bow tie", "time lord",
                "Earth", "neutron flow"};
           for(int i = 0; i<subjectThes.length; i++){
               if(subject == subjectThes[i]){
                   addThe1 = true;
                   break;
                }        
            }
        }
        boolean addThe2 = false;
        {//check if the object needs a "the"
            String[] objectThes={"lost moon of Poosh", "TARDIS", "Cybermen", "time",
                "paradox", "ATMOS System", "rebel flesh", "psychic paper", "spoilers"};
           for(int i = 0; i<objectThes.length; i++){
               if(object == objectThes[i]){
                   addThe2 = true;
                   break;
                }        
            }
        }
        String the1 = addThe1 ? "The " : "";
        String the2 = addThe2 ? "the " : "";
        if(!addThe1) adjective1 = adjective1.substring(0, 1).toUpperCase() + adjective1.substring(1);//Capitalize the first letter of the subject's adjective if it is the first word
        
        //construct and build the sentence
        String sentence = the1 + adjective1 + " " + subject + " " + verb + " " + the2 + adjective2 + " " + object + ".";
        System.out.println(sentence);
    }
    
    public static void main(String[] args) {
        final int runtime = 10;
        for(int i = 0; i<runtime; i++){
            buildSentence();
        }
    }
}
