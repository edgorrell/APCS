import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        String name;
        
        System.out.println("Enter your full name: ");
        name = scan.nextLine();
        
        System.out.println(name.substring(0,name.indexOf(" ")) + " " +  name.charAt(name.indexOf(" ") + 1) + ".");
        name = name.substring(name.indexOf(" ")+1);
        name = name.substring(name.indexOf(" ")+1);
        System.out.println(name);
    }
}