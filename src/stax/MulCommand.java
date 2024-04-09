package stax;
import mvc.*;
public class MulCommand extends Command  {
    public MulCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Calculator calc = (Calculator) model;
        calc.mul();
    }
}