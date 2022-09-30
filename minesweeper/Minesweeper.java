import java.util.Scanner;

public class Minesweeper implements Game{
    //null: unknown
    //true: clear
    //false: flags
    Boolean[] board = new Boolean[64];
    int[] mines = new int[10];
    int[] near;
    
    Boolean next = false;
    int row, col, num, num2;
    boolean win = false,first = true;
    
    public void run(Scanner scan){
        // init board
        for(int i = 0; i < 64; i++){
            board[i] = null;
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
                    if(num == 1 || num == 2){
                        next = true;
                    }
                }
                
                // square to preform action
                row = -1;
                col = -1;
                while(row < 0 || row > 9){
                    Main.clear();
                    print();
                    if(num == 1){
                        System.out.println("\nRow of square to dig:\n> ");
                    } else {
                        System.out.println("\nRow of square to flag:\n> ");
                    }
                    row = scan.nextInt();
                }
                while(col < 0 || col > 9){
                    Main.clear();
                    print(row, 0);
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
                    print(row ,col);
                    if(num == 1)
                    System.out.println("\nDig at (" + col + ", " + row + ")?");
                    else
                    System.out.println("Place flag at (" + col + ", " + row + ")?");
                    System.out.println("Yes: 1");
                    System.out.println("No: 2");
                    System.out.println((8*(8-row)) + (col-1));
                    num2 = scan.nextInt();
                }
                if(num2 == 1){
                    next = true;
                } else {
                    next = false;
                }
            }
            
            num2 = (8*(8-row)) + (col-1);
            // if digging
            if(num == 1){
                // init mines, makes sure mines != first choice/near
                // 0 1 2
                // 3 4 5
                // 6 7 8
                if(first){
                    near = new int[] {-9,-8,-7,-1,0,1,7,8,9};
                    switch(num2 % 8){
                        case 0:
                            near[0] = 2;
                            near[3] = 2;
                            near[6] = 2;
                            break;
                        case 7:
                            near[2] = 2;
                            near[5] = 2;
                            near[8] = 2;
                            break;
                        default:
                            break;
                    }
                    if(num2 < 8){
                        near[0] = 2;
                        near[1] = 2;
                        near[2] = 2;
                    } else if(num2 > 55){
                        near[6] = 2;
                        near[7] = 2;
                        near[8] = 2;
                    }
                    for(int i = 0; i < 9; i++){
                        if(near[i] != 2){
                            near[i] += num2;
                        } else {
                            near[i] = -1;
                        }
                    }
                    
                    num = 0;
                    for(int i = 0; i < 10; i++){
                        next = false;
                        while(!next){
                            num = (int)(63.0*Math.random());
                            if(getArrayIndex(near, mines[i]) == -1
                               && getArrayIndex(mines,num) == -1){
                                System.out.println(num);
                                scan.nextLine();
                                mines[i] = num;
                                next = true;
                            }
                        }
                    }
                    first = false;
                    scan.nextLine();
                }
                
                // lose if dig mine
                if(getArrayIndex(mines, 8*(8-row) + (col -1)) != -1){
                    Main.clear();
                    printMines();
                    System.out.println("\n You Lost!");
                    break;
                } else {
                    clear(num2);
                }
             
            } else {
                // flag
                if(board[num2] == null){
                    board[num2] = false;
                } else if(board[num2] == false){
                    board[num2] = null;
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
            }
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
        int[] near = {-9,-8,-7,-1,1,7,8,9};
        int num = 0;
        // 0 1 2
        // 3 X 4
        // 5 6 7
        // checking if corner/side (index/out of bounds error)
        switch(index % 8){
            case 0:
                near[0] = 0;
                near[3] = 0;
                near[5] = 0;
                break;
            case 7:
                near[2] = 0;
                near[4] = 0;
                near[7] = 0;
                break;
            default:
                break;
        }
        if(index < 8){
            near[0] = 0;
            near[1] = 0;
            near[2] = 0;
        } else if(index > 55){
            near[5] = 0;
            near[6] = 0;
            near[7] = 0;
        }
        
        // if clear (no mines surrounding) clear near
        if(getArrayIndex(mines, index) != -1){
            for(int i = 0; i < 8; i++){
                if(near[i] != 0 && (board[near[i] + index] == null || board[near[i] + index] == false)){
                    clear(near[i] + index);
                }
            }
        }
        board[index] = true;
    }
    
    private void print(){
        System.out.println("\n     ---------------------------------");
        for(int i = 0; i < 8; i++){
            System.out.print(" " + (8-i) + "   |");
            for(int j = 0; j < 8; j++){
                if(board[(8*i) + j] == null){
                    System.out.print(" # |");
                } else if(board[(8*i) + j] == false){
                    System.out.print(" ! |");
                } else if(board[(8*i) + j] == true){
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
                if(board[(8*i) + j] == null){
                    System.out.print(" # |");
                } else if(!board[(8*i) + j]){
                    System.out.print(" ! |");
                } else if(board[(8*i) + j]){
                    System.out.print(" " + check((8*i) + j) + " |");
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