package client.bll.services;

import client.model.entity.Movie;

import java.io.IOException;
import java.util.List;

public interface MovieServiceI {
   // public void setMovieDAOI(MovieDAOI movieDAOI);
    //public void setCurrentSession(SessionFactory sessionFactory);
    public Movie addMovie(Movie movie);
    public Movie updateMovie(Movie movie);
    public List<Movie> listMovie() throws InterruptedException, IOException, ClassNotFoundException;
    public Movie getMovieById(int id);
    public void removeMovie(int id);
    public Movie getMovieByName(String name) throws InterruptedException, IOException, ClassNotFoundException;
}
