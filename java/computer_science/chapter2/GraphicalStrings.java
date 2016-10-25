/**
 * Write a description of class GraphicalStrings here.
 * 
 * @author Logan Traffas 
 * @version 9/26/2016
 * assignment: individual assignment (harder) for p2.10
 */

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JComponent;
import javax.swing.JFrame;

public class GraphicalStrings extends JComponent
{
    public static String buildSentence(){//from my phrase o matic
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
        return sentence;
    }
    public void paintComponent(Graphics g){//draw a whole bunch of random sentences in different colors
        Graphics2D g2 = (Graphics2D)g;
        int xPos = 1, yPos = 12;
        final Color[] colors = {Color.BLACK,Color.BLUE,Color.CYAN,Color.GRAY,Color.DARK_GRAY,Color.LIGHT_GRAY,Color.GREEN,Color.MAGENTA,Color.ORANGE,Color.PINK,Color.RED,Color.YELLOW};//array of all the standard colors
        final int COLORS_SIZE = 12;//size of the colors array
        for(int i=0;i<COLORS_SIZE; i++){//do all the colors
            g2.setColor(colors[i]);
            g2.drawString(buildSentence(),xPos,yPos);
            yPos+=10;
        }
    }
    public static void main(String args[]){
        JFrame frame = new JFrame();
        frame.setSize(600,200);
        frame.setTitle("Colorific Phrase 'omatic");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicalStrings component = new GraphicalStrings();
        frame.add(component);
        frame.setVisible(true);
    }
}