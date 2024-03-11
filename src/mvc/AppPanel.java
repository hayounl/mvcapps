package mvc;

import tools.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class AppPanel extends JPanel implements ActionListener, Subscriber {
    private static final int FRAME_WIDTH = 500;
    private static final int FRAME_HEIGHT = 500;
    private Model model;
    private JFrame frame;
    protected JPanel controlPanel;
    private View view;
    private AppFactory factory;
    public AppPanel(AppFactory factory) {
        this.factory = factory;
        model = factory.makeModel();
        controlPanel = new ControlPanel();
        view = factory.makeView(model);
        view.setBackground(Color.GRAY);
        controlPanel.setBackground(Color.PINK);
        this.setLayout(new GridLayout(1, 2));
        this.add(controlPanel, BorderLayout.CENTER);
        this.add(view);

        model.subscribe(this);

        frame = new SafeFrame();
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container cp = frame.getContentPane();
        cp.add(this);
        frame.setJMenuBar(this.createMenuBar());
        //frame.setTitle(model.fileName);
        frame.setTitle(factory.getTitle());
        //frame.setSize(500, 500);
        frame.setSize(FRAME_WIDTH,FRAME_HEIGHT);
        //frame.setMinimumSize(new Dimension(400, 250));
        //frame.setVisible(true);

    }

    public void display() {frame.setVisible(true);}
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
        boolean found = false;
        try {
            for (String s : factory.getEditCommands()){
                if(s.equals(cmd)){
                    found = true;
                    break;
                }
            }
            if(found){
                Command c = factory.makeEditCommand(model, cmd);
                c.execute();
            }
            else{
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
                        Utilities.inform(factory.about());
                        break;
                    }
                    case "Help":{
                        Utilities.inform(factory.getHelp());
                        break;
                    }
                    default: {
                        throw new Exception("Unrecognized command: " + cmd);
                    }
                }
            }

        } catch (Exception ex) {
            Utilities.error(ex);
        }
    }

    @Override
    public void update() {

    }

    class ControlPanel extends JPanel {
        public ControlPanel() {
            setBackground(Color.GRAY);
            JPanel p = new JPanel();

            //setLayout(new BorderLayout());
        }

    }
}
