package tictactoe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 17:24
 */
public abstract class Player {
    protected final Difficult difficult;
    protected final char symbol;
    private final List<Cell> moves;

    public Player(Difficult difficult,
                  char symbol) {
        this.difficult = difficult;
        this.symbol = symbol;
        this.moves = new ArrayList<>();
    }

    protected boolean isAvailable(Cell[][] board,
                                  int rowIndex,
                                  int columnIndex) {
        return board[rowIndex][columnIndex].getSymbol() != 'X' &&
               board[rowIndex][columnIndex].getSymbol() != 'O';
    }

    protected List<Cell> getMoves() {
        return moves;
    }

    protected void addMove(Cell cell) {
        this.moves.add(cell);
    }

    public abstract void play(Cell[][] board,
                              List<Cell> oponentMoves);
}
