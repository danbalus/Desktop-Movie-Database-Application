package server;

import observer.ObservableI;
import observer.ObserverI;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerConnectionRepository implements ObservableI {

    public List<ServerConnection> getServerConnectionList() {
        return serverConnectionList;
    }

    private static List<ServerConnection> serverConnectionList ;

    public ServerConnectionRepository(){
        serverConnectionList = new ArrayList<ServerConnection>();
    }

    public void createServerConnection(Socket clientSocket) throws IOException {
        ServerConnection serverConnection = new ServerConnection(clientSocket,this);
        register(serverConnection);
        serverConnection.setServerConnection(this);
        serverConnection.start();


    }
    @Override
    public void notifyObserver() throws IOException, InterruptedException {
        for(ServerConnection serverConnection: serverConnectionList){
            System.out.println("\n"+" INTRA\n");
            serverConnection.update();
        }
    }

    @Override
    public void register(ServerConnection observerI) {
        serverConnectionList.add(observerI);
    }

    @Override
    public void unregister(ServerConnection observerI) {
        serverConnectionList.remove(observerI);
    }

    public void setChange() throws IOException {
        for(ServerConnection serverConnection: serverConnectionList){
            serverConnection.writeNotif();
        }
    }
}
