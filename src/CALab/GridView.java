package CALab;

import javax.swing.*;
import mvc.*;
import java.awt.*;

public class GridView extends View {
    private Grid grid; // Added by Hayoun
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

        // Version 2: commented out line 21 code bit
        initializeCellViews(dim); // method to handle creating cellViews[][]
        // update(); // Call update after CellViews have been initialized
        // Fill in 'cellViews' array and then add 'cellViews' to the GridView
        /*
        for(int i = 0; i < len; i++) {
            for(int j = 0; j < len; j++) {
                Cell cell = grid.getCell(i, j);
                CellView cellView = new CellView(cell);
                cellView.setBackground(cell.getColor());
                cellView.setBorder(BorderFactory.createLineBorder(Color.black));

                // For LifeLab, this should be getAmbience not getStatus !!!
                cellView.setText(Integer.toString(cell.getStatus()));
                cellView.addActionListener(e -> {
                    // When a CellView is clicked, toggle the status of the associated cell
                    cell.nextState();
                    // Update the color of the CellView based on the new status of the cell
                    cellView.setBackground(cell.getColor());
                    // Update the label of the CellView based on the new ambience level of the cell
                    // For LifeLab, this should be getAmbience not getStatus !!!
                    cellView.setText(Integer.toString(cell.getAmbience())); // replaced getStatus() with getAmbience()
                });
                cellViews[i][j] = cellView;
                add(cellView);
            }
        }
        */

        /*
        Cell cell = new CellView(((Grid)model).getCell(row, col)
        cellViews[row][col] = cell
        set cell.row and cell.col here
        */
        //int len = grid.getDim();
        /*int len = 20;

        setLayout(new GridLayout(len, len));
        int numView = 0;
        //tiles color determined by odd/even
        for (int i = 0; i < len; i++)
        {
            for (int j = 0; j < len; j++)
            {
                if ((j + i)%2 == 1){
                    TileView tile = new TileView(Color.red);
                    tile.setText("2");
                    add(tile);
                }
                else{
                    add(new TileView(Color.green));
                }
            }

        }*/
    }

    private void initializeCellViews(int gridDim) {
        for (int i = 0; i < gridDim; i++) {
            for (int j = 0; j < gridDim; j++) {
                Cell cell = grid.getCell(i, j); // get Cell obj at this pos in the grid
                CellView cellView = new CellView(cell); // Associate the Cell object with CellView instance
                cellViews[i][j] = cellView; // Assign CellView instance to the CellView 2D array
                add(cellView); // Add CellView instance to GridView for display
            }
        }
    }

    // Added in version 2 to follow professor's partial implementation of GridView
    public void update() {
        for (int i = 0; i < grid.getDim(); i++) {
            for (int j = 0; j < grid.getDim(); j++) {
                CellView cellView = cellViews[i][j];
                cellView.update(); // Call the update method of each CellView
            }
        }
    }

    /*class TileView extends JLabel {
        TileView(Color color) {
            setPreferredSize(new Dimension(100,100));
            setOpaque(true);
            setBackground(color);
            setBorder(BorderFactory.createLineBorder(Color.black));

        }
    }*/
    /*public void update(String msg, Object oldState, Object newState) {
        // call update method of each CellView
    }*/

}