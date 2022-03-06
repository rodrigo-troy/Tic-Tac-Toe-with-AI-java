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
    private Board board;

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

    private boolean isGameOver(Player player1,
                               Player player2) {
        List<CellGroup> winnerCoord = getWinnerCombination();
        int xCells = this.board.getPlayerMoves(player1).size();
        int oCells = this.board.getPlayerMoves(player2).size();
        int emptyCells = 9 - xCells - oCells;

        boolean xWin = false;
        boolean oWin = false;
        for (CellGroup cellGroup : winnerCoord) {
            Cell cell1 = this.board.getTable()[cellGroup.getRowIndex0()][cellGroup.getColumnIndex0()];
            Cell cell2 = this.board.getTable()[cellGroup.getRowIndex1()][cellGroup.getColumnIndex1()];
            Cell cell3 = this.board.getTable()[cellGroup.getRowIndex2()][cellGroup.getColumnIndex2()];

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

        this.board = new Board(player1,
                               player2);
        this.board.printBoard();

        while (true) {
            player1.play(board);
            this.board.printBoard();

            if (this.isGameOver(player1,
                                player2)) {
                return;
            }

            player2.play(board);

            this.board.printBoard();

            if (this.isGameOver(player1,
                                player1)) {
                return;
            }
        }
    }
}
