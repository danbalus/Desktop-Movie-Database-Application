package client;

import client.model.constants.SingletonClass;
import client.view.gui.LoginGUI;
import javafx.application.Application;

import java.io.*;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static javafx.application.Application.launch;

public class Client  {
    private static final int PORT = 3000;
    private static final String HOST = "localhost";
    //private List<ClientConnection> clientConnectionList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        ClientConnection clientConnection = new ClientConnection(new Socket(HOST, PORT) ,args);

       // clientConnectionList.add(clientConnection);
       // clientConnection.setCurrentClient(clientConnection);
        clientConnection.start();

        ///clientConnection.login(args);
        //LoginGUI loginGUI = new LoginGUI();
        //loginGUI.startt();
        //clientConnection.setLoginGUI(loginGUI);


        SingletonClass x = SingletonClass.getInstance();
        x.setClientConnection(clientConnection);

        clientConnection.login(args);


        System.out.println("()()"+Thread.currentThread().getName());
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Main app loop.");
        while (true)
        {
            System.out.println(Instant.now() + " Type the message to send to the server and press enter:");

            String message = consoleInput.readLine();
            clientConnection.sendMessageToServer(message);
        }
    }
}
