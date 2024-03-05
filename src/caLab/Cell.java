package caLab;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cell {
    private int row;
    private int col;
    private Color color;
    private List<Cell> neighbors;
    public void Cell(int x, int y){
        this.row = x;
        this.col = y;
    }
    public void observe(){}
    public void interact(){}
    public void update(){}
    public void reset(Boolean random){}
    public int getStatus(){
        return 1;
    }
    public void nextState(){}
    public void choosePartner(){}
    public void unPartner(){}
    public Color getColor(){
        return color;
    }

}
