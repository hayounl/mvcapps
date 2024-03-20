package CALab;

import javax.swing.*;
import mvc.*;
import java.awt.*;

public class GridView extends View {
    private CellView cellViews[][]; // 2D Array to represent Grid of CellView JButtons
    /* The GridView component is literally a Grid of CellView objects.
           A CellView is a JButton
           This means GridView should render a Grid of JButtons (CellView's)
                so that each CellView can listen for actions performed on it.
     */
    public GridView(Grid grid) {
        super(grid);
        int dim = grid.getDim();
        cellViews = new CellView[dim][dim];
        setLayout(new GridLayout(dim, dim));

        initializeCellViews(grid.dim);
    }
    private void initializeCellViews(int gridDim) {
        for (int i = 0; i < gridDim; i++) {
            for (int j = 0; j < gridDim; j++) {
                Cell cell = ((Grid)model).getCell(i, j);
                cell.subscribe(this);
                CellView cellView = new CellView(cell); // Associate the Cell object with CellView instance
                cellViews[i][j] = cellView; // Assign CellView instance to the CellView 2D array
                add(cellView); // Add CellView instance to GridView for display
                cellView.update();
            }
        }
        repaint();
    }
    public void setModel(Model model){
        super.setModel(model);
        removeAll();
        initializeCellViews(((Grid)model).dim);
        revalidate();
        repaint();

    }
    public void paintComponent(Graphics gc){
        super.paintComponent(gc);
        Grid grid = (Grid)model;
        for (int i = 0; i < grid.getDim(); i++) {
            for (int j = 0; j < grid.getDim(); j++) {
                cellViews[i][j].update();
            }
        }
    }

}