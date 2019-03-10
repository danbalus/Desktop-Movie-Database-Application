package observer;

import java.io.IOException;

public interface ObserverI {
    void update() throws IOException, InterruptedException;
}
