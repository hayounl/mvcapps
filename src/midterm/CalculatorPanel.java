package midterm;

import mvc.*;

import javax.swing.*;
public class CalculatorPanel extends AppPanel {
    public CalculatorPanel(AppFactory factory) {
        super(factory);
        JButton button = new JButton("Clear");
        controlPanel.add(button);
        button.addActionListener(this);
        button = new JButton("Add");
        controlPanel.add(button);
        button.addActionListener(this);
        button = new JButton("Mul");
        controlPanel.add(button);
        button.addActionListener(this);
    }

    public static void main(String[] args) {
        AppPanel panel = new CalculatorPanel(new CalculatorFactory());
        panel.display();
    }

}
