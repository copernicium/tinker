package computer_science.chapter3;
/**
 * Code to write the same message to different friends
 * 
 * @author Logan Traffas
 * @version 10/5/16
 * assignment: Lab 3.3--Postcards
 */
public class Postcard
{
    private String message;
    private String sender;
    private String recipient;
    public Postcard(String aSender, String aMessage)
    {
        
        message = aMessage;
        sender = aSender;
        recipient = "";
    }
    
    public void setRecipient(String recipient){
        this.recipient = recipient;
    }
 
    public void print(){
        System.out.print("Dear " + recipient + ",\n" + message + "\nLove, " + sender + "\n");
    }
}