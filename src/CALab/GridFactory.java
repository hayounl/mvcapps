package CALab;

import mvc.*;

public class GridFactory implements AppFactory {
    public Model makeModel() {return new CALab.life.Society(20);}
    public View makeView(Model m){return new GridView((Grid)m);}
    public String[] getEditCommands() { return new String[] {"Run1", "Run50", "Populate", "Clear"}; }
    public Command makeEditCommand(Model model, String type) {
        if (type == "Run1" || type == "Run50")
            return new RunCommand(model, type);
        else if (type == "Populate")
            return new PopulateCommand(model);
        else if (type == "Clear")
            return new ClearCommand(model);

        return null;
    }
    public String getTitle() {return "CALab";}
    public String[] getHelp() {
        return new String[] {"put explanation of buttons here"};
    }
    public String about() {
        return "CALab version 1.0. SJSU 2024 Team 4";
    }

}

