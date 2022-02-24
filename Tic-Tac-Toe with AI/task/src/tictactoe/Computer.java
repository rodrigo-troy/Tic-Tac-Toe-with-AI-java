package tictactoe;

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
    public void play(Cell[][] board) {
        System.out.println("Making move level \"easy\"");

        if (difficult.equals(Difficult.EASY)) {
            easyMove(board);
        } else if (difficult.equals(Difficult.MEDIUM)) {
            mediumMove(board);
        }
    }

    private void mediumMove(Cell[][] board) {

    }

    private void easyMove(Cell[][] board) {
        while (true) {
            int rowIndex = (int) (Math.random() * 3);
            int columnIndex = (int) (Math.random() * 3);

            if (board[rowIndex][columnIndex].getSymbol() != 'X' &&
                board[rowIndex][columnIndex].getSymbol() != 'O') {
                board[rowIndex][columnIndex] = Cell.createCell(rowIndex,
                                                               columnIndex,
                                                               symbol);
                this.addMove(board[rowIndex][columnIndex]);
                return;
            }
        }
    }
}
