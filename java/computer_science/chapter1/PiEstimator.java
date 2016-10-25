
/**
 * a program that estimates pi
 * 
 * @author Logan Taffas 
 * @version 9/16/16 
 * assigment: chapter 1 individual assignment
 */
public class PiEstimator
{
    public static void main(String[] args){
        double pi=1;//initial value of pi
        final int RUNTIME = 100;//how many cycles it does
        int denominator = 1;
        for(int i=1; i<=RUNTIME; i++){
            pi+=1/denominator;//denominator;
            denominator++;
            pi-=1/denominator;
            denominator++;
        }
        pi*=4;
        System.out.println(pi);
    }
}
