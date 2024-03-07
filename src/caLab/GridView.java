package CALab;

import javax.swing.*;

import CALab.life.LifeGrid;
import mvc.*;
import java.awt.*;

public class GridView extends View {
    private Grid grid;
    private CellView cellViews[][];

    public GridView(Grid grid) {
        super(grid);
        /*
        Cell cell = new CellView(((Grid)model).getCell(row, col)
        cellViews[row][col] = cell
        set cell.row and cell.col here
        */
        //int len = grid.getDim();
        int len = 20;
        //grid layout 6x6
        setLayout(new GridLayout(len, len));
        int numView = 0;
        //tiles color determined by odd/even
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                if ((j + i)%2 == 1){
                    add(new TileView(Color.red));}
                else{
                    add(new TileView(Color.green));
                }
            }

        }

    }
    class TileView extends JLabel {

        TileView(Color color) {
            setPreferredSize(new Dimension(100,100));
            setOpaque(true);
            setBackground(color);
        }
    }
    public void update(String msg, Object oldState, Object newState) {
        // call update method of each CellView
    }

}