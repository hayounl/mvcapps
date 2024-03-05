package caLab;
import caLab.Cell;
public class Grid {
    private int time;
    private int dim = 20;
    public void Grid(){

    }
    public Cell makeCell(){
        return new Cell();
    }
    public void observe(){}
    public void interact(){}
    public void update(){}
    public void updateLoop(int x){
        for (int i = 0; i < x; i++){
            update();
        }
    }
    public void repopulate(Boolean randomly){

    }
}
