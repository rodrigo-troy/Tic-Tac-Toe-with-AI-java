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
        this.constructTree(root,
                           true);
        System.out.println("END constructTree");
    }

    private void constructTree(Node parentNode,
                               Boolean isMaxPlayerMove) {
        Board parentNodeBoard = parentNode.getBoard();
        List<Cell> availableCells = parentNodeBoard.getEmptyCells();

        System.out.printf("availableCells[%d]: %s\n",
                          availableCells.size(),
                          availableCells);

        for (Cell cell : availableCells) {
            Board newBoard = parentNodeBoard.getCopy();
            newBoard.addMove(isMaxPlayerMove ? maxPlayer : minPlayer,
                             cell);

            newBoard.printBoard();

            Node newNode = new Node(newBoard,
                                    !isMaxPlayerMove);
            parentNode.addChild(newNode);

            if (newBoard.getEmptyCells().size() > 0) {
                constructTree(newNode,
                              !isMaxPlayerMove);
            }
        }
    }
}
