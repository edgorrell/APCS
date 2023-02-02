package minesweeper;

import java.util.Scanner;

public class Minesweeper implements Game{
    int[] cons = new int[2];
    
    public void Minesweeper(Scanner scan){
        int size = -1, mines = -1;
        while(size < 3){
            Main.clear();
            System.out.println("Enter board size ( > 3): ");
            size = scan.nextInt();
        }
        while(mines < 1 || mines > (int)Math.pow(size,2)-9){
            Main.clear();
            System.out.println("Enter number of mines (1 - " + ((int)Math.pow(size,2)-9) +"): ");
            mines = scan.nextInt();
        }
        
        cons[0] = size;
        cons[1] = mines;
    }
    
    public void run(Scanner scan){
        //null: unknown
        //true: clear
        //false: flags
        final int SIZE = cons[0], MINES = cons[1];
        
        Boolean[] board = new Boolean[64];
        int[] mines = new int[10];
        int[] near;
        
        Boolean next = false;
        int row, col, flags = 10, num, num2;
        boolean win = false, first = true;
    
        // init board
        for(int i = 0; i < 64; i++){
            board[i] = null;
        }

        while(!win){
            while(true){
                num = 0;
                num2 = 0;
                // determine to dig or flag
                while(!(num == 1 || num == 2)){
                    Main.clear();
                    print(board, mines, flags);
                    System.out.println("\nChoose an option:");
                    System.out.println("  Dig a square: 1");
                    System.out.println("  Place a flag: 2");
                    System.out.print("> ");
                    num = scan.nextInt();
                    if(num == 1 || num == 2){
                        next = true;
                    }
                }
                
                // square to preform action
                row = -1;
                col = -1;
                while(row <= 0 || row >= 9){
                    Main.clear();
                    print(board, mines, flags);
                    if(num == 1){
                        System.out.println("\nRow of square to dig:\n> ");
                    } else {
                        System.out.println("\nRow of square to flag:\n> ");
                    }
                    row = scan.nextInt();
                }
                while(col <= 0 || col >= 9){
                    Main.clear();
                    print(board, mines, flags, row, 0);
                    if(num == 1){
                        System.out.println("\nColumn of square to dig:\n> ");
                    }else {
                        System.out.println("\nColumn of square to flag:\n> ");
                    }
                    col = scan.nextInt();
                }
                
                num2 = 0;
                // confirmation of action
                while(!(num2 == 1 || num2 == 2)){
                    Main.clear();
                    print(board, mines, flags, row ,col);
                    if(num == 1){
                        System.out.println("\nDig at (" + col + ", " + row + ")?");
                    } else{
                        System.out.println("Place flag at (" + col + ", " + row + ")?");
                    }
                    System.out.println("Yes: 1");
                    System.out.println("No: 2");
                    num2 = scan.nextInt();
                }
                if(num2 == 1){
                    break;
                }
            }
            
            num2 = (8*(8-row)) + (col-1);
            // if digging
            if(num == 1){
                // init mines, makes sure mines != first choice/near
                // 0 1 2
                // 3 4 5
                // 6 7 8
                // stuck here?
                if(first){
                    near = nearSpaces(board, mines, num2);
                    num = 0;
                    for(int i = 0; i < 10; i++){
                        next = false;
                        num = (int)(63 * Math.random()); 
                        while(!next){
                            if(getArrayIndex(mines, num) == -1 && 
                                getArrayIndex(near, num) == -1&& num != num2){
                                next = true;
                            } else {
                                num = (int)(63 * Math.random());   
                            }
                        }
                        mines[i] = num;
                    }
                    first = false;
                }

                // lose if dig mine
                if(getArrayIndex(mines, num2) != -1){
                    Main.clear();
                    printMines(board, mines, flags);
                    System.out.println("\nYou Lost!");
                    break;
                } else if(board[num2] == null){
                    clear(board, mines, num2);
                }
             
            } else {
                // flag
                if(board[num2] == null){
                    board[num2] = false;
                    flags--;
                } else if(board[num2] == false){
                    board[num2] = null;
                    flags++;
                } else if(board[num2] == true){
                    System.out.println("You can't place a flag here!");
                    scan.nextLine();
                }
            }
            // check if won
            num2 = 0;
            for(int i = 0; i < 63; i++){
                if(board[i] == null || board[i] == false){
                    num2 ++;
                }
            }
            if(num2 == 10){
                win = true;
                Main.clear();
                print(board, mines, flags);
                System.out.println("You Win!");
            }
        }
        System.out.println("Press Enter to Exit:");
        scan.nextLine();
        scan.nextLine();
    }
    
