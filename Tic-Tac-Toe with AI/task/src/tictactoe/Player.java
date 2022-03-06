package tictactoe;

import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 17:24
 */
public abstract class Player {
    protected final Difficult difficult;
    protected final char symbol;

    public Player(Difficult difficult,
                  char symbol) {
        this.difficult = difficult;
        this.symbol = symbol;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract void play(Board board);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return symbol == player.symbol && difficult == player.difficult;
    }

    @Override
    public int hashCode() {
        return Objects.hash(difficult,
                            symbol);
    }
}
