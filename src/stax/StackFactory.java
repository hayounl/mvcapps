package stax;

import mvc.AppFactory;
import mvc.Command;
import mvc.Model;
import mvc.View;

public class StackFactory implements AppFactory {
    @Override
    public Model makeModel() {
        return new Calculator();
    }

    @Override
    public View makeView(Model model) {
        return new StackView(model);
    }

    @Override
    public String[] getEditCommands() {
        return new String[] {"Push", "Pop", "Clear", "Add", "Mul"};
    }

    @Override
    public Command makeEditCommand(Model model, String type) {
        switch(type) {
            case "Pop": return new PopCommand(model);
            case "Push": return new PushCommand(model);
            case "Clear": return new ClearCommand(model);
            case "Add": return new AddCommand(model);
            case "Mul": return new MulCommand(model);
            default: return null;
        }
    }

    @Override
    public String getTitle() {
        return "Stack Calculator";
    }

    @Override
    public String[] getHelp() {
        return new String[]{
                "Pop: removes top element from stack",
                "Push: prompts user for a number and pushes it onto stack",
                "Add: replaces top 2 elements by their sum",
                "Clear: empties stack",
                "Mul: replaces top 2 elements by their product"
        };
    }

    @Override
    public String about() {
        return "Stack Calculator 1.0, Copyright 2024 by Cyberdellic Designz";
    }
}