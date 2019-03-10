import client.bll.repository.MovieRepositoryI;
import client.bll.repository.UserRepositoryI;
import client.controller.ControllerAdmin;
import client.model.entity.Actor;
import client.model.entity.Movie;
import client.model.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import client.view.gui.AdministratorController;
import client.bll.services.MovieService;
import client.bll.services.MovieServiceI;
import client.bll.services.UserService;
import client.bll.services.UserServiceI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class TestControllerAdmin {
    //private MovieServiceI movieServiceI;
    private MovieRepositoryI movieRepositoryI;
    //private UserServiceI userServiceI;
    private UserRepositoryI userRepositoryI;

    private AdministratorController administratorController;
    private AdministratorController administratorController2;
    private  Movie movie;// = new Movie();
    private  User user;

    private ControllerAdmin controllerAdmin;
    private ControllerAdmin controllerAdmin2;

    private List<Movie> movies = new ArrayList<Movie>();
    private List<Actor> actors = new ArrayList<Actor>();
    private List<User> users = new ArrayList<User>();

    @Before
    public void setUp() {
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

       // controllerAdmin  = new ControllerAdmin(administratorController, movieRepositoryI);
        controllerAdmin2 = new ControllerAdmin(administratorController2, userRepositoryI);
        controllerAdmin = mock(ControllerAdmin.class);
    }



    @Test
    public void testAddMovie(){
        Movie movie = new Movie("Twin Peaks","Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed");

        when(controllerAdmin.addMovie(movie.getName(),movie.getGenre(), movie.getTitle(),  movie.getRating(),movie.getStatus())).thenReturn(movie);
        Assert.assertEquals(controllerAdmin.addMovie("Twin Peaks",
                "Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed"), movie);
    }

    @Test
    public void testUpdateMovie() throws InterruptedException {
        Movie movie = new Movie(1,"Twin Peaks","Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed");

        when(controllerAdmin.updateMovie(movie.getId(),movie.getName(),movie.getGenre(), movie.getTitle(),  movie.getRating(),movie.getStatus())).thenReturn(movie);
        Assert.assertEquals(controllerAdmin.updateMovie(1,"Twin Peaks",
                "Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed"), movie);
    }
    @Test
    public void testUpdateMovie2() throws InterruptedException {
        Movie movie = mock(Movie.class);

        when(movie.getGenre()).thenReturn("comedy");
        when(movie.getRating()).thenReturn(9);
        when(movie.getTitle()).thenReturn("good movie");

        controllerAdmin.updateMovie(movie.getId(),movie.getName(),movie.getGenre(), movie.getTitle(),  movie.getRating(),movie.getStatus());
        verify(controllerAdmin,atLeastOnce()).updateMovie(movie.getId(),movie.getName(),movie.getGenre(), movie.getTitle(),  movie.getRating(),movie.getStatus());
    }



    @Test
    public void testUpdateMovieJavaFx() throws InterruptedException {
        //Movie movie =new Movie();
        when(movie.getId()).thenReturn(1);
        when(movie.getName()).thenReturn("name");
        when(movie.getGenre()).thenReturn("comedy");
        when(movie.getRating()).thenReturn(10);
        when(movie.getTitle()).thenReturn("good movie");
       // administratorController.updateMovieButtonClicked();
        controllerAdmin.updateMovie(1,"name","comedy","good movie",10,"delayed");
        verify(controllerAdmin,atLeastOnce()).updateMovie(1,"name","comedy","good movie",10,"delayed");

    }

    @Test
    public void testDeleteMovie(){
        doNothing().when(controllerAdmin).deleteMovie(anyInt());
        controllerAdmin.readMovie(anyInt());
        verify(controllerAdmin, atLeastOnce()).readMovie(anyInt());
    }

    @Test
    public void testReadMovie(){
        when(controllerAdmin.readMovie(anyInt())).thenReturn(movie);
        Assert.assertEquals(controllerAdmin.readMovie(movie.getId()),movie);
    }

    @Test
    public void testFindMovieId(){
        when(controllerAdmin.readMovie(anyInt())).thenReturn(movie);
        Assert.assertEquals(controllerAdmin.readMovie(1), movie);
    }
    @Test
    public void testAddActorToMovie(){
        Movie movie = new Movie(1,"Twin Peaks","Mystery",
                "An idiosyncratic FBI agent investigates the murder of a young " +
                        "woman in the even more idiosyncratic town of Twin Peaks.",
                10,"delayed");
        Actor actor = new Actor(3,"Tom Hanks","good actor");

        actors.clear();
        actors.add(actor);
        movie.setActors(actors);
        //verify(movieServiceI).addMovie(movie);
        when(controllerAdmin.addMovie(movie.getName(),movie.getGenre(), movie.getTitle(), movie.getRating(),movie.getStatus())).thenReturn(movie);
        when(controllerAdmin.addActorToMovie(1,3)).thenReturn(movie);
        Assert.assertEquals(controllerAdmin.addActorToMovie(movie.getId(),actor.getId()), movie);
    }


    @Test
    public void testDeleteActorToMovie() {
        doNothing().when(controllerAdmin).deleteActorFromMovie(1, 3);
        controllerAdmin.deleteActorFromMovie(1,3);
        verify(controllerAdmin, atLeastOnce()).deleteActorFromMovie(1,3);
    }


    @Test
    public void testFindAllMovies() throws InterruptedException, IOException, ClassNotFoundException {
        when(movieRepositoryI.listMovie()).thenReturn(movies);
        Assert.assertEquals(movieRepositoryI.listMovie(), movies);
    }



    @Test
    public void testFindMovieName() throws InterruptedException, IOException, ClassNotFoundException {
        when(movieRepositoryI.getMovieByName(anyString())).thenReturn(movie);
        Assert.assertEquals(movieRepositoryI.getMovieByName(anyString()), movie);
    }




    @Test
    public void testAddUser() throws IOException {
        User user = new User("name","password",1,
                "user22","aaaa@yahoo.com", 33);

       when(controllerAdmin.addUser(user.getName(),user.getPassword(), user.getTypeAccount(),  user.getId(),user.getEmail(),
              user.getAge())).thenReturn(user);
        Assert.assertEquals(controllerAdmin.addUser("name","password",1,
                "user22","aaaa@yahoo.com", 33), user);
    }

    @Test
    public void testUpdateUser() throws IOException, ClassNotFoundException, InterruptedException {
        User user = new User("name","password",1,
                "user22","aaaa@yahoo.com", 33);

        when(controllerAdmin.updateUser(user.getName(),user.getPassword(), user.getTypeAccount(),  user.getId(),user.getEmail(),
                user.getAge())).thenReturn(user);
        Assert.assertEquals(controllerAdmin.updateUser("name","password",1,
                "user22","aaaa@yahoo.com", 33), user);
    }

    @Test
    public void testDeleteUser() throws IOException {
        ControllerAdmin controllerAdmin = mock(ControllerAdmin.class);
        doNothing().when(userRepositoryI).removeUser(anyString());
        controllerAdmin.deleteUser(anyString());
        verify(controllerAdmin, atLeastOnce()).deleteUser(anyString());
    }

    @Test
    public void testReadUser() throws InterruptedException, IOException, ClassNotFoundException {
        User user = new User("name","password",0,
            "user","aaaa@yahoo.com", 35);
        when(userRepositoryI.getUserById("user")).thenReturn(user);
        Assert.assertEquals(controllerAdmin2.readUser(user.getId()),user);
    }

    @Test
    public void testFindAllUsers() throws InterruptedException, IOException, ClassNotFoundException {
        when(userRepositoryI.listUser()).thenReturn(users);
        Assert.assertEquals(userRepositoryI.listUser(), users);
    }

    @Test
    public void testFindUserId() throws InterruptedException, IOException, ClassNotFoundException {
        when(userRepositoryI.getUserById(anyString())).thenReturn(user);
        Assert.assertEquals(controllerAdmin2.readUser(anyString()), user);
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




}
