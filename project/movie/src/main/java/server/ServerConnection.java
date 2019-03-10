package server;

import client.ClientConnection;
import client.bll.repository.ActorRepositoryI;
import client.bll.repository.DirectorRepositoryI;
import client.bll.repository.MovieRepositoryI;
import client.bll.repository.UserRepositoryI;
import client.model.constants.SingletonClass;
import client.model.entity.Actor;
import client.model.entity.Director;
import client.model.entity.Movie;
import client.model.entity.User;
import javafx.application.Platform;
import observer.ObservableI;
import observer.ObserverI;
import server.dao.*;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ServerConnection extends Thread implements ObserverI {

    private final Socket clientSocket;
    private long lastSentMessageMillis;
    private MovieRepositoryI movieRepositoryI;
    private ActorRepositoryI actorRepositoryI;
    private DirectorRepositoryI directorRepositoryI;
    private UserRepositoryI userRepositoryI;
    private MovieDAOI movieDAOI;
    private UserDAOI userDAOI;
    private ActorDAOI actorDAOI;
    private DirectorDAOI directorDAOI;
    private List<ClientConnection> listOfClients;
    private ServerConnectionRepository serverConnectionRepository;
    private  boolean ok;
    ServerConnectionRepository serverConnection;
    private SingletonClass client = SingletonClass.getInstance();;
    private ClientConnection clientConnection;
    private Movie movie;
    // private List<Socket> clientsList;
    //private ThreadGroup clientThreadGroup;
    //private  ObjectOutputStream output;
    private ObjectOutputStream output ;
    private ObjectInputStream input;
    public ServerConnection(Socket clientSocket,ServerConnectionRepository serverConnectionRepository ) throws IOException {
        this.clientSocket = clientSocket;
        this.movieRepositoryI = movieRepositoryI;
        movieDAOI = new MovieDAO();
        userDAOI = new UserDAO();
        actorDAOI = new ActorDAO();
        directorDAOI = new DirectorDAO();
        listOfClients = new ArrayList<>();
        this.serverConnectionRepository = serverConnectionRepository;
        output = new ObjectOutputStream(clientSocket.getOutputStream());
        input = new ObjectInputStream(clientSocket.getInputStream());

        movie = new Movie();
    }
    public ServerConnection(Socket clientSocket  ) throws IOException {
        this.clientSocket = clientSocket;
        this.movieRepositoryI = movieRepositoryI;
        movieDAOI = new MovieDAO();
        userDAOI = new UserDAO();
        actorDAOI = new ActorDAO();
        directorDAOI = new DirectorDAO();
        listOfClients = new ArrayList<>();
        this.serverConnectionRepository = serverConnectionRepository;
          output = new ObjectOutputStream(clientSocket.getOutputStream());
          input = new ObjectInputStream(clientSocket.getInputStream());
        movie = new Movie();
    }


    void writeNotif() throws IOException {

    }
    @Override
    public void run()
    {
        try//(ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream());
            //ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream()))
        {
            //this.output = output;
            while (clientSocket.isConnected())
            {
                //if (System.currentTimeMillis() - lastSentMessageMillis > 10000)
               // {
                    //pr();
                    //System.out.println(Instant.now() + " Sending the notification to client");
                //System.out.println("ok= "+ok +" from " + this.getName());
                if(ok){
                       output.writeObject("STATUS_CHANGE_NOTIF");
                        output.writeObject(movie.getName());
                        ok =false;
                    }
                    //System.out.println("ok= "+ok +" from " + this.getName());

                   // lastSentMessageMillis = System.currentTimeMillis();
                //}

                // Seems that input.available() is not reliable?
                boolean clientHasData = clientSocket.getInputStream().available() > 0;
                if (clientHasData) {
                    String msg = (String) input.readObject();
                   // client = (SingletonClass) input.readObject();
                    //System.out.println(client);
                    //clientConnection = client.clientConnection;
                    System.out.println(Instant.now() + "*** Got from client: " + msg);
                   // System.out.println(Instant.now() + " Sending response: ACK");
                    if (msg.equals("UPDATE_MOVIE"/*MessageTransfer.UPDATE_MOVIE*/)) {
                        // output.writeObject(messageToTransfer);
                        Movie movie = (Movie) input.readObject();

                        movieDAOI.updateMovie(movie);
                        this.movie = movie;
                        //serverConnection.notifyAllConnections();
//                          clientConnection.update();
                        ok = true;
                        //serverConnectionRepository.setChange();
                        System.out.println("I've finished the process UPDATE_MOVIE");

                        // Update the Label on the JavaFx Application Thread

                        //output.writeObject("STATUS_CHANGE_NOTIF");


                        serverConnectionRepository.notifyObserver();
                        //Thread.sleep(1000);
                       // output.reset();

                        // output.writeObject(movie);
                    }//else if(msg.equals("CLIENT")) {
                     //   ClientConnection clientConnection = (ClientConnection) input.readObject();
                       //clientsList.add(clientConnection);
                    //}
                    else
                        if(msg.equals("ADD_MOVIE")) {
                            Movie movie = (Movie) input.readObject();
                            movieDAOI.addMovie(movie);
                            System.out.println("I've finished the process ADD_MOVIE");
                        }else
                        if(msg.equals("REMOVE_MOVIE")) {
                            int id = (int) input.readObject();
                            movieDAOI.removeMovie(id);
                            System.out.println("I've finished the process REMOVE_MOVIE");
                        }else
                        if(msg.equals("READ_MOVIE")) {
                            int id = (int) input.readObject();
                            System.out.println("id:= " + id);
                            Movie movie = movieDAOI.getMovieById(id);
                            if(movie != null) {
                                System.out.println("movie:= " + movie.toString());
                            }else{
                                System.out.println("movie:= null");
                            }
                            output.writeObject("READED_MOVIE");
                            output.writeObject(movie);
                        }else
                        if(msg.equals("GET_ALL_MOVIES")) {
                            // int id = (int) input.readObject();
                            //System.out.println("id:= " + id);
                            List<Movie> listMovie = movieDAOI.listMovie();
                            System.out.println("movie:= " + Arrays.asList(listMovie));
                            output.writeObject("LIST_OF_ALL_MOVIES");
                            output.writeObject(listMovie);
                        }else
                        if(msg.equals("READ_MOVIE_BY_NAME")) {
                            String id = (String) input.readObject();
                            System.out.println("id:= " + id);
                            Movie movie = movieDAOI.getMovieByName(id);
                            if(movie != null) {
                                System.out.println("movie by name:= " + movie.toString());
                            }else{
                                System.out.println("movie by name:= null");
                            }
                            output.writeObject("READED_MOVIE_BY_NAME");
                            output.writeObject(movie);
                        }else
                        if(msg.equals("ADD_USER")) {
                            User user = (User) input.readObject();
                            userDAOI.addUser(user);
                            System.out.println("I've finished the process ADD_USER");
                        }else
                        if(msg.equals("REMOVE_USER")) {
                            String id = (String) input.readObject();
                            userDAOI.removeUser(id);
                            System.out.println("I've finished the process REMOVE_USER");
                        }else
                        if(msg.equals("UPDATE_USER")) {
                            User user = (User) input.readObject();
                            userDAOI.updateUser(user);
                        }else
                        if(msg.equals("READ_USER")) {
                            String id = (String) input.readObject();
                            System.out.println("id:= " + id);
                            User user = userDAOI.getUserById(id);
                            if(user != null) {
                                System.out.println("user read:= " + user.toString());
                            }else{
                                System.out.println("user read:= null");
                            }
                            output.writeObject("READED_USER");
                            output.writeObject(user);
                        }else
                        if(msg.equals("GET_ALL_USERS")) {
                            // int id = (int) input.readObject();
                            //System.out.println("id:= " + id);
                            List<User> listUser = userDAOI.listUser();
                            System.out.println("users:= " + Arrays.asList(listUser));
                            output.writeObject("LIST_OF_ALL_USERS");
                            output.writeObject(listUser);
                        }else
                        if(msg.equals("GET_ALL_ACTORS")) {
                            // int id = (int) input.readObject();
                            //System.out.println("id:= " + id);
                            List<Actor> listActor = actorDAOI.listActor();
                            System.out.println("actors:= " + Arrays.asList(listActor));
                            output.writeObject("LIST_OF_ALL_ACTORS");
                            output.writeObject(listActor);
                        }else
                        if(msg.equals("GET_ALL_DIRECTORS")) {
                            // int id = (int) input.readObject();
                            //System.out.println("id:= " + id);
                            List<Director> listDirector = directorDAOI.listDirector();
                            System.out.println("directors:= " + Arrays.asList(listDirector));
                            output.writeObject("LIST_OF_ALL_DIRECTORS");
                            output.writeObject(listDirector);
                        }
                   // output.writeObject("ACK");
                }

                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println(Instant.now() + " Client disconnected?");
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // cleanup
        try
        {
            clientSocket.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


    public void update() throws IOException, InterruptedException {
        //output.writeObject("STATUS_CHANGE_NOTIF");
        //Thread.sleep(1000);
        //System.out.println("STATUS_CHANGE_NOTIF");


       // String idLogedUser = singletonClass.id;//constants.getId();
//       client.clientConnection.update();
       // JOptionPane.showMessageDialog(null, "Modificari", "MESSAGE", JOptionPane.INFORMATION_MESSAGE);
        output.writeObject("STATUS_CHANGE_NOTIF");
        output.writeObject(movie.getName());

    }
    public void setListOfSickets(List<ClientConnection> listOfClients) {
        this.listOfClients = listOfClients;
    }

    public void setServerConnection(ServerConnectionRepository serverConnection) {
        this.serverConnection = serverConnection;
    }
}
