package client.model.feature;

import client.model.entity.Movie;

import java.util.ArrayList;

public interface Raport {
    public void start( ArrayList<Movie> movieList);//conflict list with iText =>arraylist
}
