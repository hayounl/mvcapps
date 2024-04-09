package stax;
import mvc.*;
import javax.swing.*;
public class StackPanel extends AppPanel {
    public StackPanel(AppFactory factory) {
        super(factory);
        JButton button = new JButton("Clear");
        controlPanel.add(button);
        button.addActionListener(this);
        button = new JButton("Push");
        controlPanel.add(button);
        button.addActionListener(this);
        button = new JButton("Pop");
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
        AppPanel panel = new StackPanel(new StackFactory());
        panel.display();
    }

}
