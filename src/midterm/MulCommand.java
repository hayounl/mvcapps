package midterm;

import mvc.*;
public class MulCommand extends Command  {
    public MulCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Calculator calc = (Calculator) model;
        Double x = Double.parseDouble(Utilities.ask("Enter a number"));
        calc.mul(x);
    }
}