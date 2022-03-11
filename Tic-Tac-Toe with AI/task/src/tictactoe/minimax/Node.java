package tictactoe.minimax;

import tictactoe.Board;
import tictactoe.Cell;

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
    private Cell originalMove;
    private boolean isGameOver;
    private boolean isMaxPlayerMove;
    private Integer score;
    private List<Node> children;

    public Node(Board board,
                Cell originalMove,
                boolean isMaxPlayerMove) {
        this.board = board;
        this.originalMove = originalMove;
        this.isGameOver = false;
        this.isMaxPlayerMove = isMaxPlayerMove;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public void setGameOver(boolean gameOver) {
        isGameOver = gameOver;
    }

    public Cell getOriginalMove() {
        return originalMove;
    }

    public void setOriginalMove(Cell originalMove) {
        this.originalMove = originalMove;
    }

    public Board getBoard() {
        return board;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
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
               ", isGameOver=" + isGameOver +
               ", isMaxPlayerMove=" + isMaxPlayerMove +
               ", originalMove=" + originalMove +
               ", score=" + score +
               ", #children=" + this.getChildren().size() +
               '}';
    }
}
