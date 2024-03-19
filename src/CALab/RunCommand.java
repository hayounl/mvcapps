package CALab;

import mvc.*;

public class RunCommand extends Command {

    private String type;
    public RunCommand(Model model, String type) {
        super(model);
        this.type = type;
    }

    public void execute() {
        Grid grid = (Grid)model;
        if(type.equals("Run50")) {
            grid.updateLoop(50);
        }
        else {
            grid.updateLoop(1);
        }
    }

}