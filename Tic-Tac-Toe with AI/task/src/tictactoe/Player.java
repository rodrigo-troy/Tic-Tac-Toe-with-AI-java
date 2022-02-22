package tictactoe;

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

    public Player(Difficult difficult,
                  char symbol) {
        this.difficult = difficult;
        this.symbol = symbol;
    }

    public abstract void play(Cell[][] board);
}
