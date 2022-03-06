package tictactoe;

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
    public void play(Board board) {
        if (difficult.equals(Difficult.EASY)) {
            System.out.println("Making move level \"easy\"");
            easyMove(board);
        } else if (difficult.equals(Difficult.MEDIUM)) {
            System.out.println("Making move level \"medium\"");
            mediumMove(board);
        } else if (difficult.equals(Difficult.HARD)) {
            System.out.println("Making move level \"hard\"");
            hardMove(board);
        }
    }

    private void hardMove(Board board) {
        this.easyMove(board);
    }

    private void mediumMove(Board board) {
        for (CellGroup cellGroup : TicTacToe.getWinnerCombination()) {
            int matches = cellGroup.matches(board.getPlayerMoves(this),
                                            this.symbol);

            if (matches == 2) {
                Optional<Cell> winnerMove = cellGroup.getWinnerMove(board.getPlayerMoves(this),
                                                                    this.symbol);

                if (winnerMove.isPresent()) {
                    Cell cell = winnerMove.get();

                    if (board.isAvailable(cell.getRow(),
                                          cell.getColumn())) {
                        board.addMove(this,
                                      Cell.createCell(cell.getRow(),
                                                      cell.getColumn(),
                                                      symbol));
                        return;
                    }
                }
            }
        }

        for (CellGroup cellGroup : TicTacToe.getWinnerCombination()) {
            int matches = cellGroup.matches(board.getOpponentMoves(this),
                                            this.symbol == 'X' ? 'O' : 'X');

            if (matches == 2) {
                Optional<Cell> winnerMove = cellGroup.getWinnerMove(board.getOpponentMoves(this),
                                                                    this.symbol == 'X' ? 'O' : 'X');

                if (winnerMove.isPresent()) {
                    Cell cell = winnerMove.get();

                    if (board.isAvailable(cell.getRow(),
                                          cell.getColumn())) {
                        board.addMove(this,
                                      Cell.createCell(cell.getRow(),
                                                      cell.getColumn(),
                                                      symbol));
                        return;
                    }
                }
            }
        }

        this.easyMove(board);
    }

    private void easyMove(Board board) {
        while (true) {
            int rowIndex = (int) (Math.random() * 3);
            int columnIndex = (int) (Math.random() * 3);

            if (board.isAvailable(rowIndex,
                                  columnIndex)) {
                board.addMove(this,
                              Cell.createCell(rowIndex,
                                              columnIndex,
                                              symbol));
                return;
            }
        }
    }
}
