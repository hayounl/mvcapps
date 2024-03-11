package CALab;

import mvc.AppFactory;
import mvc.*;

public class GridFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Some Grid??();
    }
    @Override
    public View makeView(Model model){
        return new CellView();
    }
}
    @Override
    public String getTitle() {
        return "CALab";
    }