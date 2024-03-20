package CALab;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import mvc.*;

public class CellView extends JButton implements ActionListener, Subscriber {
    private Cell myCell;

    public CellView(Cell c) {
        myCell = c;
        if (c != null) {
            c.subscribe(this); // Make the instance of CellView subscribe to the notifications of the Cell
        }
        this.addActionListener(this);
        update(); // we have to update the appearance of CellView when it is initialized!
        // Initialize the appearance of the CellView using the Cell's current status
        setBackground(Color.RED);
        setBorder();
        setText("0");
    }

    public CellView() { this(null); }

    // Change the state of the associated Cell when the CellView JButton is clicked
   @Override
    public void actionPerformed(ActionEvent e) {
        myCell.nextState();
        update(); // call update needed? No, since CellView is a subscriber of Cell, it will sync to its updates
    }

    public void setBackground() {
        setBackground(myCell.getColor());
    }
    public void setBorder() {
        setBorder(BorderFactory.createLineBorder(Color.black));
    }
    public void setText() {
        setText(Integer.toString(myCell.getStatus()));
    }
    public void setAmbience() {
        setText(Integer.toString(myCell.getAmbience()));
    }

    @Override
    public void update() {
        setOpaque(true);
        setBackground();
        setAmbience();
    }
}