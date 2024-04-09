package stax;
import mvc.*;
public class PushCommand extends Command  {
    public PushCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Calculator calc = (Calculator) model;
        Double x = Double.parseDouble(Utilities.ask("Enter a number"));
        calc.push(x);
    }
}