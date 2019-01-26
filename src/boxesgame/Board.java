package boxesgame;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Board {

    int aiResult = 0;

    int playerResult = 0;

    private char player;

    private char turn = 'I';

    private Box[][] board;

    private List<String> availableMoves;

    public List<String> getAvailableMoves() {
        return availableMoves;
    }
    
    @Override
    public Board clone() throws CloneNotSupportedException {
//        super.clone();
        Board newBoard = new Board(this.board.length, this.board[0].length);
        for(int i = 0; i < this.board.length; i++) {
            for(int j = 0; j < this.board[i].length; j++) {
                newBoard.board[i][j] = this.board[i][j].clone();
            }
        }
        newBoard.aiResult = this.aiResult;
        newBoard.playerResult = this.playerResult;
        newBoard.player = this.player;
        newBoard.availableMoves.removeAll(newBoard.availableMoves);
        newBoard.availableMoves.addAll(this.availableMoves);
        newBoard.turn = this.turn;
        return newBoard;
    }

    public int getAiResult() {
        return aiResult;
    }

    public void setAiResult(int aiResult) {
        this.aiResult = aiResult;
    }

    public int getPlayerResult() {
        return playerResult;
    }

    public void setPlayerResult(int playerResult) {
        this.playerResult = playerResult;
    }

    public char getTurn() {
        return turn;
    }

    public void setTurn(char turn) {
        this.turn = turn;
    }

    public char getPlayer() {
        return player;
    }

    public void setPlayer(char player) {
        this.player = player;
    }

    public Box[][] getBoard() {
        return board;
    }

    public void setBoard(Box[][] board) {
        this.board = board;
    }

    public Board() {
        this.availableMoves = new CopyOnWriteArrayList<>();

    }

    public Board(int h, int w) {
        this.availableMoves = new CopyOnWriteArrayList<>();
        this.board = new Box[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                this.board[i][j] = new Box(false, false, false, false);
                this.availableMoves.add(""+i + j + "t");
                this.availableMoves.add(""+i + j + "b");
                this.availableMoves.add(""+i + j + "l");
                this.availableMoves.add(""+i + j + "r");
            }
        }
    }

    public void addWall(int h, int w, char coordinate) {
        if (h > this.board.length || h < 0 || w > this.board[0].length || w < 0) {
            return;
        }
        switch (coordinate) {
            case 't':
                this.board[h][w].setTop(true);
                this.availableMoves.remove(""+h + w + "t");
                if (this.isClosed(this.board[h][w])) {
                    this.board[h][w].setOwner(this.turn);
                    if (this.turn == 'I') {
                        this.aiResult++;
                    } else {
                        this.playerResult++;
                    }
                }
                if (h > 0) {
                    this.board[h - 1][w].setBottom(true);
                    this.availableMoves.remove(""+(h - 1) + w + "b");
                    if (this.isClosed(this.board[h - 1][w])) {
                        this.board[h - 1][w].setOwner(this.turn);
                        if (this.turn == 'I') {
                            this.aiResult++;
                        } else {
                            this.playerResult++;
                        }
                    }
                }
                if(!this.isClosed(this.board[h][w]) && !(h>0 && this.isClosed(this.board[h - 1][w]))) {
                    this.turn();
                }
                break;
            case 'b':
                this.board[h][w].setBottom(true);
                this.availableMoves.remove(""+h + w + "b");
                if (this.isClosed(this.board[h][w])) {
                    this.board[h][w].setOwner(this.turn);
                    if (this.turn == 'I') {
                        this.aiResult++;
                    } else {
                        this.playerResult++;
                    }
                } 
                if (h < this.board.length - 1) {
                    this.board[h + 1][w].setTop(true);
                    this.availableMoves.remove(""+(h + 1) + w + "t");
                    if (this.isClosed(this.board[h + 1][w])) {
                        this.board[h + 1][w].setOwner(this.turn);
                        if (this.turn == 'I') {
                            this.aiResult++;
                        } else {
                            this.playerResult++;
                        }
                    }
                }
                if(!this.isClosed(this.board[h][w]) && !((h< this.board.length - 1) && this.isClosed(this.board[h + 1][w]))) {
                    this.turn();
                }
                break;
            // still cases l and r
            case 'l':
                this.board[h][w].setLeft(true);
                this.availableMoves.remove(""+h + w + "l");
//                System.out.print("" +w + h + "l");
                if (this.isClosed(this.board[h][w])) {
                    this.board[h][w].setOwner(this.turn);
                    if (this.turn == 'I') {
                        this.aiResult++;
                    } else {
                        this.playerResult++;
                    }
                } 
                if (w > 0) {
                    this.board[h][w - 1].setRight(true);
                    this.availableMoves.remove(""+h + (w - 1) + "r");
//                    System.out.print("" +w + (h - 1) + "l");
                    if (this.isClosed(this.board[h][w - 1])) {
                        this.board[h][w-1].setOwner(this.turn);
                        if (this.turn == 'I') {
                            this.aiResult++;
                        } else {
                            this.playerResult++;
                        }
                    }
                }
                if(!this.isClosed(this.board[h][w]) && !(w>0 && this.isClosed(this.board[h][w-1]))) {
                    this.turn();
                }
                break;
            case 'r':
                this.board[h][w].setRight(true);
                this.availableMoves.remove(""+h + w + "r");
//                System.out.println("" +w + h + "l");
                if (this.isClosed(this.board[h][w])) {
                    this.board[h][w].setOwner(this.turn);
                    if (this.turn == 'I') {
                        this.aiResult++;
                    } else {
                        this.playerResult++;
                    }
                }
                if (w < this.board[0].length - 1) {
                    this.board[h][w + 1].setLeft(true);
                    this.availableMoves.remove("" +h + (w + 1) + "l");
//                    System.out.println("" +w + (h + 1) + "l");
                    if (this.isClosed(this.board[h][w + 1])) {
                        this.board[h][w + 1].setOwner(this.turn);
                        if (this.turn == 'I') {
                            this.aiResult++;
                        } else {
                            this.playerResult++;
                        }
                    }
                }
                if(!this.isClosed(this.board[h][w]) && !((w< this.board[0].length - 1) && this.isClosed(this.board[h][w+1]))) {
                    this.turn();
                }
                break;
            default:
                // do nothing
                System.out.println("invalid value!");
        }
    }

    public void removeWall(int h, int w, char coordinate) {
        if (h > this.board.length || h < 0 || w > this.board[0].length || w < 0) {
            return;
        }
        switch (coordinate) {
            case 't':
                if (this.isClosed(this.board[h][w])) {
                    if (this.turn == 'I') {
                        this.aiResult--;
                    } else {
                        this.playerResult--;
                    }
//                    this.turn();
                } else {
//                    this.turn();
                }
                this.board[h][w].setTop(false);
                this.availableMoves.add(""+h + w + "t");
                this.board[h][w].setOwner(' ');

                if (h > 0) {
                    if (this.isClosed(this.board[h - 1][w])) {
                        if (this.turn == 'I') {
                            this.aiResult--;
                        } else {
                            this.playerResult--;
                        }
//                        this.turn();
                    } else {
//                    this.turn();
                    }
                    this.board[h - 1][w].setBottom(false);
//                    this.availableMoves.add(""+(w - 1) + h + "b");
                    this.board[h - 1][w].setOwner(' ');
                }
                break;
            case 'b':
                if (this.isClosed(this.board[h][w])) {
                        if (this.turn == 'I') {
                            this.aiResult--;
                        } else {
                            this.playerResult--;
                        }
//                        this.turn();
                    } else {
//                    this.turn();
                }
                this.board[h][w].setBottom(false);
                this.availableMoves.add(""+h + w + "b");
                this.board[h][w].setOwner(' ');
                if (h < this.board.length - 1) {
                    if (this.isClosed(this.board[h + 1][w])) {
                        if (this.turn == 'I') {
                            this.aiResult--;
                        } else {
                            this.playerResult--;
                        }
//                        this.turn();
                    } else {
//                        this.turn();
                    }
                    this.board[h + 1][w].setTop(false);
//                    this.availableMoves.add(""+(w + 1) + h + "t");
                    this.board[h + 1][w].setOwner(' ');
                }
                break;
            // still cases l and r
            case 'l':
                if (this.isClosed(this.board[h][w])) {
                        if (this.turn == 'I') {
                            this.aiResult--;
                        } else {
                            this.playerResult--;
                        }
//                        this.turn();
                    } else {
//                    this.turn();
                }
                this.board[h][w].setLeft(false);
                this.availableMoves.add(""+h + w + "l");
                this.board[h][w].setOwner(' ');
                if (w > 0) {
                    if (this.isClosed(this.board[h][w-1])) {
                        if (this.turn == 'I') {
                            this.aiResult--;
                        } else {
                            this.playerResult--;
                        }
//                        this.turn();
                    } else {
//                        this.turn();
                    }
                    this.board[h][w - 1].setRight(false);
//                    this.availableMoves.add(""+w + (h - 1) + "r");
                    this.board[h][w-1].setOwner(' ');
                }
                break;
            case 'r':
                if (this.isClosed(this.board[h][w])) {
                        if (this.turn == 'I') {
                            this.aiResult--;
                        } else {
                            this.playerResult--;
                        }
//                        this.turn();
                    } else {
//                    this.turn();
                }
                this.board[h][w].setRight(false);
                this.availableMoves.add(""+h + w + "r");
                this.board[h][w].setOwner(' ');
                if (w < this.board[0].length - 1) {
                    if (this.isClosed(this.board[h][w+1])) {
                        if (this.turn == 'I') {
                            this.aiResult--;
                        } else {
                            this.playerResult--;
                        }
//                        this.turn();
                    } else {
//                        this.turn();
                    }
                    this.board[h][w + 1].setLeft(false);
//                    this.availableMoves.add(""+w + (h + 1) + "l");
                    this.board[h][w + 1].setOwner(' ');
                }
                break;
            default:
                // do nothing
                System.out.println("invalid value!");
        }
    }

    private boolean isClosed(Box box) {
        return box.isTop() && box.isBottom() && box.isRight() && box.isLeft();
    }

    public boolean isFinish() {
        for (Box[] boxs : board) {
            for (Box box : boxs) {
                if (box.getOwner() == ' ') {
                    return false;
                }
            }
        }

        return true;
    }

    private void turn() {
        if (this.turn == 'I') {
            this.turn = this.player;
        } else {
            this.turn = 'I';
        }
    }

    public void printBoard() {
        int count = 0;
        for (int i = 0; i < this.board[0].length; i++) {
            System.out.print("  " + i + " ");
        }
        System.out.println();
        for (int i = 0; i <= this.board[0].length; i++) {
            System.out.print("====");
        }
        System.out.println();
        for (Box[] boxes : this.board) {
            for (Box box : boxes) {
                System.out.print("o");
                if (box.isTop()) {
                    System.out.print("---");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.print("o");
            System.out.println();

            for (Box box : boxes) {
                if (box.isLeft()) {
                    System.out.print("| ");
                } else {
                    System.out.print("  ");
                }
                if (this.isClosed(box)) {
                    System.out.print(box.getOwner() + " ");
                } else {
                    System.out.print("  ");
                }
                if (box == boxes[boxes.length - 1]) {
                    if (box.isRight()) {
                        System.out.print("|");
                    } else {
                        System.out.print(" ");
                    }
                    System.out.print("       = " + count);
                    count++;
                }
            }

            System.out.println();
        }
        for (Box box : this.board[this.board.length - 1]) {
            System.out.print("o");
            if (box.isBottom()) {
                System.out.print("---");
            } else {
                System.out.print("   ");
            }
        }
        System.out.println("o");
    }

}
