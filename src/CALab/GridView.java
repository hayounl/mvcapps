package CALab;

import javax.swing.*;
import mvc.*;
import java.awt.*;

public class GridView extends View {
    private Grid grid;
    private CellView cellViews[][]; // 2D Array to represent Grid of CellView JButtons

    /* The GridView component is literally a Grid of CellView objects.
           A CellView is a JButton
           This means GridView should render a Grid of JButtons (CellView's)
                so that each CellView can listen for actions performed on it.
     */
    public GridView(Grid grid) {
        super(grid);
        this.grid = grid;
        int dim = grid.getDim();
        cellViews = new CellView[dim][dim];
        setLayout(new GridLayout(dim, dim));

        initializeCellViews(dim);
    }
    private void initializeCellViews(int gridDim) {
        for (int i = 0; i < gridDim; i++) {
            for (int j = 0; j < gridDim; j++) {
                Cell cell = grid.getCell(i, j);
                cell.subscribe(this);
                CellView cellView = new CellView(cell); // Associate the Cell object with CellView instance
                cellViews[i][j] = cellView; // Assign CellView instance to the CellView 2D array
                add(cellView); // Add CellView instance to GridView for display
            }
        }
    }
    public void update() {
        for (int i = 0; i < grid.getDim(); i++) {
            for (int j = 0; j < grid.getDim(); j++) {
                CellView cellView = cellViews[i][j];
                cellView.update();
            }
        }
    }
}