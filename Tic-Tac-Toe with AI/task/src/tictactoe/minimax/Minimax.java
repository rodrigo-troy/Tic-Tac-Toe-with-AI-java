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
                             null);
        tree.setRoot(root);

        //System.out.println("INIT constructTree");
        this.constructTree(root,
                           true,
                           1000);
        //System.out.println("END constructTree");
    }

    public Cell getBestMove() {
        Cell bestMove = this.findBestChild(true,
                                           tree.getRoot().getChildren()).getOriginalMove();
        return bestMove;
    }

    private void setNodeScore(Node node,
                              Boolean isMaxPlayerMove,
                              int distancePoints) {
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
            node.setScore(0);
            return;
        }

        if (xWin) {
            if (node.getOriginalMove().getSymbol() == 'X' &&
                maxPlayer.getSymbol() == 'X' &&
                isMaxPlayerMove) {
                node.setScore(10 + distancePoints);
            } else {
                node.setScore(-10 - distancePoints);
            }

            node.setGameOver(true);

            return;
        }

        if (oWin) {
            if (node.getOriginalMove().getSymbol() == 'O' &&
                maxPlayer.getSymbol() == 'O' &&
                isMaxPlayerMove) {
                node.setScore(10 + distancePoints);
            } else {
                node.setScore(-10 - distancePoints);
            }

            node.setGameOver(true);

            return;
        }

        if (emptyCells > 0) {
            if (node.getOriginalMove().getSymbol() == 'X' &&
                maxPlayer.getSymbol() == 'X' &&
                isMaxPlayerMove) {
                node.setScore(-10 - distancePoints);
            } else {
                node.setScore(10 + distancePoints);
            }

            return;
        }

        node.setGameOver(true);
        node.setScore(0);
    }

    private Node findBestChild(boolean isMaxPlayer,
                               List<Node> children) {
       /* System.out.println("INIT findBestChild");
        System.out.println("\tisMaxPlayer: " + isMaxPlayer);
        children.forEach(node -> System.out.printf("score: %d.\t",
                                                   node.getScore()));*/
        Comparator<Node> byScoreComparator = Comparator.comparing(Node::getScore);

        Node node = children.stream()
                            .max(isMaxPlayer ? byScoreComparator : byScoreComparator.reversed())
                            .orElseThrow(NoSuchElementException::new);

       /* System.out.println("\n\tBest child: " + node);
        System.out.println("END findBestChild\n");
*/
        return node;
    }

    private void constructTree(Node parentNode,
                               Boolean isMaxPlayerMove,
                               int distancePoints) {
        Board parentNodeBoard = parentNode.getBoard();
        List<Cell> availableCells = parentNodeBoard.getEmptyCells();

      /*  System.out.printf("availableCells[%d]: %s\n",
                          availableCells.size(),
                          availableCells);*/

        for (Cell cell : availableCells) {
            Board newBoard = parentNodeBoard.getCopy();
            Cell originalMove = newBoard.addMove(isMaxPlayerMove ? maxPlayer : minPlayer,
                                                 cell);
            //newBoard.printBoard();
            Node newNode = new Node(newBoard,
                                    originalMove);
            this.setNodeScore(newNode,
                              isMaxPlayerMove,
                              distancePoints);

            parentNode.addChild(newNode);

            if (!newNode.isGameOver()) {
                this.constructTree(newNode,
                                   !isMaxPlayerMove,
                                   distancePoints - 100);
            }
        }

        Node bestChild = this.findBestChild(isMaxPlayerMove,
                                            parentNode.getChildren());

        parentNode.setScore(bestChild.getScore());
    }
}
