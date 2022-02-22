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
    private Player player1;
    private Player player2;

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

    private boolean isGameOver() {
        List<CellGroup> winnerCoord = getWinnerCombination();
        int emptyCells = 0;
        int xCells = 0;
        int oCells = 0;

        for (int row = 0; row < board.length; row++) {
            for (int column = 0; column < board[row].length; column++) {
                switch (board[row][column].getSymbol()) {
                    case 'X':
                        xCells++;
                        break;
                    case 'O':
                        oCells++;
                        break;
                    case '_': case ' ':
                        emptyCells++;
                        break;
                }
            }
        }

        boolean xWin = false;
        boolean oWin = false;
        for (CellGroup cellGroup : winnerCoord) {

        }


        for (String s : winnerCoord) {
            String[] pos = s.split("");

            int pos1 = Integer.parseInt(pos[0]);
            int pos2 = Integer.parseInt(pos[1]);
            int pos3 = Integer.parseInt(pos[2]);

            if (board[pos1].equals(board[pos2]) && board[pos2].equals(board[pos3])) {
                if (board[pos1].equals("X")) {
                    xWin = true;
                } else if (board[pos1].equals("O")) {
                    oWin = true;
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
                player2 = PlayerFactory.createPlayer(Difficult.getByAlias(c[1]),
                                                     'O');
            }
        }

        this.printBoard();

        while (true) {
            player1.play(board);
            printBoard();

            if (this.isGameOver()) {
                return;
            }

            player2.play(board);

            printBoard();

            if (this.isGameOver()) {
                return;
            }
        }
    }
}
