package minesweeper;

import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Game game;
        
        final String[] GAMES = {
            "Minesweeper", "TicTacToe"
        };
        while(true){
            int choice = -1;
            clear();
            while(choice <= 0 || choice > GAMES.length){
                System.out.println("Select an option: ");
                for(int i = 0; i < GAMES.length; i++){
                    System.out.println((i+1) + ": " + GAMES[i]);
                }
                System.out.println("-1: Close");
                choice = scan.nextInt();
            }
            
            if(choice == -1){ System.exit(0); }
            game = null;
            switch(choice){
                case 1:
                    game = new Minesweeper();
                    break;
                case 2:
                    game = new TicTacToe();
                    break;
            }
            game.run(scan);
        }
    }
    
    public static void clear(){
        System.out.print('\u000C');   
    }
}