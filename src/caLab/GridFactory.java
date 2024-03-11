package CALab;

import mvc.*;
import stoplight2.ChangeCommand;

public class GridFactory implements AppFactory {
    private Grid grid;
    public String getTitle() {return "CALab";}
    public Model makeModel() {return grid;}
    public void setModel(Model m){grid = (Grid)m;}
    public View makeView(Model m){return new CellView((Cell)m);}
    public String[] getEditCommands() { return new String[] {"Run1", "Run50"}; }
    public Command makeEditCommand(Model model, String type, Object source) {
        if (type == "Run1")
            return new ChangeCommand(model);
        return null;
    }
    public String[] getHelp() {
        return new String[] {"explanation of buttons here"};
    }
    public String about() {
        return "CALab version 1.0. SJSU 2024 Team 4";
    }

}

