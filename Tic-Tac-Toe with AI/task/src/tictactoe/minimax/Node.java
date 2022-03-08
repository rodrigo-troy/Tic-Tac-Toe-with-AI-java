package tictactoe.minimax;

import tictactoe.Board;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: learning-java
 * User: rodrigotroy
 * Date: 07-03-22
 * Time: 22:08
 */
public class Node {
    private final Board board;
    private boolean isMaxPlayer;
    private int score;
    private List<Node> children;

    public Node(Board board,
                boolean isMaxPlayer) {
        this.board = board;
        this.isMaxPlayer = isMaxPlayer;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isMaxPlayer() {
        return isMaxPlayer;
    }

    public void setMaxPlayer(boolean maxPlayer) {
        isMaxPlayer = maxPlayer;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public List<Node> getChildren() {
        if (children == null) {
            children = new ArrayList<>();
        }

        return children;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }

    public void addChild(Node newNode) {
        this.getChildren().add(newNode);
    }

    @Override
    public String toString() {
        return "Node{" +
               "board=" + board +
               ", isMaxPlayer=" + isMaxPlayer +
               ", score=" + score +
               ", #children=" + this.getChildren().size() +
               '}';
    }
}
