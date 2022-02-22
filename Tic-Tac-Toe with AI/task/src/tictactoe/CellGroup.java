package tictactoe;

/**
 * Created with IntelliJ IDEA.
 * $ Project: Tic-Tac-Toe with AI
 * User: rodrigotroy
 * Date: 22-02-22
 * Time: 18:10
 */
public class CellGroup {
    private final Cell[] group;

    public CellGroup(Cell cell1,
                     Cell cell2,
                     Cell cell3) {
        group = new Cell[]{cell1, cell2, cell3};
    }


}
