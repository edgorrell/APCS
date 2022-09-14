import java.util.Scanner;

public class Minesweeper implements Game{
    //null: unknown
    //true: clear
    //false: flag
    Boolean[] board = new Boolean[64];
    int[] mines = new int[10];
    int row, col, num;
    
    public void run(Scanner scan){
        for(int i = 0; i < 64; i++){
            board[i] = null;
        }
        print(board);
    }
    private void print(Boolean[] board){
        for(int i = 0; i < 8; i++){
            System.out.print((8-i) + " ");
            for(int j = 0; j < 8; j++){
                if(board[j] == null){
                    System.out.print("# ");
                } else if(!board[j]){
                    System.out.print("! ");
                } else if(board[j]){
                    System.out.print(check(board,j) + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("  1 2 3 4 5 6 7 8");
    }
    private int check(Boolean[] board, int index){
        return 1;
    }
    private void clear(Boolean[] board){
        
    }
}