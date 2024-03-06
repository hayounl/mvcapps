package CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.List;
import java.util.Random;

import mvc.*;

public abstract class Cell extends Publisher implements Serializable {

    protected int row = 0, col = 0;
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid = null;
    protected Cell partner = null;


    // choose a random neighbor as a partner
    public void choosePartner() {
        if (partner == null && neighbors != null) {
			/*
			Set partner to null
			Convert neighbors set to a local array
			Starting at a random position in the array search for a neighbor without a partner
			Make the first such neighbor (if any) the partner and set its partner field to this
			*/
            boolean partnered = false;
            Random random = new Random();
            int r = random.nextInt(neighbors.size());

            Cell[] myNeighbors = new Cell[neighbors.size()];
            neighbors.toArray(myNeighbors);

            for (int i = r; i < neighbors.size(); i++){
                if (myNeighbors[i].partner == null && !partnered){
                    partner = myNeighbors[i];
                    partner.partner = this;
                    partnered = true;
                }
            }
            for (int i = 0; i < r; i++){
                if (myNeighbors[i].partner == null && !partnered){
                    partner = myNeighbors[i];
                    partner.partner = this;
                    partnered = true;
                }
            }

        }

    }

    public void unpartner() {
        if (partner != null) {
            if (partner.partner != null) {
                partner.partner = null;
            }
            partner = null;
        }
    }

    // observer neighbors' states
    public abstract void observe();
    // interact with a random neighbor
    public abstract void interact();
    // update my state
    public abstract void update();
    // set status to status + 1 mod whatever
    public abstract void nextState();
    // set status to a random or initial value
    public abstract void reset(boolean randomly);
    public abstract int getStatus();
    public Color getColor() {
        return Color.BLACK;
    }

}