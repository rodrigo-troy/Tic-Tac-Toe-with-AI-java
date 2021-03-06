package tictactoe;

import java.util.Scanner;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 17:52
 */
public class Human extends Player {
    public Human(char symbol) {
        super(Difficult.USER,
              symbol);
    }

    @Override
    public void play(Board board) {
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

            if (board.isAvailable(rowIndex,
                                  columnIndex)) {
                board.addMove(this,
                              Cell.createCell(rowIndex,
                                              columnIndex,
                                              symbol));
                return;
            } else {
                System.out.println("This cell is occupied! Choose another one!");
            }
        }
    }
}
