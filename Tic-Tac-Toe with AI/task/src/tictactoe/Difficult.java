package tictactoe;

import java.util.EnumSet;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 20-02-22
 * Time: 15:13
 */
public enum Difficult {
    UNDEFINED("undefined"),
    EASY("easy"),
    MEDIUM("medium"),
    HARD("hard");

    private String alias;

    Difficult(String alias) {
        this.alias = alias;
    }

    public String getAlias() {
        return alias;
    }

    public Difficult getByAlias(String alias) {
        return EnumSet.allOf(Difficult.class)
                      .stream()
                      .filter(rt -> alias.equalsIgnoreCase(rt.getAlias()))
                      .findFirst()
                      .orElse(Difficult.UNDEFINED);
    }
}
