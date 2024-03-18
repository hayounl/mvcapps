package CALab.life;

import CALab.Cell;
import CALab.Grid;

import java.awt.*;

public class Agent extends Cell {
    public int ambience;
    protected int status = 0;

    public Agent(Grid grid, int row, int col) {
        super(grid, row, col);
        this.status = 0;
        this.ambience = 8; //Why 8? Shouldn't it be 0?
    }

    public void setStatus(int statNumber) {
        if(statNumber == 1) { //must be a status of 1 or 2
            status = statNumber;
            color = Color.green;
        }
        else if(statNumber == 0) { //must be a status of 1 or 2
            status = statNumber;
            color = Color.red;
        }
    }

    @Override
    public void observe() {
        this.neighbors = myGrid.getNeighbors(this, 1);
        int count = 0;
        for(Cell eachCell : neighbors) {
            if(eachCell.getStatus() == 1) {
                count++;
            }
        }
        this.ambience = count;
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
        notifySubscribers();
    }

    @Override
    public void reset(boolean randomly) {
        this.setStatus(0);
    }

    @Override
    public int getStatus() {
        return this.status;
    }

    public Color getColor() {
        return color;
    }

    public int getAmbience() {
        return ambience;
    }
}

