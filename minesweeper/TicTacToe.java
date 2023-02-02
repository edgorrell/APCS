package minesweeper;

import java.util.Scanner;

public class TicTacToe implements Game{
    public void run(Scanner scan){
        int play;
        Boolean[] board = new Boolean[9];
        boolean win = false;
        boolean turn = true;
        boolean next = false;
    
        for(int i = 0; i < 9; i++){
            play = 0;
            next = false;
            while(!next){
                Main.clear();
                print();
                System.out.println("");
                print(board);
                System.out.println("");
                if(turn){
                System.out.print("Player 1 (X) ");
                } else{
                System.out.print("Player 2 (O) ");          
                }
                System.out.println("choose your postion to play in:");
                play = scan.nextInt();
    
                if(play > 0 && play < 10 && board[play - 1] == null){
                    next = true;
                }
            }
            play--;
            board[play] = turn;
            win = check(board);
            turn = !turn;
            if(win){
              break;
            }
        }
        
        Main.clear();
        print(board);
        System.out.println("");
        if(win){
          if(turn){
            System.out.println("Player 2 Wins!");
          } else{
            System.out.println("Player 1 Wins!");
          }
        } else {
          System.out.println("It's a tie!");
        }
        System.out.println("Press Enter to Exit:");
        scan.nextLine();
        scan.nextLine();
    }
  
    private boolean check(Boolean[] board){
        final String[] CHECK = {
          "012", "345", "678",
          "036", "147", "258",
          "048", "246"
        };
        
        for(int i = 0; i < 8; i++){
            if(board[Integer.valueOf(CHECK[i].substring(0,1))] ==
               board[Integer.valueOf(CHECK[i].substring(1,2))] &&
               board[Integer.valueOf(CHECK[i].substring(0,1))] ==
               board[Integer.valueOf(CHECK[i].substring(2,3))]){
              if(board[Integer.valueOf(CHECK[i].substring(0,1))] != null){
                return true; 
              }
            }
        }
        return false;
    }
  
    private void print(){
        System.out.println(" 1 | 2 | 3 ");
        System.out.println("-----------");
        System.out.println(" 4 | 5 | 6 ");
        System.out.println("-----------");
        System.out.println(" 7 | 8 | 9 ");
    }
  
    private void print(Boolean[] board){
        String[] signs = new String[9];
        for(int i = 0; i < 9; i++){
          if(board[i] == null){
            signs[i] = " ";
          } else if(board[i]){
            signs[i] = "X";
          }else{
            signs[i] = "O";
          }
        }
        System.out.println(" "+signs[0]+" | "+signs[1]+" | " +signs[2]);
        System.out.println("-----------");
        System.out.println(" "+signs[3]+" | "+signs[4]+" | " +signs[5]);
        System.out.println("-----------");
        System.out.println(" "+signs[6]+" | "+signs[7]+" | " +signs[8]);
    }
}