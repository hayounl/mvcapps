package CALab.life;

import CALab.Cell;
import CALab.Grid;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Society extends Grid {
    public static int percentAlive = 50;
    public static Set<Integer> rebirth = new HashSet<Integer>();
    public static Set<Integer> death = new HashSet<Integer>();

    public Society(int dim){
        super();
    }

    static {
        rebirth.add(3);
        death.add(0);
        death.add(1);
        death.add(4);
        death.add(5);
        death.add(6);
        death.add(7);
        death.add(8);
    }

    /*
    public void populate() {
        //Loop through entire grid cell by cell & populate it
        Random random = new Random();
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                // 1. use makeCell to fill in cells
                Agent newCell = makeCell(j, i);
                cells[j][i] = newCell;
                // 1.1 'repopulate' the cell as it is made
                newCell.setStatus(random.nextInt(2));
            }
        }
        // Loop through entire grid cell by cell & set ambience levels
        //      by calling observe()
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                cells[j][i].observe();
            }
        }
        notifySubscribers();
    }
    */
    @Override
    public Agent makeCell(int row, int col) {
        return new Agent(this, row, col);
    }
}
