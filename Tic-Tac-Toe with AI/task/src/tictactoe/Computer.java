package tictactoe;

import java.util.List;
import java.util.Optional;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 17:53
 */
public class Computer extends Player {
    public Computer(Difficult difficult,
                    char symbol) {
        super(difficult,
              symbol);
    }

    @Override
    public void play(Cell[][] board,
                     List<Cell> oponentMoves) {
        if (difficult.equals(Difficult.EASY)) {
            System.out.println("Making move level \"easy\"");
            easyMove(board);
        } else if (difficult.equals(Difficult.MEDIUM)) {
            System.out.println("Making move level \"medium\"");
            mediumMove(board,
                       oponentMoves);
        } else if (difficult.equals(Difficult.HARD)) {
            System.out.println("Making move level \"hard\"");
            hardMove(board,
                     oponentMoves);
        }
    }

    private void hardMove(Cell[][] board,
                          List<Cell> opponentMoves) {
        this.easyMove(board);
    }

    private void mediumMove(Cell[][] board,
                            List<Cell> opponentMoves) {
        for (CellGroup cellGroup : TicTacToe.getWinnerCombination()) {
            int matches = cellGroup.matches(this.getMoves(),
                                            this.symbol);

            if (matches == 2) {
                Optional<Cell> winnerMove = cellGroup.getWinnerMove(this.getMoves(),
                                                                    this.symbol);

                if (winnerMove.isPresent()) {
                    Cell cell = winnerMove.get();

                    if (this.isAvailable(board,
                                         cell.getRow(),
                                         cell.getColumn())) {
                        board[cell.getRow()][cell.getColumn()] = Cell.createCell(cell.getRow(),
                                                                                 cell.getColumn(),
                                                                                 symbol);
                        this.addMove(board[cell.getRow()][cell.getColumn()]);
                        return;
                    }
                }
            }
        }

        for (CellGroup cellGroup : TicTacToe.getWinnerCombination()) {
            int matches = cellGroup.matches(opponentMoves,
                                            this.symbol == 'X' ? 'O' : 'X');

            if (matches == 2) {
                Optional<Cell> winnerMove = cellGroup.getWinnerMove(opponentMoves,
                                                                    this.symbol == 'X' ? 'O' : 'X');

                if (winnerMove.isPresent()) {
                    Cell cell = winnerMove.get();

                    if (this.isAvailable(board,
                                         cell.getRow(),
                                         cell.getColumn())) {
                        board[cell.getRow()][cell.getColumn()] = Cell.createCell(cell.getRow(),
                                                                                 cell.getColumn(),
                                                                                 symbol);
                        this.addMove(board[cell.getRow()][cell.getColumn()]);
                        return;
                    }
                }
            }
        }

        this.easyMove(board);
    }

    private void easyMove(Cell[][] board) {
        while (true) {
            int rowIndex = (int) (Math.random() * 3);
            int columnIndex = (int) (Math.random() * 3);

            if (this.isAvailable(board,
                                 rowIndex,
                                 columnIndex)) {
                board[rowIndex][columnIndex] = Cell.createCell(rowIndex,
                                                               columnIndex,
                                                               symbol);
                this.addMove(board[rowIndex][columnIndex]);
                return;
            }
        }
    }
}
