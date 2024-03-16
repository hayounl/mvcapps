package CALab.life;

import CALab.Cell;
import CALab.Grid;

public class Agent extends Cell {
    int ambience;

    public Agent(Grid grid) {
        super(grid);
        this.status = 0;
        this.ambience = 8; //Why 8? Shouldn't it be 0?
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
            this.status = 1;
            //rebirth cell
        }
        else {
            this.status = 0;
            //kills this cell
        }
    }

    @Override
    public void nextState() {
        if(this.status == 0) {
            this.status = 1;
        }
        else {
            this.status = 0;
        }
    }

    @Override
    public void reset(boolean randomly) {
        this.status = 0;
    }

    @Override
    public int getStatus() {
        return this.status;
    }
}
