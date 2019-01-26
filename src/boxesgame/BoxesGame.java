/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxesgame;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ibrahim
 */
public class BoxesGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Scanner scanner = new Scanner(System.in);
            AIPlayer aiplayer = new AIPlayer();
            System.out.println("Please Enter height: ");
            int height = scanner.nextInt();
            System.out.println("Please Enter width: ");
            int width = scanner.nextInt();
            Board board = new Board(height, width);
            System.out.println("Please Enter Player Character: ");
            char player = scanner.next().charAt(0);
            board.setPlayer(player);
            String move;
            board.printBoard();
            while (!board.isFinish()) {
//            board.printBoard();
                if (board.getTurn() == 'I') {
                    System.out.println("Turn: " + board.getTurn());
                    aiplayer.play(board, 1);
                    board.getAvailableMoves().forEach((state) -> {
                        System.out.print(state + " ");
                    });
                    System.out.println();
                    board.printBoard();
                } else {
                    System.out.println("Turn: " + board.getTurn());
                    System.out.println("Please Enter your move: ");
                    move = scanner.next();

                    board.addWall(Integer.parseInt(move.substring(0, 1)), Integer.parseInt(move.substring(1, 2)), move.charAt(2));
                    board.printBoard();
                }
            }
            System.out.println("Your score: " + board.playerResult + " and computer score: " + board.aiResult);
//    	board.addWall(0, 0, 'b');
//    	board.addWall(1, 1, 't');
//    	board.addWall(1, 1, 'b');
//    	board.addWall(1, 1, 'l');
//    	board.addWall(1, 1, 'r');
//    	board.getBoard()[1][1].setOwner('A');
//    	board.printBoard();

//            Board bord = new Board(2, 2);
//            bord.setPlayer('P');
//            AIPlayer aiplayer = new AIPlayer();
//            bord.addWall(1, 1, 't');
//            bord.printBoard();
//            aiplayer.play(bord, 1);
//            bord.printBoard();
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(BoxesGame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
