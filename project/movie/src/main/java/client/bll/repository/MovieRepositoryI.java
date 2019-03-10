package client.bll.repository;

import client.model.entity.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieRepositoryI {

   // public void setActorDAOI(ActorDAOI actorDAOI);
    //public void setDirectorDAOI(DirectorDAOI directorDAOI);
    //public void setMovieDAOI(MovieDAOI movieDAOI);


    //public void setCurrentSession(SessionFactory sessionFactory);

    public List<Movie> searchByRating(String data) throws InterruptedException, IOException, ClassNotFoundException;
    public List<Movie> searchByGenre(String data) throws InterruptedException, IOException, ClassNotFoundException;
    public List<Movie> searchByActors(String data) throws InterruptedException, IOException, ClassNotFoundException;
    public List<Movie> searchByDirectors(String data) throws InterruptedException, IOException, ClassNotFoundException;
    //criteria = ration, genre,actors, directors


    public Movie addMovie(Movie movie);
    public Movie updateMovie(Movie movie);
    public List<Movie> listMovie() throws InterruptedException, IOException, ClassNotFoundException;
    public Movie getMovieById(int id);
    public void removeMovie(int id);
    public Movie getMovieByName(String name) throws InterruptedException, IOException, ClassNotFoundException;
}
