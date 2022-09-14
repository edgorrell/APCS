import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        Game game = new Minesweeper();
        game.run(scan);
    }
    public static void clear(){
        
    }
}