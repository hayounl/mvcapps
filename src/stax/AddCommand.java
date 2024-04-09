package stax;

import mvc.*;

public class AddCommand extends Command  {
    public AddCommand(Model model) {
        super(model);
    }

    @Override
    public void execute() throws Exception {
        Calculator calc = (Calculator) model;
        calc.add();
    }
}