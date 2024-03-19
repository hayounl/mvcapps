package CALab;

import java.util.*;
import CALab.life.Agent;
import mvc.*;

public abstract class Grid extends Model {
    static private int time = 0;
    protected int dim = 20;
    protected Cell[][] cells;

    public int getDim() { return dim; }
    public int getTime() { return time; }
    public Cell getCell(int row, int col) { return cells[row][col]; }

    public abstract Cell makeCell(int row, int col);

    public Grid(int dim) {
        this.dim = dim;
        cells = new Cell[dim][dim];
        // Removed in Alex's version: populate();
        populate(false);
    }
    public Grid() { this(20); }

    // Removed in Alex's version: public void populate() {
    public void populate(boolean initialized) {
        //Loop through entire grid cell by cell & populate it
        if(!initialized) {
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    // 1. use makeCell to fill in cells
                    cells[i][j] = makeCell(i, j);
                }
            }
        }
        else {
            // Call repopulate to set the status of each cell
            repopulate(true);
        }
        // Loop through entire grid cell by cell & set ambience levels by calling observe()
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[i][j].observe();
            }
        }
    }
    public void clear() {
        repopulate(false);
        System.out.println("Called Repopulate(false)!");
    }
    // Can be called by Populate OR Clear
    //      randomly = True means we are repopulating randomly
    //      randomly = False means we are 'clearing' the grid
    public void repopulate(boolean randomly) {
        if (randomly) {
            // Loop through entire grid cell by cell & randomly set the status of each cell
            Random random = new Random();
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    Cell newCell = cells[i][j];
                    Agent agentCell = (Agent) newCell;
                    agentCell.setStatus(random.nextInt(2));
                }
            }
        } else {
            // Loop through entire grid cell by cell & set the status of each cell to 0 (dead)
            for (int i = 0; i < cells.length; i++) {
                for (int j = 0; j < cells[i].length; j++) {
                    Cell newCell = cells[i][j];
                    Agent agentCell = (Agent) newCell;
                    agentCell.setStatus(0);
                }
            }
        }
        observe();
        // notify subscribers
        notifySubscribers();
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
        int top, below, left, right;

        if(row == 0) {
            top = n;
        } else {
            top = (row-1)%n;
        }

        if(row == n) {
            below = 0;
        } else {
            below = (row+1)%n;
        }//below = (row+1)%n;

        if(col == 0) {
            left = n;
        } else {
            left = (col-1)%n;
        }

        if(col == n) {
            right = 0;
        } else {
            right = (col+1)%n;
        }//right = (col+1)%n;
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
        notifySubscribers();
    }
    public void updateLoop(int cycles) {
        observe();
        for(int cycle = 0; cycle < cycles; cycle++) {
            interact();
            update();
            observe();
            time++;
            System.out.println("time = " + time);
            Agent newAgent = (Agent)cells[1][1];
            notifySubscribers();

            System.out.println(newAgent.getAmbience());
        }
    }
}