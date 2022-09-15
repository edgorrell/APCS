import java.util.Scanner;
import java.util.Arrays;

public class Minesweeper implements Game{
    //null: unknown
    //true: clear
    //false: flags
    Boolean[] board = new Boolean[64];
    int[] mines = new int[10];
    int row = 0, col = 0, num;
    boolean win = false, next = false;
    
    public void run(Scanner scan){
        for(int i = 0; i < 64; i++){
            board[i] = null;
        }
        for(int i = 0; i < 10; i++){
            num = (int)(63*Math.random());
            while(!next){
                for(int j = 0; j < 10; j++){
                    if(num == mines[j]){
                        next = false;
                        break;
                    } else {
                        next = true;
                    }
                }
            }
        }
        print(board);
        for(int i = 0; i < 10; i++){
            System.out.println(mines[i]);
        }
        while(!win){
            
        }
    }
    
    
    private String check(Boolean[] board, int index){
        // 1 2 3
        // 4 x 5
        // 6 7 8
        int[] spaces = {1,2,3,4,5,6,7,8};
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
            switch(spaces[i]){
                case 1:
                    if(Arrays.stream(mines).anyMatch(x -> x == (index - 9))){
                        num ++;
                    }
                    break;
                case 2:
                    if(Arrays.stream(mines).anyMatch(x -> x == (index - 8))){
                        num ++;
                    }
                    break;
                case 3:
                    if(Arrays.stream(mines).anyMatch(x -> x == (index - 7))){
                        num ++;
                    }
                    break;
                case 4:
                    if(Arrays.stream(mines).anyMatch(x -> x == (index - 1))){
                        num ++;
                    }
                    break;
                case 5:
                    if(Arrays.stream(mines).anyMatch(x -> x == (index + 1))){
                        num ++;
                    }
                    break;
                case 6:
                    if(Arrays.stream(mines).anyMatch(x -> x == (index + 7))){
                        num ++;
                    }
                    break;
                case 7:
                    if(Arrays.stream(mines).anyMatch(x -> x == (index + 8))){
                        num ++;
                    }
                    break;
                case 8:
                    if(Arrays.stream(mines).anyMatch(x -> x == (index + 9))){
                        num ++;
                    }
                    break;
                case 0:
                    break;
            }
        }
        if(num != 0){
            return String.valueOf(num);
        } else {
            return " ";
        }
    }
    
    private void clear(Boolean[] board){
        int[] spaces = {1,2,3,4,5,6,7,8};
    }
    
    private void print(Boolean[] board){
        for(int i = 0; i < 8; i++){
            System.out.print(" " + (8-i) + " ");
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
        System.out.println("   1 2 3 4 5 6 7 8");
    }
    
    private int getArrayIndex(int[] arr,int value) {
        int k=0;
        
        for(int i=0;i<arr.length;i++){

            if(arr[i]==value){
                k=i;
                break;
            }
        }
        return k;
    }
}