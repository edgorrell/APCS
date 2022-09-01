/*

*/

import java.util.Scanner;
import java.text.NumberFormat;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        
        double bill;
        int numPeople;
        
        System.out.println("What is the subtotal for the bill?");
        bill = 1.2 * scan.nextDouble();
        System.out.println("How many people are paying?");
        numPeople = scan.nextInt();
        
        //
        System.out.println("Subtotal: " + fmt.format(bill * ((double) 5/6)));
        System.out.println("Tip: " + fmt.format((bill/6)));
        System.out.println("Total: " + fmt.format(bill));
        
        System.out.println("Cost per Person: " + fmt.format(bill/numPeople));
        System.out.println("\t Subtotal: " + fmt.format((bill * ((double) 5/6))/numPeople));
        System.out.println("\t Tip: " + fmt.format(((bill/6)/numPeople)));
    }
}