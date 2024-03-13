package CALab;
import CALab.life.LifeGrid;
import mvc.*;

import javax.swing.*;
public class GridPanel extends AppPanel {
    private JButton run1;
    private JButton run50;
    private JButton populate;
    private JButton clear;
    public GridPanel(AppFactory factory){
        super(factory);

        run1 = new JButton("Run1");
        run50 = new JButton("Run50");
        populate = new JButton("Populate");
        clear = new JButton("Clear");
        run1.addActionListener(GridPanel.this);
        run50.addActionListener(GridPanel.this);
        populate.addActionListener(GridPanel.this);
        clear.addActionListener(GridPanel.this);
        controlPanel.add(run1);
        controlPanel.add(run50);
        controlPanel.add(populate);
        controlPanel.add(clear);

    }

    public static void main(String[] args) {
        AppFactory factory = new GridFactory();
        GridPanel panel = new GridPanel(factory);
        panel.display();
    }

}
