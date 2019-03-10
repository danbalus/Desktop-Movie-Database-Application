package server;

import client.ClientConnection;
import client.bll.repository.ActorRepositoryI;
import client.bll.repository.DirectorRepositoryI;
import client.bll.repository.MovieRepositoryI;
import client.bll.repository.UserRepositoryI;
import client.bll.services.*;
import client.model.constants.SingletonClass;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 3000;

    public static void main(String[] args) throws IOException, ClassNotFoundException
    {
        ServerConnectionRepository serverConnections = new ServerConnectionRepository();
        try (ServerSocket socket = new ServerSocket(PORT))
        {

            while (true)
            {

                System.out.println(Instant.now() + " Waiting for client...");

                Socket clientSocket = socket.accept();


                serverConnections.createServerConnection(clientSocket);

                //ServerConnection serverConnection = new ServerConnection(clientSocket);
               // serverConnection.start();

            }
        }
    }

}
