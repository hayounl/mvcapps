package stax;
import mvc.*;
import java.util.*;
import javax.swing.*;

public class StackView extends View {
    JList<Double> memory;
    DefaultListModel<Double> memModel;
    public StackView(Model model) {
        super(model);
        memModel = new DefaultListModel<Double>();
        Calculator calc =(Calculator) model;
        Iterator<Double> it = calc.iterator();
        while(it.hasNext()) { memModel.add(0, it.next()); }
        memory = new JList<Double>(memModel);
        memory.setLayoutOrientation(JList.VERTICAL);
        JScrollPane sp = new JScrollPane(memory);
        add(sp);
    }

    @Override
    public void update() {
        super.update();
        Calculator calc = (Calculator) model;
        Iterator<Double> it = ((Calculator) model).iterator();
        memModel.clear();
        while(it.hasNext()) { memModel.add(0, it.next()); }
        repaint();
    }
}
