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
        return new String[] {"'Run1' will perform one complete updateLoop() cycle." +
                "\n'Run50' will perform 50 complete updateLoop() cycles." +
                "\n'Populate' will randomly set the state of each cell." +
                "\n'Clear' will clear the entire grid of cells. Setting each cell state to 0 (dead)."};
    }
    public String about() {
        return "CALab version 1.0. SJSU 2024 Team 4";
    }

}

