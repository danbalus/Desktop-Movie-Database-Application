package observer;

import server.ServerConnection;

import java.io.IOException;

public interface ObservableI { // interface for Subject
    void register(ServerConnection observerI);
    void unregister(ServerConnection observerI);
    void notifyObserver() throws IOException, InterruptedException;
}
