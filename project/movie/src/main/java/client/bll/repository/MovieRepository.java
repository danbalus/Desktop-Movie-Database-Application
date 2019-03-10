package client.bll.repository;

import client.bll.services.*;
import client.model.entity.Actor;
import client.model.entity.Director;
import client.model.entity.Movie;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovieRepository implements MovieRepositoryI {

    private ActorServiceI actorServiceI;
    private DirectorServiceI directorServiceI;
    private MovieServiceI movieServiceI;

    public MovieRepository() {
        actorServiceI = new ActorService();
        directorServiceI = new DirectorService();
        movieServiceI = new MovieService();
    }


    @Override
    public List<Movie> searchByRating(String data) throws InterruptedException, IOException, ClassNotFoundException {
        List<Movie> movieList = movieServiceI.listMovie();
        for (Iterator<Movie> iterator = movieList.iterator(); iterator.hasNext(); ) {
            Movie movie = iterator.next();
            String rating = Integer.toString(movie.getRating());
            if (!rating.equalsIgnoreCase(data)) {
                iterator.remove();
            }
        }
        if (movieList.isEmpty()) {
            throw new NullPointerException("Nu exista filme cu acest rating");
        }

        return movieList;
    }

    @Override
    public List<Movie> searchByGenre(String data) throws InterruptedException, IOException, ClassNotFoundException {
        List<Movie> movieList = movieServiceI.listMovie();
        for (Iterator<Movie> iterator = movieList.iterator(); iterator.hasNext(); ) {
            Movie movie = iterator.next();
            String rating = movie.getGenre();
            if (!rating.equalsIgnoreCase(data)) {
                iterator.remove();
            }
        }
        if (movieList.isEmpty()) {
            throw new NullPointerException("Nu exista filme cu acest gen");
        }

        return movieList;
    }


    @Override
    public  List<Movie> searchByActors(String data) throws InterruptedException, IOException, ClassNotFoundException {
        List<Actor> actorList = actorServiceI.listActor();

        List<Movie> movieList = new ArrayList<>();
        for (Iterator<Actor> iterator = actorList.iterator(); iterator.hasNext(); ) {
            System.out.println(actorList);
            Actor actor = iterator.next();
            String nume = actor.getName();
            if (nume.equalsIgnoreCase(data)) {
                List<Movie> list = new ArrayList<>();
                list= actor.getMovies();
                //System.out.println("\n\n\n\n\n\n\n^^^^^^^^^^^^^^^^<>^^^^^^^^^^^" + list);
                if (!list.isEmpty()) {
                    movieList.addAll(list);
                }
            }
        }
        if (movieList.isEmpty()) {
            throw new NullPointerException("Nu exista filme cu acest actor");
        }

        return movieList;
    }

    @Override
    public List<Movie> searchByDirectors(String data) throws InterruptedException, IOException, ClassNotFoundException {

        List<Director> directorList = directorServiceI.listDirector();
        List<Movie> movieList = new ArrayList<>();
        for (Iterator<Director> iterator = directorList.iterator(); iterator.hasNext(); ) {
            Director director = iterator.next();
            String nume = director.getName();
            if (nume.equalsIgnoreCase(data)) {
                movieList.addAll(director.getMovies());
            }
        }
        return movieList;

    }

    @Override
    public Movie addMovie(Movie movie) {
        movieServiceI.addMovie(movie);
        return movie;//for testing
    }

    @Override
    public Movie updateMovie(Movie movie)   {
        movieServiceI.updateMovie(movie);
        return movie;//for testing
    }

    @Override
    public List<Movie> listMovie() throws InterruptedException, IOException, ClassNotFoundException {
        List<Movie> movieList = movieServiceI.listMovie();
        return movieList;//for testing
    }

    @Override
    public Movie getMovieById(int id) {
        Movie movie = movieServiceI.getMovieById(id);
        return movie;//for testing
    }

    @Override
    public void removeMovie(int id) {
       movieServiceI.removeMovie(id);


    }

    @Override
    public Movie getMovieByName(String name) throws InterruptedException, IOException, ClassNotFoundException {
        Movie movie = movieServiceI.getMovieByName(name);
        return movie;
    }

    //TODO:    AICI AR TREBUI SA FAC SEARCH UL, FOLOSING DAO
    //TODO:
    //TODO:
}
