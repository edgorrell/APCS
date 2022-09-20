import java.util.Scanner;
import java.util.Arrays;

public class Minesweeper implements Game{
    //null: unknown
    //true: clear
    //false: flags
    Boolean[] board = new Boolean[64];
    int[] mines = new int[10];
    
    Boolean next = false;
    int row = 0, col = 0, num, num2;
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
        
        while(!win){
            next = false;
            
            while(!next){
                num = 0;
                num2 = 0;
                // determine to dig or flag
                while(!(num == 1 || num == 2)){
                    Main.clear();
                    print();
                    System.out.println("\nChoose an option:");
                    System.out.println("  Dig a square: 1");
                    System.out.println("  Place a flag: 2");
                    System.out.print("> ");
                    num = scan.nextInt();
                    if(num == 1 || num == 2)next = true;
                }
                
                // square to preform action
                Main.clear();
                print();
                if(num == 1) System.out.println("Row of square to dig:\n> ");
                else System.out.println("Row of square to flag:\n> ");
                row = scan.nextInt();
                Main.clear();
                print(row, 0);
                if(num == 1)System.out.println("Column of square to dig:\n> ");
                else System.out.println("Column of square to flag:\n> ");
                col = scan.nextInt();
                num2 = 0;
                
                // confirmation of action
                while(!(num2 == 1 || num2 == 2)){
                    Main.clear();
                    print(row ,col);
                    if(num == 1)
                    System.out.println("Dig at (" + row + ", " + col + ")?");
                    else
                    System.out.println("Place flag at (" + row + ", " + col + ")?");
                    System.out.println("Yes: 1");
                    System.out.println("No: 2");
                    num2 = scan.nextInt();
                }
                if(num2 == 1){
                    next = true;
                } else {
                    next = false;
                }
            }
            
            
            if(num == 1){
                
            } else {
                
            }
            System.out.print("how");
            scan.nextLine();
            win = true;
        }
    }
    
    private int check(int index){
        // 1 2 3
        // 4 x 5
        // 6 7 8
        int[] spaces = {1,2,3,4,5,6,7,8};
        int[] near = {-9,-8,-7,-1,1,7,8,9};
        int num = 0;
        // checking if corner/side (index/out of bounds error)
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
        
        // checks each nearby cell for mines
        for(int i = 0; i < 8; i++){
            if(spaces[i] != 0){
                if(getArrayIndex(mines,(index +  near[i])) != -1){
                    num++;
                }
            }
        }
        return num;
    }
    
    private void clear(int index){
        int[] spaces = {1,2,3,4,5,6,7,8};
        int[] near = {-9,-8,-7,-1,1,7,8,9};
        int num = 0;
        // checking if corner/side (index/out of bounds error)
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
        
        // if clear (not mines surronding) clear surrondings
        if(check(index) == 0){
            for(int i = 0; i < 10; i++){
                if(spaces[i] != 0){
                    clear(index + near[i]);
                }
            }
            board[index] = true;
        } else if(getArrayIndex(mines, index) == -1){
            // just a number, gets shown
            board[index] = true;
        }
    }
    
    private void print(){
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(board[j] == null){
                    System.out.print(" # |");
                } else if(!board[(8*i) + j]){
                    System.out.print(" ! |");
                } else if(board[(8*i) + j]){
                    System.out.print(" " + check((8*i) + j) + " |");
                }
            }
            System.out.println("\n     ---------------------------------");
        }
        System.out.println("\n       1   2   3   4   5   6   7   8");
    }
    
    private void print(int row, int col){
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            if((8 - i) == row) System.out.print(" " + (8-i) + " > |");
            else System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(board[j] == null){
                    System.out.print(" # |");
                } else if(!board[(8*i) + j]){
                    System.out.print(" ! |");
                } else if(board[(8*i) + j]){
                    System.out.print(" " + check((8*i) + j) + " |");
                }
            }
            System.out.println("\n     ---------------------------------");
        }
        System.out.print("\n       ");
        for(int i = 0; i < 8; i++){
            if(i == (col - 1)) System.out.print("^   ");
            else System.out.print("    ");
        }
        System.out.println("\n       1   2   3   4   5   6   7   8");
    }
    
    private void printMines(){
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(getArrayIndex(mines,((8*i) + j)) != -1){
                    System.out.print(" X |");
                } else {
                    System.out.print(" " + check(((8*i) + j)) + " |");
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