package ComputerScience.Chapter1;
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
        double pi=0;//initial value of pi
        final double RUNTIME = 1000000000.0;//how many cycles it does
        int denominator = 1;
        for(double i=0.0; i<=RUNTIME; i+=1.0){//use an infinite series (or at least the first part) to find pi/4
            pi+=(1.0/(double)denominator);
            denominator+=2;
            pi-=(1.0/(double)denominator);
            denominator+=2;
        }
        pi*=4.000;
        System.out.println(pi);
    }
}