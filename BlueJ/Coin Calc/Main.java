import java.util.Scanner;
import java.text.NumberFormat;

class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        
        double total = 0;
        
        System.out.print("Number of pennies: ");
        total += (0.01 * scan.nextInt());
        System.out.print("Number of nickels: ");
        total += (0.05 * scan.nextInt());
        System.out.print("Number of dimes: ");
        total += (0.1 * scan.nextInt());
        System.out.print("Number of quarters: ");
        total += (0.25 * scan.nextInt());
        
        System.out.println("Your total is: " + fmt.format(total));
    }
}