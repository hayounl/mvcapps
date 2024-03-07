package CALab;

import java.awt.*;
import java.util.*;

import mvc.*;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }
    public abstract Cell makeCell(boolean uniform);
    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        populate();
    }
    public Grid() { this(20); }

    protected void populate() {
        // 1. use makeCell to fill in cells
        makeCell(true);
        // 2. use getNeighbors to set the neighbors field of each cell

    }

    // called when Populate button is clicked
    public void repopulate(boolean randomly) {
        //should call reset
        if (randomly) {
            // randomly set the status of each cell
            Random random = new Random();
            for (int i = 0; i < cells.length; i++){
                for (int j = 0; j < cells[i].length;j++){
                    cells[i][j].setStatus(random.nextInt(8));
                }
            }

        } else {
            // set the status of each cell to 0 (dead)
            for (int i = 0; i < cells.length; i++){
                for (int j = 0; j < cells[i].length;j++){
                    cells[i][j].setStatus(0);
                }
            }
        }
        // notify subscribers
    }
    public Set<Cell> getNeighbors(Cell asker, int radius) {
        /*
        return the set of all cells that can be reached from the asker in radius steps.
        If radius = 1 this is just the 8 cells touching the asker.
        Tricky part: cells in row/col 0 or dim - 1.
        The asker is not a neighbor of itself.
        */
        Set<Cell> myNeighbors = new HashSet<Cell>();
        int row = asker.row;
        int col = asker.col;
        int n = dim -1;
        int top = (row-1)%dim;
        int below = (row+1)%dim;
        int left = (col-1)%dim;
        int right = (col+1)%dim;
        //row above
        myNeighbors.add(cells[top][left]);
        myNeighbors.add(cells[top][col]);
        myNeighbors.add(cells[top][right]);
        //row on
        myNeighbors.add(cells[row][left]);
        myNeighbors.add(cells[row][right]);
        //row below
        myNeighbors.add(cells[below][left]);
        myNeighbors.add(cells[below][col]);
        myNeighbors.add(cells[below][right]);

        return myNeighbors;
    }

    // overide these
    public int getStatus() { return 0; }
    public Color getColor() { return Color.GREEN; }
    // cell phases:
    public void observe() {
        // call each cell's observe method and notify subscribers
        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[i].length;j++){
                cells[i][j].observe();
            }
        }
        notifySubscribers();
    }
    public void interact() {
        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[i].length;j++){
                cells[i][j].interact();
            }
        }
        notifySubscribers();
    }
    public void update() {
        for (int i = 0; i < cells.length; i++){
            for (int j = 0; j < cells[i].length;j++){
                cells[i][j].update();
            }
        }
        notifySubscribers();    }
    public void updateLoop(int cycles) {
        observe();
        for(int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
        }
    }
}