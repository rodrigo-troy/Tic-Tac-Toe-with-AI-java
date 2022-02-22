package tictactoe;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 17:59
 */
public class PlayerFactory {
    public static Player createPlayer(Difficult difficult,
                                      char symbol) {
        if (difficult.equals(Difficult.EASY) ||
            difficult.equals(Difficult.MEDIUM) ||
            difficult.equals(Difficult.HARD)) {
            return new Computer(difficult,
                                symbol);
        }

        return new Human(symbol);
    }
}
