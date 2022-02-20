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

    private void playComputer(String symbol) {
        System.out.println("Making move level \"easy\"");

        while (true) {
            int rowIndex = (int) (Math.random() * 3);
            int columnIndex = (int) (Math.random() * 3);

            if (!board[rowIndex * 3 + columnIndex].equals("X") &&
                !board[rowIndex * 3 + columnIndex].equals("O")) {
                board[rowIndex * 3 + columnIndex] = symbol;
                return;
            }
        }
    }

    private void playHuman(String symbol) {
        Scanner scanner = null;

        while (true) {
            scanner = new Scanner(System.in);
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

            rowIndex--;
            columnIndex--;

            if (board[rowIndex * 3 + columnIndex].equals("X") ||
                board[rowIndex * 3 + columnIndex].equals("O")) {
                System.out.println("This cell is occupied! Choose another one!");
            } else {
                board[rowIndex * 3 + columnIndex] = symbol;
                return;
            }
        }
    }

    public void starGame() {
        String player1 = null;
        String player2 = null;
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

            if (!c[1].equalsIgnoreCase("easy") &&
                !c[1].equalsIgnoreCase("user")) {
                System.out.println("Bad parameters!");
                continue;
            } else {
                player1 = c[1];
            }

            if (!c[2].equalsIgnoreCase("easy") &&
                !c[2].equalsIgnoreCase("user")) {
                System.out.println("Bad parameters!");
            } else {
                player2 = c[2];
            }
        }

        this.printBoard();

        boolean player1IsHuman = player1.equals("user");
        boolean player2IsHuman = player2.equals("user");

        while (true) {
            if (player1IsHuman) {
                this.playHuman("X");
            } else {
                this.playComputer("X");
            }

            printBoard();

            if (this.isGameOver()) {
                return;
            }

            if (player2IsHuman) {
                this.playHuman("O");
            } else {
                this.playComputer("O");
            }

            printBoard();

            if (this.isGameOver()) {
                return;
            }
        }
    }
}
