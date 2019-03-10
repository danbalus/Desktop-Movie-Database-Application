package client;

import client.model.constants.SingletonClass;
import client.model.entity.Actor;
import client.model.entity.Director;
import client.model.entity.Movie;
import client.model.entity.User;
import client.view.gui.LoginController;
import client.view.gui.LoginGUI;
import javafx.application.Application;
import message.MessageTransfer;
import observer.ObserverI;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static javafx.application.Application.launch;

public class ClientConnection extends Thread implements ObserverI {
    private final Socket socket;

    public ObjectOutputStream getOutput() {
        return output;
    }

    private final ObjectOutputStream output;

    public ObjectInputStream getInput() {
        return input;
    }

    private final ObjectInputStream input;

    public void setLoginGUI(LoginGUI loginGUI) {
        this.loginGUI = loginGUI;
    }

    private  LoginGUI loginGUI;
    private final String[] args;
   // private /*MessageTransfer*/ String messageToTransfer;
    private Object objectToTransfer;
    private Object objectReceivedMovie;
    private Object objectReceivedUser;


    private List<User> listOfUsersReceived;
    private List<Movie> listOfMoviesReceived;
    private List<Actor> listOfActorsReceived;
    private List<Director> listOfDirectorsReceived;
    private boolean iReceived ;//= false;;
    private String msg;//= "Init";
    private String movieName;



    //private ClientConnection currentClient;

    public ClientConnection(Socket socket, String[] args) throws IOException {
        this.socket = socket;
        output = new ObjectOutputStream(socket.getOutputStream());
        input = new ObjectInputStream(socket.getInputStream());
        loginGUI = new LoginGUI();
        this.args = args;
       // iReceived = false;
       // msg = "Init";
    }
    public ClientConnection(Socket socket, ObjectInputStream input, ObjectOutputStream output) throws IOException {
        this.socket = socket;
        this. output = output;
        this.input = input;
        args = new String[9999];
        loginGUI = new LoginGUI();
        // iReceived = false;
        // msg = "Init";
    }
    public List<Movie> getListOfMoviesReceived() {
        return listOfMoviesReceived;
    }
    public Object getObjectReceivedMovie() {
        return objectReceivedMovie;
    }
    public Object getObjectReceivedUser() {
        return objectReceivedUser;
    }
    public List<User> getListOfUsersReceived() {
        return listOfUsersReceived;
    }
    public List<Actor> getListOfActorsReceived() {
        return listOfActorsReceived;
    }

    public List<Director> getListOfDirectorsReceived() {
        return listOfDirectorsReceived;
    }
    public boolean isiReceived() {
        return iReceived;
    }
    //public void setCurrentClient(ClientConnection currentClient) {
    //    this.currentClient = currentClient;
   // }
    public void login(String [] args){{
    }
        launch(LoginGUI.class, args);
      // Application.launch(LoginGUI.class, args);
    //loginGUI.start();
    }

