package midterm;
import mvc.*;

import javax.swing.*;

public class CalculatorView extends View {
    JTextField accumField = new JTextField("0", 10); // displays 1 in 10 columns
    public CalculatorView(Model model) {
        super(model);
        Calculator calc =(Calculator)model;
        add(accumField);
    }

    @Override
    public void update() {
        super.update();
        Calculator calc = (Calculator) model;
        accumField.setText(calc.accumulator.toString());
        repaint();
    }
}
