package midterm;

import mvc.*;

public class Calculator extends Model {
    Double accumulator = 0.0;
    public void add(Double x){accumulator+= x;changed();}
    public void clear() { accumulator=0.0; changed(); }

    public void mul(Double x) {
        accumulator*=x;
        changed();
    }

}