package stax;
import mvc.*;
public class PopCommand extends Command  {
    public PopCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Calculator calc = (Calculator) model;
        calc.pop();
    }
}
