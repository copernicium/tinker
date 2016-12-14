package computer_science.Chapter1;
public class BoxedName{
    public static void addLine(int length){
        System.out.print("+");
        for(int i=0; i< length+2; i++){
            System.out.print("-");
        }
        System.out.println("+");
    }
    
    public static void main(String[] args){
        String name = "Logan Traffas";
        addLine(name.length());
        System.out.println("| " + name + " |");
        addLine(name.length());
    }
}
