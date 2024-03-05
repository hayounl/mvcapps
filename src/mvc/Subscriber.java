package mvc;

public interface Subscriber {
    // called by notifySubscribers and GridView.update
    public void update();
}
