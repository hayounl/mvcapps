package mvc;

public interface AppFactory {
    public default View makeView(){return new View(new Model());}
    //public default AppPanel makeAppPanel (){
    //    return new AppPanel();
    //}
    //hello
    public default Model makeModel(){
        return new Model();
    }

}
