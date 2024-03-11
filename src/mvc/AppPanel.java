package mvc;

import tools.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

public class AppPanel extends JPanel implements ActionListener, PropertyChangeListener {
    private Model model;
    protected ControlPanel controlPanel = new ControlPanel();
    private View view;
    public AppPanel(AppFactory factory) {
        model = factory.makeModel();
        //controlPanel = new ControlPanel();
        view = factory.makeView();

        this.setLayout(new GridLayout(1, 2));
        this.add(controlPanel, BorderLayout.CENTER);
        JPanel p = new JPanel();
        p.setLayout(new GridLayout(1, 1));
        p.add(view);

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        frame.setTitle(model.fileName);
        frame.setSize(500, 500);
        frame.setMinimumSize(new Dimension(400, 250));
        frame.setVisible(true);

    }

    protected JMenuBar createMenuBar() {
        JMenuBar result = new JMenuBar();
        JMenu fileMenu = tools.Utilities.makeMenu("File", new String[]{"New", "Save", "Open", "Quit"}, this);
        result.add(fileMenu);
        JMenu helpMenu = Utilities.makeMenu("Help", new String[]{"About", "Help"}, this);
        result.add(helpMenu);
        return result;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        try {
            switch (cmd) {
                case "Save": {
                    String fName = Utilities.getFileName((String) null, false);
                    ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(fName));
                    os.writeObject(this.model);
                    os.close();
                    break;
                }

                case "Open": {

                    if (Utilities.confirm("Are you sure? Unsaved changes will be lost!")) {
                        String fName = Utilities.getFileName((String) null, true);
                        ObjectInputStream is = new ObjectInputStream(new FileInputStream(fName));
                        model = (Model) is.readObject();
                        is.close();
                    }
                    break;
                }

                case "New": {
                    model = new Model();
                    view.setModel(model);
                    break;
                }

                case "Quit": {
                    System.exit(0);
                    break;
                }

                case "About": {
                    Utilities.inform("Group 4, SJSU, 2024. All rights reserved.");
                    break;
                }
                default: {
                    throw new Exception("Unrecognized command: " + cmd);
                }
            }
        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.GRAY);
            setLayout(new BorderLayout());
        }

    }
}