    @Override
    public void run() {
        //login(args);
        try
        {
            this.setName("Sefu");
            System.out.println("<><><><><><><>------<><><<>");
           // sendClient();
            //Application.launch(LoginGUI.class, args);
            System.out.println("<><><><><><><><><><<>");
           //SingletonClass x = SingletonClass.getInstance();
            //x.setClientConnection(this);
           // loginGUI.startLoginGUI();
//            loginGUI.wait(2000);
            while (socket.isConnected())
            {
                // Seems that input.available() is not reliable?

                boolean serverHasData = socket.getInputStream().available() > 0;
                if (serverHasData) {
                    ///////////////// msg = (String) input.readObject();
                   // System.out.println( "Time: " + Instant.now() + " ***Got from server: " + msg);
                   ///// System.out.println( "Time: " + Instant.now() + " ***Got from server: ");
                    iReceived = false;


                   // checkReceive();
                        // output.writeObject(movie);
                }
                //checkReceive();
                //if ((true/*MessageTransfer.UPDATE_MOVIE*/)) {
                 //   System.out.println("^^^^^^^^^^^^^^^^^^^^^");
                 //   output.writeObject(messageToTransfer);
                  //  Movie movie = (Movie) objectToTransfer;
                 //   output.writeObject(movie);
                //}
                try
                {
                    Thread.sleep(500);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
            System.out.println("Time: " + Instant.now() + " Server disconnected");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resetIReceived(){
        iReceived = false;
    }

    private void checkReceive() throws IOException, ClassNotFoundException {
        msg = (String) input.readObject();

        if (msg.equals("STATUS_CHANGE_NOTIF")) {
            //output.writeObject("OK");
            String movieName = (String) input.readObject();
            this.movieName = movieName;
            update();
            iReceived = true;
            System.out.println("I've finished the process STATUS_CHANGE_NOTIF(UPDATE)");

        }else

        if (msg.equals("READED_MOVIE"/*MessageTransfer.UPDATE_MOVIE*/)) {
            // output.writeObject(messageToTransfer);
            Movie movie = (Movie) input.readObject();
            System.out.println("movie:== " + movie);
            objectReceivedMovie = movie;
            iReceived = true;
            System.out.println("I've finished the process READED_MOVIE");

        }else
            if (msg.equals("LIST_OF_ALL_MOVIES")) {
            // output.writeObject(messageToTransfer);
            List<Movie> listMovie = (List<Movie>) input.readObject();
            System.out.println("LIST OF movie:== " + Arrays.asList(listMovie));
            listOfMoviesReceived = listMovie;
            iReceived = true;
                System.out.println("I've finished the process LIST_OF_ALL_MOVIES");
            }else
            if (msg.equals("READED_MOVIE_BY_NAME")) {
            // output.writeObject(messageToTransfer);
            Movie movie = (Movie) input.readObject();
            System.out.println("movie by name :== " + movie);objectReceivedMovie = movie;
            iReceived = true;
            System.out.println("I've finished the process READED_MOVIE_BY_NAME");
            }else
            if (msg.equals("READED_USER"/*MessageTransfer.UPDATE_MOVIE*/)) {
            // output.writeObject(messageToTransfer);
            User user = (User) input.readObject();
            System.out.println("user:== " + user);
            objectReceivedUser = user;
            iReceived = true;
            System.out.println("I've finished the process READED_USER");

            }else
            if (msg.equals("LIST_OF_ALL_USERS")) {
                // output.writeObject(messageToTransfer);
                List<User> listUser = (List<User>) input.readObject();
                System.out.println("LIST OF user:== " + Arrays.asList(listUser));
                listOfUsersReceived = listUser;
                iReceived = true;
                System.out.println("I've finished the process LIST_OF_ALL_USERS");

            }else
                if (msg.equals("LIST_OF_ALL_ACTORS")) {
                // output.writeObject(messageToTransfer);
                List<Actor> listActor = (List<Actor>) input.readObject();
                System.out.println("LIST OF actors:== " + Arrays.asList(listActor));
                listOfActorsReceived = listActor;
                iReceived = true;
                System.out.println("I've finished the process LIST_OF_ALL_ACTORS");
        }else
            if (msg.equals("LIST_OF_ALL_DIRECTORS")) {
                // output.writeObject(messageToTransfer);
                List<Director> listDirector = (List<Director>) input.readObject();
                System.out.println("LIST OF directors:== " + Arrays.asList(listDirector));
                listOfDirectorsReceived = listDirector;
                iReceived = true;
                System.out.println("I've finished the process LIST_OF_ALL_DIRECTORS");
        }

    }
    public void sendMessageToServer(String message) throws IOException {
        output.writeObject(message);
    }

    public void updateMovieSendToServer(Movie movie, String messageToTransfer/*MessageTransfer messageTransfer*/) throws IOException {

       // this.messageToTransfer = messageTransfer;
       // this.objectToTransfer = movie;
        output.writeObject(messageToTransfer);
       // Movie movie = (Movie) objectToTransfer;
       output.writeObject(movie);
        //System.out.println("\n\n\n\n\nsuper mere::::::" + this.toString());
        //output.writeObject("NOTIF");

    }

    public void addMovieSendToServer(Movie movie, String messageToTransfer) throws IOException {
       // this.messageToTransfer = messageTransfer;
      // this.objectToTransfer = movie;
        output.writeObject(messageToTransfer);
        // Movie movie = (Movie) objectToTransfer;
        output.writeObject(movie);
    }

    public void removeMovieSendToServer(int id, String messageToTransfer) throws IOException {
       // this.messageToTransfer = messageTransfer;
       // this.objectToTransfer = id;
        output.writeObject(messageToTransfer);
        // Movie movie = (Movie) objectToTransfer;
        output.writeObject(id);
    }

    public synchronized void getMovieSendToServer(int id, String messageToTransfer) throws IOException, InterruptedException, ClassNotFoundException {
      //  this.messageToTransfer = messageTransfer;
       // this.objectToTransfer = id;

        output.writeObject(messageToTransfer);
        output.writeObject(id);
        Thread.sleep(1000);
        checkReceive();
    }

    public synchronized void getAllMoviesSendToServer(String messageToTransfer) throws IOException, InterruptedException, ClassNotFoundException {
        //  this.messageToTransfer = messageTransfer;
        // this.objectToTransfer = id;
        output.writeObject(messageToTransfer);
       // output.writeObject(id);
        Thread.sleep(1000);
        checkReceive();
    }


    public synchronized void getMovieByNameSendToServer(String name, String messageToTransfer) throws IOException, InterruptedException, ClassNotFoundException {
        output.writeObject(messageToTransfer);
        output.writeObject(name);
        Thread.sleep(1000);
        checkReceive();
    }

    public synchronized void addUserSendToServer(User user, String messageToTransfer) throws IOException {
        output.writeObject(messageToTransfer);
        output.writeObject(user);
    }

    public synchronized void updateUserSendToServer(User user, String messageToTransfer) throws IOException, InterruptedException, ClassNotFoundException {
        output.writeObject(messageToTransfer);
        output.writeObject(user);
        //Thread.sleep(1000);
       // checkReceive();
    }

    public synchronized void removeUserSendToServer(String id, String messageToTransfer) throws IOException {
        output.writeObject(messageToTransfer);
        output.writeObject(id);
    }

    public synchronized void getUserSendToServer(String id, String messageToTransfer) throws IOException, ClassNotFoundException, InterruptedException {
        output.writeObject(messageToTransfer);
        output.writeObject(id);
        Thread.sleep(1000);
        checkReceive();
    }


    public synchronized void getAllUsersSendToServer( String messageToTransfer) throws IOException, InterruptedException, ClassNotFoundException {
        output.writeObject(messageToTransfer);
        // output.writeObject(id);
        Thread.sleep(1000);
        checkReceive();
    }


    public synchronized void getAllActorsSendToServer(String messageToTransfer) throws InterruptedException, IOException, ClassNotFoundException {
        output.writeObject(messageToTransfer);
        // output.writeObject(id);
        Thread.sleep(1000);
        checkReceive();
    }

    public synchronized void getAllDirectorsSendToServer(String messageToTransfer) throws IOException, InterruptedException, ClassNotFoundException {
        output.writeObject(messageToTransfer);
        // output.writeObject(id);
        Thread.sleep(1000);
        checkReceive();
    }

    @Override
    public void update() {
        JOptionPane.showMessageDialog(null, "Modificari status filme" /*la filmul: " + movieName*/, "MESSAGE", JOptionPane.INFORMATION_MESSAGE);

        System.out.println("\n\n*********************************************************************");
        System.out.println("*********************************************************************");
        System.out.println("**********THE STATUS OF SOME MOVIES HAS CHANGED**********************");
        System.out.println("*********************************************************************");
        System.out.println("*********************************************************************\n\n");
    }

/*
    public void sendClient() throws IOException {
        output.writeObject("CLIENT");
        output.writeObject(this);
    }
*/
}
