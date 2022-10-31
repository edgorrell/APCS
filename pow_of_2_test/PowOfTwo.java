import java.util.Scanner;

public class PowOfTwo{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int size, max;
        char out;
        
        System.out.println("Enter size: ");
        size = scan.nextInt();
        System.out.println("Enter max power: ");
        max = scan.nextInt();
        
        System.out.print("\u000c");
        for(int i = 1; i <= size; i++){
            out = 'A';
            for(int j = 0; j < (size + "").length() - (i + "").length(); j++){
                System.out.print(" ");
            }
            System.out.print(i + ": ");
            for(int j = 1; j < size; j++){
                if(i % (int)Math.pow(2,j) == 0){
                    System.out.print("-");
                    out++;
                } else {
                    break;
                }
            }
            System.out.println(out);
        }
    }
}