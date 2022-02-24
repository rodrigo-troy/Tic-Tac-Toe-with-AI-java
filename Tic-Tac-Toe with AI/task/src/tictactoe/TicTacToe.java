package tictactoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 20-02-22
 * Time: 13:30
 */
public class TicTacToe {
    private final Cell[][] board;

    public TicTacToe() {
        this.board = new Cell[3][3];
        this.prepareBoard();
    }

    public static List<CellGroup> getWinnerCombination() {
        List<CellGroup> winnerCoord = new ArrayList<>();
        winnerCoord.add(new CellGroup(0,
                                      0,
                                      0,
                                      1,
                                      0,
                                      2));
        winnerCoord.add(new CellGroup(1,
                                      0,
                                      1,
                                      1,
                                      1,
                                      2));
        winnerCoord.add(new CellGroup(2,
                                      0,
                                      2,
                                      1,
                                      2,
                                      2));
        winnerCoord.add(new CellGroup(0,
                                      0,
                                      1,
                                      0,
                                      2,
                                      0));
        winnerCoord.add(new CellGroup(0,
                                      1,
                                      1,
                                      1,
                                      2,
                                      1));
        winnerCoord.add(new CellGroup(0,
                                      2,
                                      1,
                                      2,
                                      2,
                                      2));
        winnerCoord.add(new CellGroup(0,
                                      0,
                                      1,
                                      1,
                                      2,
                                      2));
        winnerCoord.add(new CellGroup(0,
                                      2,
                                      1,
                                      1,
                                      2,
                                      0));
        return winnerCoord;
    }

    private void prepareBoard() {
        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                board[row][column] = Cell.createEmptyCell(row,
                                                          column);
            }
        }
    }

    private boolean isGameOver(Player player1,
                               Player player2) {
        List<CellGroup> winnerCoord = getWinnerCombination();
        int xCells = player1.getMoves().size();
        int oCells = player2.getMoves().size();
        int emptyCells = 9 - xCells - oCells;

        boolean xWin = false;
        boolean oWin = false;
        for (CellGroup cellGroup : winnerCoord) {
            Cell cell1 = board[cellGroup.getRowIndex0()][cellGroup.getColumnIndex0()];
            Cell cell2 = board[cellGroup.getRowIndex1()][cellGroup.getColumnIndex1()];
            Cell cell3 = board[cellGroup.getRowIndex2()][cellGroup.getColumnIndex2()];

            if (cell1.getSymbol() == cell2.getSymbol() &&
                cell2.getSymbol() == cell3.getSymbol()) {
                if (cell1.getSymbol() == 'X') {
                    xWin = true;
                    break;
                } else if (cell1.getSymbol() == 'O') {
                    oWin = true;
                    break;
                }
            }
        }

        if (xCells - oCells >= 2 ||
            oCells - xCells >= 2 ||
            ((xWin == oWin) && xWin)) {
            System.out.println("Impossible");
            return true;
        }

        if (xWin) {
            System.out.println("X wins");
            return true;
        }

        if (oWin) {
            System.out.println("O wins");
            return true;
        }

        if (emptyCells > 0) {
            System.out.println("Game not finished");
            return false;
        }

        System.out.println("Draw");
        return true;
    }

    private void printBoard() {
        System.out.println("---------");

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                char symbol = board[row][column].getSymbol();

                if (column == 0) {
                    System.out.print("| " + symbol);
                } else if (column == 1) {
                    System.out.print(" " + symbol + " ");
                } else {
                    System.out.print(symbol + " |\n");
                }
            }
        }

        System.out.println("---------");
    }

    public void starGame() {
        Player player1 = null;
        Player player2 = null;
        Scanner scanner = new Scanner(System.in);

        while (player1 == null || player2 == null) {
            System.out.print("Input command: ");

            String line = scanner.nextLine();

            if (line.equalsIgnoreCase("exit")) {
                return;
            }

            String[] c = line.split(" ");

            if (c.length != 3) {
                System.out.println("Bad parameters!");
                continue;
            }

            if (!c[0].equalsIgnoreCase("start")) {
                System.out.println("Bad parameters!");
                continue;
            }

            if (Difficult.getByAlias(c[1]).equals(Difficult.UNDEFINED)) {
                System.out.println("Bad parameters!");
                continue;
            } else {
                player1 = PlayerFactory.createPlayer(Difficult.getByAlias(c[1]),
                                                     'X');
            }

            if (Difficult.getByAlias(c[2]).equals(Difficult.UNDEFINED)) {
                System.out.println("Bad parameters!");
            } else {
                player2 = PlayerFactory.createPlayer(Difficult.getByAlias(c[2]),
                                                     'O');
            }
        }

        this.printBoard();

        while (true) {
            player1.play(board,
                         player2.getMoves());
            this.printBoard();

            if (this.isGameOver(player1,
                                player2)) {
                return;
            }

            player2.play(board,
                         player1.getMoves());

            this.printBoard();

            if (this.isGameOver(player1,
                                player1)) {
                return;
            }
        }
    }
}
