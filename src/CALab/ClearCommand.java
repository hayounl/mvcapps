package CALab;
import mvc.*;
public class ClearCommand extends Command{
    public ClearCommand(Model model){super(model);}
    public void execute(){
        Grid grid = (Grid)model;
        //
        int dim = grid.getDim();
        for (int i = 0; i < dim; i++){
            for (int j = 0; j < dim; j++){
                Cell cell = grid.getCell(i,j);
                cell.setStatus(0);
            }
        }
        grid.notifySubscribers();
    }
}
