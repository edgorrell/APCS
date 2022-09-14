import java.util.Scanner;

public class Minesweeper extends Game{
    //null = unknown
    //true = clear
    //false = mine
    Boolean[] board = new Boolean[64];
    int[] mines, flags;
    int row, col, num;
    
    public void run(Scanner scan){
        for(int i = 0; i < 10; i++){
            num = (int)(64*Math.random());
            while(!board[num]){
                num = (int)(64*Math.random());
            }
            board[num] = false;
        }
        
    }
    private void print(Boolean[] board){
        for(int i = 0; i < 8; i++){
            for(int j = 0; i < 8; i++){
                
            }
        }
    }
}