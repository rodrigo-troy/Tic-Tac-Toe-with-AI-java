package tictactoe;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 15:05
 */
public class Cell {
    private final int row;
    private final int column;
    private final char symbol;

    public Cell(Cell cell) {
        this.row = cell.getRow();
        this.column = cell.getColumn();
        this.symbol = cell.getSymbol();
    }

    public Cell(int row,
                int column,
                char symbol) {
        this.row = row;
        this.column = column;
        this.symbol = symbol;
    }

    public static Cell createEmptyCell(int row,
                                       int column) {
        return new Cell(row,
                        column,
                        ' ');
    }

    public static Cell createCell(int row,
                                  int column,
                                  char symbol) {
        return new Cell(row,
                        column,
                        symbol);
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public char getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "Cell{" +
               "x=" + column +
               ", y=" + row +
               ", symbol=" + symbol +
               '}';
    }
}
