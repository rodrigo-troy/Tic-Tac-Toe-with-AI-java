package tictactoe;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 06-03-22
 * Time: 13:49
 */
public class Board {
    private Cell[][] table;
    private Map<Player, List<Cell>> playersMoves;

    public Board(Player player1,
                 Player player2) {
        this.preparePlayer(player1,
                           player2);
        this.prepareBoard();
    }

    private void preparePlayer(Player player1,
                               Player player2) {
        this.playersMoves = new HashMap<>();
        this.playersMoves.put(player1,
                              new ArrayList<>());
        this.playersMoves.put(player2,
                              new ArrayList<>());
    }

    private void prepareBoard() {
        this.table = new Cell[3][3];
        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                table[row][column] = Cell.createEmptyCell(row,
                                                          column);
            }
        }
    }

    public List<Cell> getPlayerMoves(Player player) {
        return this.playersMoves.get(player);
    }

    public List<Cell> getOpponentMoves(Player player) {
        return this.getOpponent(player).map(this::getPlayerMoves).orElse(null);
    }

    public Optional<Player> getOpponent(Player player) {
        return this.playersMoves.keySet().stream().filter(p -> p != player).findFirst();
    }

    public void addMove(Player player,
                        Cell cell) {
        this.table[cell.getRow()][cell.getColumn()] = Cell.createCell(cell.getRow(),
                                                                      cell.getColumn(),
                                                                      player.getSymbol());
        this.getPlayerMoves(player).add(cell);
    }

    public void copyMove(Player player,
                         Cell cell) {
        this.table[cell.getRow()][cell.getColumn()] = new Cell(cell);
        this.getPlayerMoves(player).add(cell);
    }

    public Cell[][] getTable() {
        if (table == null) {
            this.table = new Cell[3][3];
        }

        return table;
    }

    public boolean isAvailable(int rowIndex,
                               int columnIndex) {
        return this.getTable()[rowIndex][columnIndex].getSymbol() != 'X' &&
               this.getTable()[rowIndex][columnIndex].getSymbol() != 'O';
    }

    public void printBoard() {
        System.out.println("---------");

        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                char symbol = table[row][column].getSymbol();

                if (column == 0) {
                    System.out.print("| " + symbol);
                } else if (column == 1) {
                    System.out.print(" " + symbol + " ");
                } else {
                    System.out.print(symbol + " |\n");
                }
            }
        }

        System.out.println("---------");
    }

    public List<Cell> getEmptyCells() {
        List<Cell> free = new ArrayList<>();

        for (int row = 0; row < table.length; row++) {
            for (int column = 0; column < table[row].length; column++) {
                char symbol = table[row][column].getSymbol();

                if (symbol == ' ') {
                    free.add(table[row][column]);
                }
            }
        }

        return free;
    }

    public Board getCopy() {
        Player[] players = new Player[2];

        int x = 0;
        for (Player player : this.playersMoves.keySet()) {
            players[x] = player;
            x++;
        }

        Board board = new Board(players[0],
                                players[1]);

        this.playersMoves.forEach((player, cells) -> {
            for (Cell cell : cells) {
                board.copyMove(player,
                               cell);
            }
        });

        return board;
    }
}
