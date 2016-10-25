/**
 * A class to simulate commerce at a candy store
 * 
 * @author Logan Traffas
 * @version 10/7/16
 * assignment: Chapter 3 Individual Assignment
 */
public class CandyStore
{
    private double revenue;
    private int stock;
    private static final double PRICEOFCANDY = .99;//dollars
    /**
     * Fetches the current balance in the register
     * @return The amount of money that the candy store has made
     */
    public double getRevenue(){
        return this.revenue;
    }
    /**
     * Fetches the amount of candy in the candy store
     * @return the current stock
     */
    public int getStock(){
        return this.stock;
    }
    /**
     * Fetches the price of a piece of candy
     * @return The price of one pience of candy
     */
    public double getPriceOfCandy(){
        return this.PRICEOFCANDY;
    }
    /**
     * Facilitates the purchasing of candy
     * @param payment The amount of money offered for a given quantity of candy
     * @param request The amount of candy that the customer wants
     */
    public void buyCandy(double payment, int request){
        while(payment > 0.00 && request> 0 && this.stock>0){
            request --;
            this.stock -- ;
            this.revenue += this.PRICEOFCANDY;
            payment -= this.PRICEOFCANDY;
        }
    }
    /**
     * Sets the money of the candy store to zero and returns the money made
     * @return The money made from the store
     */
    public double emptyRegister(){
        double finalRevenue = this.revenue;
        revenue = 0.0;
        return finalRevenue;
    }
    /**
     * Restocks the store with a given amount of candy
     */
    public void restock(final int addedStock){
        this.stock += addedStock;
    }
    public CandyStore(){
        revenue = 0.0;
        stock = 10;
    }
    public CandyStore(final int stock){
        revenue = 0.0;
        this.stock = stock;
    }
}
