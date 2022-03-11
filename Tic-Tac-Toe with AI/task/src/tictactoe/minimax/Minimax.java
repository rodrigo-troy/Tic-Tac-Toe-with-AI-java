package tictactoe.minimax;

import tictactoe.*;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 06-03-22
 * Time: 19:09
 */
public class Minimax {
    private final Player maxPlayer;
    private final Player minPlayer;
    private final Tree tree;

    public Minimax(Player maxPlayer,
                   Player minPlayer) {
        this.maxPlayer = maxPlayer;
        this.minPlayer = minPlayer;
        tree = new Tree();
    }

    public void constructTree(Board board) {
        Node root = new Node(board,
                             null,
                             true);
        tree.setRoot(root);

        this.constructTree(root,
                           true,
                           0);
    }

    public Cell getBestMove() {
        return this.findBestChild(true,
                                  tree.getRoot().getChildren()).getOriginalMove();
    }

    private boolean isGameOver(Node node) {
        List<CellGroup> winnerCoord = TicTacToe.getWinnerCombination();

        Board nodeBoard = node.getBoard();

        int xCells = nodeBoard.getPlayerMoves(maxPlayer.getSymbol() == 'X' ? maxPlayer : minPlayer).size();
        int oCells = nodeBoard.getPlayerMoves(maxPlayer.getSymbol() == 'O' ? maxPlayer : minPlayer).size();
        int emptyCells = 9 - xCells - oCells;

        boolean xWin = false;
        boolean oWin = false;
        for (CellGroup cellGroup : winnerCoord) {
            Cell cell1 = nodeBoard.getTable()[cellGroup.getRowIndex0()][cellGroup.getColumnIndex0()];
            Cell cell2 = nodeBoard.getTable()[cellGroup.getRowIndex1()][cellGroup.getColumnIndex1()];
            Cell cell3 = nodeBoard.getTable()[cellGroup.getRowIndex2()][cellGroup.getColumnIndex2()];

            if (cell1.getSymbol() == cell2.getSymbol() &&
                cell2.getSymbol() == cell3.getSymbol()) {
                if (cell1.getSymbol() == 'X') {
                    xWin = true;
                    break;
                } else if (cell1.getSymbol() == 'O') {
                    oWin = true;
                    break;
                }
            }
        }

        if (xWin || oWin) {
            return true;
        }

        return emptyCells <= 0;
    }

    private void setNodeScore(Node node,
                              Boolean isMaxPlayerMove,
                              int depth) {
        List<CellGroup> winnerCoord = TicTacToe.getWinnerCombination();

        Board nodeBoard = node.getBoard();

        int xCells = nodeBoard.getPlayerMoves(maxPlayer.getSymbol() == 'X' ? maxPlayer : minPlayer).size();
        int oCells = nodeBoard.getPlayerMoves(maxPlayer.getSymbol() == 'O' ? maxPlayer : minPlayer).size();
        int emptyCells = 9 - xCells - oCells;

        boolean xWin = false;
        boolean oWin = false;
        for (CellGroup cellGroup : winnerCoord) {
            Cell cell1 = nodeBoard.getTable()[cellGroup.getRowIndex0()][cellGroup.getColumnIndex0()];
            Cell cell2 = nodeBoard.getTable()[cellGroup.getRowIndex1()][cellGroup.getColumnIndex1()];
            Cell cell3 = nodeBoard.getTable()[cellGroup.getRowIndex2()][cellGroup.getColumnIndex2()];

            if (cell1.getSymbol() == cell2.getSymbol() &&
                cell2.getSymbol() == cell3.getSymbol()) {
                if (cell1.getSymbol() == 'X') {
                    xWin = true;
                    break;
                } else if (cell1.getSymbol() == 'O') {
                    oWin = true;
                    break;
                }
            }
        }

        if (xCells - oCells >= 2 ||
            oCells - xCells >= 2) {
            return;
        }

        if (xWin) {
            if (node.getOriginalMove().getSymbol() == 'X' &&
                maxPlayer.getSymbol() == 'X' &&
                isMaxPlayerMove) {
                node.setScore(10 - depth);
            } else {
                node.setScore(depth - 10);
            }

            node.setGameOver(true);

            return;
        }

        if (oWin) {
            if (node.getOriginalMove().getSymbol() == 'O' &&
                maxPlayer.getSymbol() == 'O' &&
                isMaxPlayerMove) {
                node.setScore(10 - depth);
            } else {
                node.setScore(depth - 10);
            }

            node.setGameOver(true);

            return;
        }

        if (emptyCells > 0) {
            return;
        }

        node.setGameOver(true);
        node.setScore(0);
    }

    private Node findBestChild(boolean isMaxPlayer,
                               List<Node> children) {
        Comparator<Node> byScoreComparator = Comparator.comparing(Node::getScore);

        return children.stream()
                       .max(isMaxPlayer ? byScoreComparator : byScoreComparator.reversed())
                       .orElseThrow(NoSuchElementException::new);
    }

    private void constructTree(Node parentNode,
                               Boolean isMaxPlayerMove,
                               int depth) {
        Board parentNodeBoard = parentNode.getBoard();
        List<Cell> availableCells = parentNodeBoard.getEmptyCells();

      /*  System.out.printf("availableCells[%d]: %s\n",
                          availableCells.size(),
                          availableCells);*/

        for (Cell cell : availableCells) {
            Board newBoard = parentNodeBoard.getCopy();
            Cell originalMove = newBoard.addMove(isMaxPlayerMove ? maxPlayer : minPlayer,
                                                 cell);

            Node newNode = new Node(newBoard,
                                    originalMove,
                                    isMaxPlayerMove);
            parentNode.addChild(newNode);

            if (this.isGameOver(newNode)) {
                this.setNodeScore(newNode,
                                  isMaxPlayerMove,
                                  depth);
            } else {
                this.constructTree(newNode,
                                   !isMaxPlayerMove,
                                   depth + 1);
            }
        }

        List<Node> children = parentNode.getChildren();

        Node bestChild = this.findBestChild(isMaxPlayerMove,
                                            children);

        parentNode.setScore(bestChild.getScore());
    }
}
