package minimac;

import tools.Subscriber;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;

public class MiniMacCommands extends JPanel implements Subscriber {
private MiniMac mac;
    static DefaultListModel<String> memoryListModel = new DefaultListModel<String>();
    private JList<String> memList = new JList<String>(memoryListModel);
    static JScrollPane scrollPane;
    public MiniMacCommands(MiniMac mac) {
        this.mac = mac;
        mac.subscribe(this);
        setLayout(new GridLayout(1,1));
        setMinimumSize(new Dimension(250,250));

        scrollPane = new JScrollPane(memList);

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
        scrollPane.repaint();
    }

    protected void updateList(String line, BufferedReader reader) throws IOException {

        memoryListModel = new DefaultListModel<String>();
        while ((line = reader.readLine()) != null) {
            memoryListModel.addElement(line);
        }
        memList.setModel(memoryListModel);
    }



}
