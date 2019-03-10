import client.ClientConnection;
import client.bll.repository.MovieRepositoryI;
import client.bll.repository.UserRepositoryI;
import client.controller.ControllerAdmin;
import client.model.entity.Actor;
import client.model.entity.Movie;
import client.model.entity.User;
import client.view.gui.AdministratorController;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.*;

public class TestClassMessageRequest {
    //private MovieServiceI movieServiceI;
    private MovieRepositoryI movieRepositoryI;
    //private UserServiceI userServiceI;
    private UserRepositoryI userRepositoryI;

    private AdministratorController administratorController;
    private AdministratorController administratorController2;
    private Movie movie;// = new Movie();
    private User user;

    private ControllerAdmin controllerAdmin;
    private ControllerAdmin controllerAdmin2;

    private List<Movie> movies = new ArrayList<Movie>();
    private List<Actor> actors = new ArrayList<Actor>();
    private List<User> users = new ArrayList<User>();
    private ClientConnection clientConnection;
    private Socket socket;
    private ObjectOutputStream output;
    private ObjectInputStream input;

    @Before
    public void setUp() throws IOException {
        administratorController = mock(AdministratorController.class);
        administratorController2 = mock(AdministratorController.class);

        //movieServiceI = mock(MovieService.class);
        movieRepositoryI = mock(MovieRepositoryI.class);
        //userServiceI  = mock(UserService.class);
        userRepositoryI = mock(UserRepositoryI.class);

        movie = mock(Movie.class);
        user = mock(User.class);

        movies = getMovieList();
        actors = getActorList();
        users = getUserList();

        controllerAdmin  = new ControllerAdmin(administratorController, movieRepositoryI);
        controllerAdmin2 = new ControllerAdmin(administratorController2, userRepositoryI);
       // clientConnection = mock(ClientConnection.class);
        socket = mock(Socket.class);
        input = mock(ObjectInputStream.class);
        output = mock(ObjectOutputStream.class);
        //clientConnection = new ClientConnection(socket,input,output);
        clientConnection = mock(ClientConnection.class);
    }

    @Test
    public void testAddMovie() throws IOException {
        Movie movie = new Movie("Twin Peaks","Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed");

       doNothing().when(clientConnection).addMovieSendToServer(isA(Movie.class), isA(String.class));
       clientConnection.addMovieSendToServer(movie, "ADD_MOVIE");
       verify(clientConnection,times(1)).addMovieSendToServer(movie,"ADD_MOVIE");
    }

    @Test
    public void testUpdateMovie() throws IOException {
        Movie movie = new Movie("Twin Peaks","Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed");

        doNothing().when(clientConnection).updateMovieSendToServer(isA(Movie.class), isA(String.class));
        clientConnection.updateMovieSendToServer(movie, "UPDATE_MOVIE");
        verify(clientConnection,times(1)).updateMovieSendToServer(movie,"UPDATE_MOVIE");
    }

    @Test
    public void testDeleteMovie() throws IOException {
        Movie movie = new Movie(1,"Twin Peaks","Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed");

        doNothing().when(clientConnection).removeMovieSendToServer(isA(Integer.class), isA(String.class));
        clientConnection.removeMovieSendToServer(movie.getId(), "REMOVE_MOVIE");
        verify(clientConnection,times(1)).removeMovieSendToServer(movie.getId(),"REMOVE_MOVIE");
    }

    @Test
    public void testReadMovie() throws IOException, ClassNotFoundException, InterruptedException {
        Movie movie = new Movie(1,"Twin Peaks","Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed");

        doNothing().when(clientConnection).getMovieSendToServer(isA(Integer.class), isA(String.class));
        clientConnection.getMovieSendToServer(movie.getId(), "READ_MOVIE");
        verify(clientConnection,times(1)).getMovieSendToServer(movie.getId(),"READ_MOVIE");
    }

    @Test
    public void testGetAllMovies() throws IOException, ClassNotFoundException, InterruptedException {
        doNothing().when(clientConnection).getAllMoviesSendToServer(isA(String.class));
        clientConnection.getAllMoviesSendToServer( "GET_ALL_MOVIES");
        verify(clientConnection,times(1)).getAllMoviesSendToServer("GET_ALL_MOVIES");
    }

