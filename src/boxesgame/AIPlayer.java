/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package boxesgame;

/**
 *
 * @author ibrahim
 */
public class AIPlayer {
    
    String move = "";

    public void play(Board bord, int depth) throws CloneNotSupportedException {

        String bestChoice = "";
        Board bord1 = bord.clone();
        int score = this.minmax(bord1, depth);
//        System.out.println("scor: " + score);
//        System.out.println("bestChoice: " + bestChoice);
        System.out.println("the move: " + this.move);
        bord.addWall(Integer.parseInt(this.move.substring(0, 1)), Integer.parseInt(this.move.substring(1, 2)), this.move.charAt(2));
    }

    /*
     * 
     function minimax(node, depth, maximizingPlayer) is
    if depth = 0 or node is a terminal node then
        return the heuristic value of node
    if maximizingPlayer then
        value := −∞
        for each child of node do
            value := max(value, minimax(child, depth − 1, FALSE))
        return value
    else (* minimizing player *)
        value := +∞
        for each child of node do
            value := min(value, minimax(child, depth − 1, TRUE))
        return value
     */
    private int minmax(Board bord, int depth) throws CloneNotSupportedException {
        int bestScore = bord.aiResult;
        int badScore = bord.playerResult;
        int nextValue;
        if (depth <= 0 || bord.isFinish()) {
            return bestScore;
        }
        if (bord.getTurn() == 'I') {
//            bestScore = -1;
            for (String mov : bord.getAvailableMoves()) {
                bord.addWall(Integer.parseInt(mov.substring(0, 1)), Integer.parseInt(mov.substring(1, 2)), mov.charAt(2));
                nextValue = minmax(bord, depth - 1);
//                System.out.println("depth is: " + depth + " nextValue: " + nextValue);
                if (bestScore <= nextValue) {
                    bestScore = nextValue;
                    this.move = mov;
//                    System.out.println("best move: " + move);
                }
                bord.removeWall(Integer.parseInt(mov.substring(0, 1)), Integer.parseInt(mov.substring(1, 2)), mov.charAt(2));
            }
        } else {
            for (String mov : bord.getAvailableMoves()) {
                bord.addWall(Integer.parseInt(mov.substring(0, 1)), Integer.parseInt(mov.substring(1, 2)), mov.charAt(2));
                nextValue = minmaxmin(bord, depth - 1);
//                System.out.println("depth is: " + depth + " nextValue: " + nextValue);
                if (badScore >= nextValue) {
                    badScore = nextValue;
                    this.move = mov;
//                    System.out.println("best move: " + move);
                }
                bord.removeWall(Integer.parseInt(mov.substring(0, 1)), Integer.parseInt(mov.substring(1, 2)), mov.charAt(2));
            }
        }
        return bestScore;
    }

    private int minmaxmin(Board bord, int depth) throws CloneNotSupportedException {
        int badScore = bord.playerResult;
        int bestScore = bord.aiResult;
        int nextValue;
        if (bord.isFinish() || depth == 0) {
            return badScore;
        }
        if (bord.getTurn() == 'I') {
//            bestScore = -1;
            for (String mov : bord.getAvailableMoves()) {
                bord.addWall(Integer.parseInt(mov.substring(0, 1)), Integer.parseInt(mov.substring(1, 2)), mov.charAt(2));
                nextValue = minmax(bord, depth - 1);
//                System.out.println("depth is: " + depth + " nextValue: " + nextValue);
                if (bestScore <= nextValue) {
                    bestScore = nextValue;
                    this.move = mov;
//                    System.out.println("best move: " + move);
                }
                bord.removeWall(Integer.parseInt(mov.substring(0, 1)), Integer.parseInt(mov.substring(1, 2)), mov.charAt(2));
            }
        } else {
            for (String mov : bord.getAvailableMoves()) {
                bord.addWall(Integer.parseInt(mov.substring(0, 1)), Integer.parseInt(mov.substring(1, 2)), mov.charAt(2));
                nextValue = minmaxmin(bord, depth - 1);
//                System.out.println("depth is: " + depth + " nextValue: " + nextValue);
                if (badScore >= nextValue) {
                    badScore = nextValue;
                    this.move = mov;
//                    System.out.println("best move: " + move);
                }
                bord.removeWall(Integer.parseInt(mov.substring(0, 1)), Integer.parseInt(mov.substring(1, 2)), mov.charAt(2));
            }
        }
        return badScore;
    }

}
