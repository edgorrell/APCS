import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Game game;
        
        final String[] GAMES = {
            "Minesweeper", "TicTacToe"
        };
        int choice = -1;
        for(int i = 0; i < GAMES.length(); i++){
            System.out.println();
        }
    }
    
    public static void clear(){
        System.out.print('\u000C');   
    }
}