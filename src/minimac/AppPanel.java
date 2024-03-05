
package minimac;

import tools.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AppPanel extends JPanel implements ActionListener {
    private MiniMac mac;
    private ControlPanel controls;
    private MiniMacView view;
    private MiniMacCommands cmds;
    public AppPanel() {

        mac = new MiniMac();
        controls = new AppPanel.ControlPanel();
        view = new MiniMacView(mac);
        cmds = new MiniMacCommands(mac);
        this.setLayout(new GridLayout(1,2));
        this.add(controls, BorderLayout.CENTER);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(2,1));
        p.add(view);
        p.add(cmds);
        this.add(p);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle("MiniMac");
        frame.setSize(500, 500);
        frame.setMinimumSize(new Dimension(400,250));
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
                case "Parse":
                    JFrame frame = new JFrame("Input");

                    String inputValue = JOptionPane.showInputDialog(frame, "Enter Program File Name");
                    if (inputValue != null) {

                        StringBuilder content = new StringBuilder();
                        try {
                            BufferedReader reader = new BufferedReader(new FileReader(inputValue));
                            BufferedReader reader2 = new BufferedReader(new FileReader(inputValue));

                            String line;
                            while ((line = reader.readLine()) != null) {
                                content.append(line).append("\n");
                            }
                            cmds.updateList(line, reader2);
                            view.updateScroll();
                            JOptionPane.showMessageDialog(frame, "File read successfully!");

                            reader.close();
                        } catch (IOException ex) {
                            JOptionPane.showMessageDialog(frame, "Error reading file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }

                        String fileContent = content.toString();
                        mac.program = MiniMacParser.parse(fileContent);
                        break;
                    }

                case "Run":
                    mac.execute();
                    break;

                case "Clear":
                    mac.clear();
                    System.out.println("clear");
                    break;

                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.mac);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        mac = (MiniMac) is.readObject();
                        view.setMac(mac);
                        cmds.setMac(mac);
                        is.close();
                    }
                    break;
                }

                case "New": {
                    mac = new MiniMac();
                    view.setMac(mac);
                    cmds.setMac(mac);
                    break;
                }

                case "Quit": {
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform("Hayoun Lim, SJSU, MiniMac, 2024. All rights reserved.");
                    break;
                }

                case "Help": {
                    String[] cmmds = new String[]{
                            "Parse: read commands from file",
                            "Run: runs commands in file",
                            "Clear: resets memory cells"
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

            setLayout(new GridLayout(3,1));
            JPanel p = new JPanel();
            JPanel p2 = new JPanel();
            JPanel p3 = new JPanel();

            JButton parse = new JButton("Parse");
            JButton run = new JButton("Run");
            JButton clear = new JButton("Clear");
            parse.addActionListener(AppPanel.this);
            run.addActionListener(AppPanel.this);
            clear.addActionListener(AppPanel.this);

            p.add(parse);
            p.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
            p2.add(run);
            p2.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
            p3.add(clear);
            p3.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

            add(p);
            add(p2);
            add(p3);
        }
    }


    public static void main(String[] args) {
        AppPanel app = new AppPanel();
    }
}