package computer_science.Chapter3;
import java.lang.Math;
/**
 * A class to test CandyStore 
 * @author Logan Traffas
 * @version 10/7/16\
 * assignment: Chapter 3 Individual Assignment
 */
public class CandyStoreTester
{
    public static void main(String[] args){
        
        final int initialStock = 0;
        CandyStore candyStore = new CandyStore(initialStock);
        final int addedStock = 10;
        candyStore.restock(addedStock);
        System.out.println("Stock:" + candyStore.getStock() + " expected:" + (initialStock + addedStock));
        final double payment = 10.00;//dollars
        final int request = 9; //how much candy they want
        candyStore.buyCandy(payment, request);
        int expectedStock = Math.max((initialStock + addedStock - request),0);
        int amountOfCandyActuallyPurchased = initialStock + addedStock - Math.max((initialStock + addedStock - request),0);
        System.out.println("Stock:" + candyStore.getStock() + " expected:" + expectedStock);
        double priceOfCandy = .99;//dollars
        System.out.println("Price of candy:" + candyStore.getPriceOfCandy() + " Expected:" + priceOfCandy);
        double amountOfMoneyActuallySpent = amountOfCandyActuallyPurchased*candyStore.getPriceOfCandy();
        System.out.println("Revenue:" + candyStore.getRevenue() + " expected: " + amountOfMoneyActuallySpent);
        candyStore.emptyRegister();
        System.out.println("Revenue:" + candyStore.getRevenue() + " expected: 0.0");
    }
}