    @Test
    public void testReadMovieByName() throws IOException, ClassNotFoundException, InterruptedException {
        Movie movie = new Movie(1,"Twin Peaks","Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed");

        doNothing().when(clientConnection).getMovieByNameSendToServer(isA(String.class), isA(String.class));
        clientConnection.getMovieByNameSendToServer(movie.getName(), "READ_MOVIE_BY_NAME");
        verify(clientConnection,times(1)).getMovieByNameSendToServer(movie.getName(),"READ_MOVIE_BY_NAME");
    }















/*


    @Test
    public void testAddMovie() throws IOException {
        User user = new User("name","password",0,
                "user","aaaa@yahoo.com", 35);

        doNothing().when(clientConnection).addMovieSendToServer(isA(Movie.class), isA(String.class));
        clientConnection.addMovieSendToServer(movie, "ADD_MOVIE");
        verify(clientConnection,times(1)).addMovieSendToServer(movie,"ADD_MOVIE");
    }

    @Test
    public void testUpdateMovie() throws IOException {
        User user = new User("name","password",0,
                "user","aaaa@yahoo.com", 35);

        doNothing().when(clientConnection).updateMovieSendToServer(isA(Movie.class), isA(String.class));
        clientConnection.updateMovieSendToServer(movie, "UPDATE_MOVIE");
        verify(clientConnection,times(1)).updateMovieSendToServer(movie,"UPDATE_MOVIE");
    }

    @Test
    public void testDeleteMovie() throws IOException {
        User user = new User("name","password",0,
                "user","aaaa@yahoo.com", 35);

        doNothing().when(clientConnection).removeMovieSendToServer(isA(Integer.class), isA(String.class));
        clientConnection.removeMovieSendToServer(movie.getId(), "REMOVE_MOVIE");
        verify(clientConnection,times(1)).removeMovieSendToServer(movie.getId(),"REMOVE_MOVIE");
    }

    @Test
    public void testReadMovie() throws IOException, ClassNotFoundException, InterruptedException {
        User user = new User("name","password",0,
                "user","aaaa@yahoo.com", 35);;

        doNothing().when(clientConnection).getMovieSendToServer(isA(Integer.class), isA(String.class));
        clientConnection.getMovieSendToServer(movie.getId(), "READ_MOVIE");
        verify(clientConnection,times(1)).getMovieSendToServer(movie.getId(),"READ_MOVIE");
    }

    @Test
    public void testGetAllMovies() throws IOException, ClassNotFoundException, InterruptedException {
        doNothing().when(clientConnection).getAllMoviesSendToServer(isA(String.class));
        clientConnection.getAllMoviesSendToServer( "GET_ALL_MOVIES");
        verify(clientConnection,times(1)).getAllMoviesSendToServer("GET_ALL_MOVIES");
    }

    @Test
    public void testReadMovieByName() throws IOException, ClassNotFoundException, InterruptedException {
        User user = new User("name","password",0,
                "user","aaaa@yahoo.com", 35);

        doNothing().when(clientConnection).getMovieByNameSendToServer(isA(String.class), isA(String.class));
        clientConnection.getMovieByNameSendToServer(movie.getName(), "READ_MOVIE_BY_NAME");
        verify(clientConnection,times(1)).getMovieByNameSendToServer(movie.getName(),"READ_MOVIE_BY_NAME");
    }
    */

    private List<Actor> getActorList(){
        Actor actor = new Actor();
        actor.setId(1);
        actor.setName("name");
        actor.setInfo("best comedy");

        Actor actor1 = new Actor();
        actor1.setId(2);
        actor1.setName("namee");
        actor1.setInfo("best comedyy");


        actors.add(actor);
        actors.add(actor1);
        return actors;
    }

    private List<User> getUserList(){
        User user = new User();
        user.setId("user");
        user.setName("name");
        user.setPassword("passs");

        User user1 = new User();
        user.setId("user2");
        user.setName("namee");
        user.setPassword("pass");


        users.add(user);
        users.add(user1);
        return users;
    }
    private List<Movie> getMovieList(){
        Movie movie = new Movie();
        movie.setId(1);
        movie.setName("name");
        movie.setGenre("comedy");
        movie.setRating(10);
        movie.setTitle("good info");

        Movie movie1 = new Movie();
        movie1.setId(2);
        movie1.setName("namee");
        movie1.setGenre("comedyy");
        movie1.setRating(10);
        movie1.setTitle("good info0");

        movies.add(movie);
        movies.add(movie1);
        return movies;
    }


}
