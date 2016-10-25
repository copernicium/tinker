public class ProductOfIntegers{
    public static void main(String[] args){
        int product=1;
        for(int i=2; i<10; i++){
            product*=i;
        }
        System.out.println("The product of the first ten integers is " + product);
    }
}
