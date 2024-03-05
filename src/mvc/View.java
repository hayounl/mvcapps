package mvc;

import tools.Subscriber;

import javax.swing.*;
import java.awt.*;

public class View extends JPanel implements Subscriber {

    public View(){
        setLayout(new GridLayout(1,1));
        setMinimumSize(new Dimension(250,250));
    }
    @Override
    public void update() {
    }
}

