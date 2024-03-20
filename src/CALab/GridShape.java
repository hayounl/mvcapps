package CALab;

import mvc.Subscriber;

import javax.swing.*;

public class GridShape extends JPanel implements Subscriber {
    private CellView cellViews[][];
    public GridShape(Grid grid, int dim, boolean isNew){
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                Cell cell = grid.getCell(i, j);
                if (isNew){
                    cell.subscribe(this);
                }
                CellView cellView = new CellView(cell); // Associate the Cell object with CellView instance
                cellViews[i][j] = cellView; // Assign CellView instance to the CellView 2D array
                add(cellView); // Add CellView instance to GridView for display
            }
        }
        repaint();
    }

    @Override
    public void update() {

    }
}
