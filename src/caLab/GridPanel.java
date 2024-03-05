package CALab;
import CALab.life.LifeGrid;
import minimac.AppPanel;
import tools.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class GridPanel extends JPanel implements ActionListener {
    private LifeGrid grid;
    private ControlPanel controls;
    private GridView gridview;
    public GridPanel(){
        gridview = new GridView(grid);
        this.setLayout(new GridLayout(1,2));
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        controls = new GridPanel.ControlPanel();
        this.add(controls, BorderLayout.CENTER);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1,1));
        p.add(gridview);
        //p.add(cmds);
        this.add(p);


        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("CALab");
        frame.setSize(1000, 500);
        frame.setMinimumSize(new Dimension(500,250));
        frame.setVisible(true);


    }
    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu editMenu = Utilities.makeMenu("Edit", new String[]{"Parse", "Run", "Clear"}, this);
        result.add(editMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try{
            switch (cmd){
                case "Run1":
                    break;

                case "Run50":
                    break;

                case "Populate":
                    break;

                case "Clear":
                    break;

                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    //os.writeObject(this.);
                    os.close();
                    break;
                }

                case "Open":
                    break;

                case "New":
                    break;

                case "Quit": {
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform("Hayoun Lim, SJSU, CALab, 2024. All rights reserved.");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "Info about buttons here later"
                    };
                    Utilities.inform(cmmds);
                    break;

                }

                default: {
                    throw new Exception("Unrecognized command: " + cmd);
                }
            }
        }
        catch(Exception ex){
            Utilities.error(ex);
        }
    }
    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.GRAY);

            setLayout(new GridLayout(2,2));
            JPanel p = new JPanel();
            JPanel p2 = new JPanel();
            JPanel p3 = new JPanel();
            JPanel p4 = new JPanel();

            JButton run1 = new JButton("Run1");
            JButton run50 = new JButton("Run50");
            JButton populate = new JButton("Populate");
            JButton clear = new JButton("Clear");
            run1.addActionListener(GridPanel.this);
            run50.addActionListener(GridPanel.this);
            populate.addActionListener(GridPanel.this);
            clear.addActionListener(GridPanel.this);

            p.add(run1);
            p.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p2.add(run50);
            p2.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p3.add(populate);
            p3.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p4.add(clear);
            p3.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            add(p);
            add(p2);
            add(p3);
            add(p4);
        }
    }
    public static void main(String[] args) {
        GridPanel app = new GridPanel();
    }

}
