package stax;
import java.util.*;
import mvc.*;

public class Calculator extends Model {
    Stack<Double> stack = new Stack<Double>();
    public void push(Double x) { stack.push(x); changed(); }
    public void pop() { stack.pop(); changed(); }
    public void clear() { stack.clear(); changed(); }
    public Iterator<Double> iterator() { return stack.iterator(); }
    public void add() {
        stack.push(stack.pop() + stack.pop());
        changed();
    }
    public void mul() {
        stack.push(stack.pop() * stack.pop());
        changed();
    }

}