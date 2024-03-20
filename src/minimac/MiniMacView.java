package minimac;

import tools.Subscriber;

import javax.swing.*;
import java.awt.*;

public class MiniMacView extends JPanel implements Subscriber {
    private MiniMac mac;
    static DefaultListModel<String> memoryListModel = new DefaultListModel<String>();
    private JList<String> memList = new JList<String>(memoryListModel);
    private JScrollPane scrollPane;

    public MiniMacView(MiniMac mac) {
        this.mac = mac;
        mac.subscribe(this);
        setLayout(new GridLayout(1,1));
        setMinimumSize(new Dimension(250,250));

        scrollPane = new JScrollPane(memList);
        scrollPane.setMinimumSize(new Dimension(250,250));
        add(scrollPane);
    }
    public void setMac(MiniMac newMac) {
        mac.unsubscribe(this);
        mac = newMac;
        mac.subscribe(this);
        memoryListModel = new DefaultListModel<String>();
        memList.setModel(memoryListModel);
        scrollPane.repaint();
        revalidate();
        repaint();
    }

    @Override
    public void update() {
        updateScroll();
        scrollPane.repaint();
    }
    protected void updateScroll() {
        memoryListModel = new DefaultListModel<String>();
        for(int num = 0; num < mac.size; num++){
            memoryListModel.add(num,"Memory["+num+"] = "+String.valueOf(mac.memory[num]));
        }
        memList.setModel(memoryListModel);
    }

}

