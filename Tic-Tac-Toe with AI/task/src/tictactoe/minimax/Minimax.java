package tictactoe.minimax;

import tictactoe.Board;
import tictactoe.Cell;
import tictactoe.Player;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 06-03-22
 * Time: 19:09
 */
public class Minimax {
    private final Player minPlayer;
    private final Player maxPlayer;
    private final Tree tree;

    public Minimax(Player minPlayer,
                   Player maxPlayer) {
        this.minPlayer = minPlayer;
        this.maxPlayer = maxPlayer;
        tree = new Tree();
    }

    public void constructTree(Board board) {
        Node root = new Node(board,
                             true);
        tree.setRoot(root);

        System.out.println("INIT constructTree");
        this.constructTree(root);
        System.out.println("END constructTree");
    }

    private void constructTree(Node parentNode) {
        List<Cell> availableCells = parentNode.getBoard().getEmptyCells();
        boolean isChildMaxPlayer = !parentNode.isMaxPlayer();

        int cellUsed = 0;
        availableCells.forEach(cell -> {
            Board board = parentNode.getBoard().getCopy();
            board.addMove(isChildMaxPlayer ? maxPlayer : minPlayer,
                          cell);

            Node newNode = new Node(board,
                                    isChildMaxPlayer);
            parentNode.addChild(newNode);

            if (availableCells.size() - cellUsed > 0) {
                constructTree(newNode);
            }
        });
    }
}
