/*
 * 
 * Evan G
 * 8/23/22
 * Blueprint for comments
 *  any description
 * who helped: 
 * 
 */
import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        // primitive data types
        byte a;
        char b;
        short c;
        int x;
        long d;
        float y;
        double z;
        
        //classes to make objects
        // (Class Name) (Obj Name) = new (Constructor)
        Integer a1;
        Double b1;
        String c1;
        Boolean d1;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Type Something: ");
        String input = scan.nextLine();
        System.out.println(input);
        
        scan.close();
    }
}
