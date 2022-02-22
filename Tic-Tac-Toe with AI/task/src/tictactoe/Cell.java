package tictactoe;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 15:05
 */
public class Cell {
    private final int x;
    private final int y;
    private final char symbol;

    public Cell(int x,
                int y,
                char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Cell{" +
               "x=" + x +
               ", y=" + y +
               ", symbol=" + symbol +
               '}';
    }
}
