package mvc;

import tools.Subscriber;

import javax.swing.*;

public class Command extends JPanel implements Subscriber {
    protected Model model;
    public Command(Model model){
        this.model = model;
    }

    @Override
    public void update() {

    }
}
