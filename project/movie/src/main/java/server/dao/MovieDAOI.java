package server.dao;

import client.model.entity.Movie;

import java.util.List;

public interface MovieDAOI   {
   // public SessionFactory getSessionFactory();
   // public void setCurrentSession(SessionFactory sessionFactory);
    public Movie addMovie(Movie actor);
    public void updateMovie(Movie actor);
    public List<Movie> listMovie();
    public Movie getMovieById(int id);
    public void removeMovie(int id);

    Movie getMovieByName(String name);
}
