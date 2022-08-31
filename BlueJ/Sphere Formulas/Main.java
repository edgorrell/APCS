import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        double radius;
        
        System.out.print("Input the radius of a sphere: ");
        radius = scan.nextDouble();
        
        System.out.println("A sphere with radius " + radius + ": ");
        System.out.println("  Volume: " + ((4/3)*Math.PI*Math.pow(radius,3)));
        System.out.println("  Surface Area: " + (4 * Math.PI * Math.pow(radius,2)));
    }
}