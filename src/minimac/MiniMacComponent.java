package minimac;

import tools.Subscriber;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class MiniMacComponent extends JComponent {
    private MiniMac mac;
    static DefaultListModel<String> listModel1 = new DefaultListModel<String>();
    private JList<String> list1 = new JList<String>(listModel1);
    public MiniMacComponent(MiniMac mac) {
        this.mac = mac;

        Border blackline = BorderFactory.createLineBorder(Color.black);
        setBorder(blackline);
        setBackground(Color.white);




    }
    protected void paintComponent(Graphics gc) {
        ////DefaultListModel<String> listModel2 = new DefaultListModel<String>();
        //for (int num = 0; num < mac.size; num++) {
        //    listModel1.add(num, "Memory[" + num + "] = " + String.valueOf(mac.memory[num]));
        //    System.out.print("hi");
       // }
        //Graphics2D gc2d = (Graphics2D)gc;
        //gc2d.setColor(Color.WHITE);
        //gc2d.fillRect(50, 50, 100, 100);


    }
}

