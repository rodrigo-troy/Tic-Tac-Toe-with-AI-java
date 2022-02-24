package tictactoe;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 18:10
 */
public class CellGroup {
    private int rowIndex0;
    private int columnIndex0;
    private int rowIndex1;
    private int columnIndex1;
    private int rowIndex2;
    private int columnIndex2;

    public CellGroup(int rowIndex0,
                     int columnIndex0,
                     int rowIndex1,
                     int columnIndex1,
                     int rowIndex2,
                     int columnIndex2) {
        this.rowIndex0 = rowIndex0;
        this.columnIndex0 = columnIndex0;
        this.rowIndex1 = rowIndex1;
        this.columnIndex1 = columnIndex1;
        this.rowIndex2 = rowIndex2;
        this.columnIndex2 = columnIndex2;
    }

    public int getRowIndex0() {
        return rowIndex0;
    }

    public int getColumnIndex0() {
        return columnIndex0;
    }

    public int getRowIndex1() {
        return rowIndex1;
    }

    public int getColumnIndex1() {
        return columnIndex1;
    }

    public int getRowIndex2() {
        return rowIndex2;
    }

    public int getColumnIndex2() {
        return columnIndex2;
    }

    public int matches(List<Cell> cells,
                       char symbol) {
        int x = 0;

        for (Cell cell : cells) {
            if ((cell.getRow() == rowIndex0 && cell.getColumn() == columnIndex0 && cell.getSymbol() == symbol) ||
                (cell.getRow() == rowIndex1 && cell.getColumn() == columnIndex1 && cell.getSymbol() == symbol) ||
                (cell.getRow() == rowIndex2 && cell.getColumn() == columnIndex2 && cell.getSymbol() == symbol)) {
                x++;
            }
        }

        return x;
    }

    public Optional<Cell> getWinnerMove(List<Cell> cells,
                                        char symbol) {
        boolean contain0 = false;
        boolean contain1 = false;
        boolean contain2 = false;

        for (Cell cell : cells) {
            if (cell.getRow() == rowIndex0 && cell.getColumn() == columnIndex0 && cell.getSymbol() == symbol && !contain0) {
                contain0 = true;
                continue;
            }

            if (cell.getRow() == rowIndex1 && cell.getColumn() == columnIndex1 && cell.getSymbol() == symbol && !contain1) {
                contain1 = true;
                continue;
            }

            if (cell.getRow() == rowIndex2 && cell.getColumn() == columnIndex2 && cell.getSymbol() == symbol && !contain2) {
                contain2 = true;
            }
        }

        if (contain1 && contain2) {
            return Optional.of(Cell.createCell(this.getRowIndex0(),
                                               this.getColumnIndex0(),
                                               symbol));
        }

        if (contain0 && contain2) {
            return Optional.of(Cell.createCell(this.getRowIndex1(),
                                               this.getColumnIndex1(),
                                               symbol));
        }

        if (contain0 && contain1) {
            return Optional.of(Cell.createCell(this.getRowIndex2(),
                                               this.getColumnIndex2(),
                                               symbol));
        }

        return Optional.empty();
    }

    @Override
    public String toString() {
        return "CellGroup{" +
               "(" + rowIndex0 +
               "," + columnIndex0 +
               "), (" + rowIndex1 +
               "," + columnIndex1 +
               "), (" + rowIndex2 +
               "," + columnIndex2 +
               ")}";
    }
}