    private int check(Boolean[] board, int[] mines, int index){
        // 1 2 3
        // 4 x 5
        // 6 7 8
        int[] near = nearSpaces(board, mines, index);
        int num = 0;
        
        // checks each nearby cell for mines
        for(int i = 0; i < 8; i++){
            if(near[i] != -1){
                if(getArrayIndex(mines,near[i]) != -1){
                    num++;
                }
            }
        }
        return num;
    }
    
    private void clear(Boolean[] board, int[] mines, int index){
        int[] near = nearSpaces(board, mines, index);
        
        board[index] = true;
        if(check(board, mines, index) == 0){
            for(int i = 0; i < 8; i++){
                if(near[i] != -1 && board[near[i]] == null){
                    clear(board, mines, near[i]);
                }
            }
        }
    }
    
    private int[] nearSpaces(Boolean[] board, int[] mines, int index){
        // 0 1 2
        // 3 X 4
        // 5 6 7
        // checking if corner/side (index/out of bounds error)
        int[] near = {-9,-8,-7,-1,1,7,8,9};
        switch(index % 8){
            case 0:
                near[0] = -2;
                near[3] = -2;
                near[5] = -2;
                break;
            case 7:
                near[2] = -2;
                near[4] = -2;
                near[7] = -2;
                break;
            default:
                break;
        }
        if(index < 8){
            near[0] = -2;
            near[1] = -2;
            near[2] = -2;
        } else if(index > 55){
            near[5] = -2;
            near[6] = -2;
            near[7] = -2;
        }
        for(int i = 0; i < 8; i++){
            if(near[i] != -2){
                near[i] += index;
            } else {
                near[i] = -1;
            }
        }
        return near;
    }
    
    private void print(Boolean[] board, int[] mines, int flags){
        System.out.println("Flags: " + flags);
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(board[(8*i) + j] == null){
                    System.out.print(" # |");
                } else if(board[(8*i) + j] == false){
                    System.out.print(" ! |");
                } else if(board[(8*i) + j] == true){
                    if(check(board, mines, ((8*i) + j)) == 0){
                        System.out.print("   |");
                    } else {    
                        System.out.print(" " + check(board, mines, ((8*i) + j)) + " |");
                    }
                }
            }
            System.out.println("\n     ---------------------------------");
        }
        System.out.println("\n       1   2   3   4   5   6   7   8");
    }
    
    private void print(Boolean[] board, int[] mines, int flags, int row, int col){
        System.out.println("Flags: " + flags);
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            if((8 - i) == row) System.out.print(" " + (8-i) + " > |");
            else System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(board[(8*i) + j] == null){
                    System.out.print(" # |");
                } else if(!board[(8*i) + j]){
                    System.out.print(" ! |");
                } else if(board[(8*i) + j]){
                    if(check(board, mines, ((8*i) + j)) == 0){
                        System.out.print("   |");
                    } else {    
                        System.out.print(" " + check(board, mines, ((8*i) + j)) + " |");
                    }
                }
            }
            System.out.println("\n     ---------------------------------");
        }
        System.out.print("       ");
        for(int i = 0; i < 8; i++){
            if(i == (col - 1)) System.out.print("^   ");
            else System.out.print("    ");
        }
        System.out.println("\n       1   2   3   4   5   6   7   8");
    }
    
    private void printMines(Boolean[] board, int[] mines, int flags){
        System.out.println("Flags: " + flags);
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(getArrayIndex(mines,((8*i) + j)) != -1){
                    System.out.print(" X |");
                } else {
                    System.out.print(" " + check(board, mines, ((8*i) + j)) + " |");
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
