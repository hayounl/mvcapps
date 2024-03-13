package CALab;

import mvc.*;

public class RunCommand extends Command {

    public RunCommand(Model model, String type) {
        super(model);
    }

    public void execute() {
        Grid grid = (Grid)model;
        // do something
    }

}