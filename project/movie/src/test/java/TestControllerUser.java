import client.controller.ControllerUser;
import client.model.config.ConfigurationFactory;
import client.model.entity.Actor;
import client.model.entity.Director;
import client.model.entity.Movie;
import client.bll.repository.MovieRepositoryI;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import client.view.gui.RegularUserControllerI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class TestControllerUser {
    private MovieRepositoryI movieRepositoryI;
    private ControllerUser controllerUser;

    private List<Movie> movies = new ArrayList<Movie>();
    private List<Actor> actors = new ArrayList<Actor>();
    private List<Director> directors = new ArrayList<Director>();

    @Before
    public void setUp() {
        //movieRepositoryI = mock(MovieRepositoryI.class);

        movies = getMovieList();
        actors = getActorList();


        //controllerUser  = new ControllerUser(regularUserControllerI, movieRepositoryI);
        controllerUser = mock(ControllerUser.class);
    }



    @Test
    public void testSearchByActor() throws InterruptedException, IOException, ClassNotFoundException {
        when(controllerUser.searchByActor("name")).thenReturn(movies);
        Assert.assertEquals(controllerUser.searchByActor("name"), movies);
    }

    @Test
    public void testSearchByDirectors() throws InterruptedException, IOException, ClassNotFoundException {
        when(controllerUser.searchByDirector("name")).thenReturn(movies);
        Assert.assertEquals(controllerUser.searchByDirector("name"), movies);
    }

    @Test
    public void testSearchByRate() throws InterruptedException, IOException, ClassNotFoundException {
        when(controllerUser.searchByRate("name")).thenReturn(movies);
        Assert.assertEquals(controllerUser.searchByRate("name"), movies);
    }

    @Test
    public void testSearchByGenre() throws InterruptedException, IOException, ClassNotFoundException {
        when(controllerUser.searchByGenre("name")).thenReturn(movies);
        Assert.assertEquals(controllerUser.searchByGenre("name"), movies);
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



}
