package midterm;


import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;
public class CalculatorFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Calculator();
    }

    @Override
    public View makeView(Model model) {
        return new CalculatorView(model);
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Clear", "Add", "Mul"};
    }

    @Override
    public Command makeEditCommand(Model model, String type) {
        switch(type) {
            case "Clear": return new ClearCommand(model);
            case "Add": return new AddCommand(model);
            case "Mul": return new MulCommand(model);
            default: return null;
        }
    }

    @Override
    public String getTitle() {
        return "Simple Accumulator Calculator";
    }

    @Override
    public String[] getHelp() {
        return new String[]{
                "Add: takes input and adds to accumulator",
                "Clear: empties accumulator",
                "Mul: takes input and multiplies to value in accumulator"
        };
    }

    @Override
    public String about() {
        return "Accumulator Calculator 1.0, Copyright 2024 by Hayoun Lim";
    }
}