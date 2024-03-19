package CALab;

import mvc.*;
public class PopulateCommand extends Command{
    public PopulateCommand(Model model){super(model);}
    public void execute(){
        Grid grid = (Grid)model;
        // Removed in Alex's version grid.populate();
        grid.populate(true);
    }
}
