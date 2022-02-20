package tictactoe;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 20-02-22
 * Time: 13:30
 */
public class TicTacToe {
    private final String[] board;

    public TicTacToe() {
        this.board = new String[]{" ", " ", " ", " ", " ", " ", " ", " ", " "};
    }

    private boolean isGameOver() {
        String[] winnerCoord = {"012", "345", "678", "036", "147", "258", "048", "246"};
        int emptyCells = 0;
        int xCells = 0;
        int oCells = 0;

        for (String value : board) {
            switch (value) {
                case "X":
                    xCells++;
                    break;
                case "O":
                    oCells++;
                    break;
                case "_": case " ":
                    emptyCells++;
                    break;
            }
        }

        boolean xWin = false;
        boolean oWin = false;
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
            System.out.print("Impossible");
            return true;
        }

        if (xWin) {
            System.out.print("X wins");
            return true;
        }

        if (oWin) {
            System.out.print("O wins");
            return true;
        }

        if (emptyCells > 0) {
            System.out.print("Game not finished");
            return false;
        }

        System.out.print("Draw");
        return true;
    }

    public void printBoard() {
        System.out.println("---------");
        for (int i = 0; i < board.length; i++) {
            String s = board[i];

            if (s.equals("_")) {
                s = " ";
            }

            if (i % 3 == 0) {
                System.out.print("| " + s);
            } else if (i % 3 == 1) {
                System.out.print(" " + s + " ");
            } else {
                System.out.print(s + " |\n");
            }
        }
        System.out.println("---------");
    }

    private void playComputer() {
        System.out.println("Making move level \"easy\"");

        while (true) {
            int rowIndex = (int) (Math.random() * 3);
            int columnIndex = (int) (Math.random() * 3);

            if (!board[rowIndex * 3 + columnIndex].equals("X") &&
                !board[rowIndex * 3 + columnIndex].equals("O")) {
                board[rowIndex * 3 + columnIndex] = "O";
                printBoard();
                return;
            }
        }
    }

    public void starGame() {
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the coordinates: ");

            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int rowIndex = scanner.nextInt();

            if (rowIndex > 3 || rowIndex < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            if (!scanner.hasNextInt()) {
                System.out.println("You should enter numbers!");
                continue;
            }

            int columnIndex = scanner.nextInt();

            if (columnIndex > 3 || columnIndex < 1) {
                System.out.println("Coordinates should be from 1 to 3!");
                continue;
            }

            System.out.println("coord= " + rowIndex + "," + columnIndex);

            rowIndex--;
            columnIndex--;

            if (board[rowIndex * 3 + columnIndex].equals("X") ||
                board[rowIndex * 3 + columnIndex].equals("O")) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                board[rowIndex * 3 + columnIndex] = "X";
                printBoard();

                if (this.isGameOver()) {
                    return;
                }

                this.playComputer();
                printBoard();

                if (this.isGameOver()) {
                    return;
                }
            }
        }
    }


}
