package mvc;

public interface AppFactory {
    Model model = new Model();
    public default View makeView(){return new View(new Model());}
    //public default AppPanel makeAppPanel (){
    //    return new AppPanel();
    //}
    Model makeModel();
    String getTitle();

}
