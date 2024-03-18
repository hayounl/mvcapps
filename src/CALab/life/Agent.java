package CALab.life;

import CALab.Cell;
import CALab.Grid;

import java.awt.*;

public class Agent extends Cell {
    public int ambience; // possibly make private?
    public int status; // possibly make private?

    // default constructor
    public Agent() {
        this.status = 0; // Default status value for an Agent (Cell)
        this.ambience = 0; // Default ambience value for an Agent (Cell)
    }

    // Original 03/18/2024
    public Agent(Grid grid) {
        super(grid);
        this.status = 0; // default status is dead
    }
    // Added by Alex 03/18/2024
    public Agent(Grid grid, int row, int col) {
        super(grid, row, col);
        this.status = 0;
        this.ambience = 8; //Why 8? Shouldn't it be 0?
    }

    @Override
    public void observe() {
        // get this Cell's neighbors and count it for Ambience
        this.neighbors = myGrid.getNeighbors(this, 1);
        int count = 0;
        for(Cell eachCell : neighbors) {
            if(eachCell.getStatus() == 1) {
                count++;
            }
        }
        ambience = count;
    }

    @Override
    public void interact() {
        //do nothing
    }

    @Override
    public void update() {
        if(ambience == 2) {
            //do nothing lol
        }
        else if(ambience == 3){
            this.setStatus(1);
            //rebirth cell
        }
        else {
            this.setStatus(0);
            //kills this cell
        }
    }

    @Override
    public void nextState() {
        if(this.status == 0) {
            this.setStatus(1);
        }
        else {
            this.setStatus(0);
        }
        this.update();
    }

    @Override
    public void reset(boolean randomly) {
        this.setStatus(0);
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    @Override
    public Color getColor() {
        // return color of the agent based on its status
        if(status == 1) return Color.green;
        else return Color.red;
    }

    @Override
    public int getAmbience() {
        return ambience;
    }
}
