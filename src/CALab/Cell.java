package CALab;

import java.awt.*;
import java.util.*;
import java.io.*;
import java.util.Random;

import mvc.*;

// Hayoun changed from 'extends Publisher implements Serializable' to 'extends Model'
public abstract class Cell extends Model {
    // protected int row = 0, col = 0; Old version
    protected int row, col; // new 03/18/2024
    protected Set<Cell> neighbors = new HashSet<Cell>();
    protected Grid myGrid = null;
    protected Cell partner = null;

    // Uncommented by Alex
    protected Color color = null; // Color is not mentioned as being included in Cell.java
    protected int status = 0; // Status is not mentioned as being included in Cell.java

    // Default constructor ! Not needed? 03/18/2024
    public Cell() {
        // Constructor
    }

    // Added by Hayoun ! Not needed? 03/18/2024
    public Cell(Grid grid) {
        myGrid = grid;
    }

    // Added by Hayoun ! Not needed 03/18/2024
    /*public void setStatus(int statNumber) {
        status = statNumber;
    }*/

    // Added by Alex 03/18/2024
    public Cell(Grid grid, int row, int col) {
        this.row = row;
        this.col = col;
        myGrid = grid;
    }

    // Added by Alex 03/18/2024
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

    // Abstract Methods
    public abstract void observe(); // observe neighbors' states
    public abstract void interact(); // interact with a random neighbor
    public abstract void update(); // update my state
    public abstract void nextState(); // set status to status + 1 mod whatever
    public abstract void reset(boolean randomly); // set status to a random or initial value

    // From CALab: 'getColor, and getStatus are abstract methods in the Cell class.'
    public abstract Color getColor();
    public abstract int getStatus();

    // Assumption from Life Lab: getAmbience is needed as a label for CellView
    public abstract int getAmbience();

    // Hayoun - Added getStatus() & getColor() Not needed
    /*
    public abstract int getStatus();
    public Color getColor() {
        return color;
    }
    */

}