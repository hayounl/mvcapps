package mvc;

import tools.Subscriber;

import java.util.LinkedList;
import java.util.List;

public class Publisher {
    private List<tools.Subscriber> observers = new LinkedList<tools.Subscriber>();

    public void subscribe(tools.Subscriber s) {
        observers.add(s);
    }

    public void unsubscribe(tools.Subscriber s) {
        observers.remove(s);
    }
    public void notifySubscribers() {
        for (Subscriber s : observers) {
            s.update();
        }
    }
}