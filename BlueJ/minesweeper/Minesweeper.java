import java.util.Scanner;
import java.util.Arrays;

public class Minesweeper implements Game{
    //null: unknown
    //true: clear
    //false: flags
    Boolean[] board = new Boolean[64];
    int[] mines = new int[10];
    
    Boolean next = false;
    int row = 0, col = 0, num;
    boolean win = false;
    
    public void run(Scanner scan){
        // init board & mines
        for(int i = 0; i < 64; i++){
            board[i] = null;
        }
        for(int i = 0; i < 10; i++){
            next = false;
            num = (int)(63*Math.random());
            while(!next){
                if(getArrayIndex(mines, num) == -1){
                    next = true;
                }
                num = (int)(63*Math.random());
            }
            mines[i] = num;
        }
        
        while(!win){;
            next = false;
            while(!next){
                Main.clear();
                print(board);
                System.out.println("\nChoose an option:");
                System.out.println("  Dig a square: 1");
                System.out.println("  Place a flag: 2");
                System.out.print("> ");
                num = scan.nextInt();
                if(num == 1 || num == 2){
                    next = true;
                }
            }
            System.out.println("noice");
            win = true;
        }
    }
    
    private int check(Boolean[] board, int index){
        // 1 2 3
        // 4 x 5
        // 6 7 8
        int[] spaces = {1,2,3,4,5,6,7,8};
        int[] near = {-9,-8,-7,-1,1,7,8,9};
        int num = 0;
        switch(index % 8){
            case 0:
                spaces[0] = 0;
                spaces[3] = 0;
                spaces[5] = 0;
                break;
            case 7:
                spaces[2] = 0;
                spaces[4] = 0;
                spaces[7] = 0;
                break;
            default:
                break;
        }
        if(index < 8){
            spaces[0] = 0;
            spaces[1] = 0;
            spaces[2] = 0;
        } else if(index > 55){
            spaces[5] = 0;
            spaces[6] = 0;
            spaces[7] = 0;
        }
        for(int i = 0; i < 8; i++){
            if(spaces[i] != 0){
                if(getArrayIndex(mines,(index +  near[i])) != -1){
                    num++;
                }
            }
        }
        return num;
    }
    
    private void clear(Boolean[] board, int index){
        int[] spaces = {1,2,3,4,5,6,7,8};
        int[] near = {-9,-8,-7,-1,1,7,8,9};
        int num = 0;
        switch(index % 8){
            case 0:
                spaces[0] = 0;
                spaces[3] = 0;
                spaces[5] = 0;
                break;
            case 7:
                spaces[2] = 0;
                spaces[4] = 0;
                spaces[7] = 0;
                break;
            default:
                break;
        }
        if(index < 8){
            spaces[0] = 0;
            spaces[1] = 0;
            spaces[2] = 0;
        } else if(index > 55){
            spaces[5] = 0;
            spaces[6] = 0;
            spaces[7] = 0;
        }
        
        if(check(board, index) == 0){
            for(int i = 0; i < 10; i++){
                if(spaces[i] != 0){
                    clear(board, index + near[i]);
                }
            }
            board[index] = true;
        } else if(getArrayIndex(mines, index) == -1){
            board[index] = true;
        }
    }
    
    private void print(Boolean[] board){
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(board[j] == null){
                    System.out.print(" # |");
                } else if(!board[j]){
                    System.out.print(" ! |");
                } else if(board[j]){
                    System.out.print(" " + check(board,j) + " |");
                }
            }
            System.out.println("\n     ---------------------------------");
        }
        System.out.println("\n       1   2   3   4   5   6   7   8");
    }
    
    private void print(Boolean[] board, int row, int col){
        
    }
    
    private void printMines(Boolean[] board, int[] mines){
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(getArrayIndex(mines,((8*i) + j)) != -1){
                    System.out.print(" X |");
                } else {
                    System.out.print(" " + check(board, ((8*i) + j)) + " |");
                }
            }
            System.out.println("\n     ---------------------------------");
        }
        System.out.println("\n       1   2   3   4   5   6   7   8");
    }
    
    private int getArrayIndex(int[] arr,int value) {
        for(int i=0;i<arr.length;i++){
            if(arr[i]==value){
                return i;
            }
        }
        
        return -1;
    }
}