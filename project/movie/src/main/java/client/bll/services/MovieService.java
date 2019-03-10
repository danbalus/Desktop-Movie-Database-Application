package client.bll.services;

import client.ClientConnection;
import client.model.constants.SingletonClass;
import server.dao.MovieDAO;
import server.dao.MovieDAOI;
import client.model.entity.EntityApp;
import client.model.entity.Movie;
import client.model.validators.IdValidators;
import client.model.validators.NumeValidator;
import client.model.validators.RateMovieValidator;
import client.model.validators.Validators;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static message.MessageTransfer.*;

public class MovieService implements MovieServiceI {
    private MovieDAOI movieDAOI;
    private List<Validators<EntityApp>> validators;
    private SessionFactory sessionFactory;
    private Session session;
    private ClientConnection clientConnection;
    private  int  count = 0;
    private SingletonClass singletonClass;

    public MovieService(){
        validators = new ArrayList<Validators<EntityApp>>();
        validators.add(new IdValidators());
        validators.add(new NumeValidator());
        movieDAOI = new MovieDAO();
        singletonClass = SingletonClass.getInstance();

        clientConnection = singletonClass.clientConnection;
    }


    //@Transactional
    public  synchronized Movie addMovie(Movie movie) {
        for (Validators<EntityApp> v : validators) {
            v.validate(movie);
        }

        RateMovieValidator rateMovieValidator = new RateMovieValidator();
        rateMovieValidator.validate(movie);


       // this.movieDAOI.addMovie(movie);
        try {
            //clientConnection.sendClient();
            clientConnection.addMovieSendToServer(movie, "ADD_MOVIE");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movie;
    }

    //@Transactional
    public synchronized Movie updateMovie(Movie movie) {
        count ++;
        System.out.println("\n\n\nCOUNT:    "+ count);
        for (Validators<EntityApp> v : validators) {
            v.validate(movie);
        }
        RateMovieValidator rateMovieValidator = new RateMovieValidator();
        rateMovieValidator.validate(movie);

        try {
            clientConnection.updateMovieSendToServer(movie, "UPDATE_MOVIE");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //this.movieDAOI.updateMovie(movie);

        return movie;
    }

    //@Transactional
    public List<Movie> listMovie() throws InterruptedException, IOException, ClassNotFoundException {
        clientConnection.getAllMoviesSendToServer("GET_ALL_MOVIES");
        List<Movie> movieList = clientConnection.getListOfMoviesReceived();
        return movieList;//this.movieDAOI.listMovie();
    }



    //@Transactional
    public synchronized Movie getMovieById(int id) {
        Movie movie = null;
       // try {
        try {
           // clientConnection.resetIReceived();
            clientConnection.getMovieSendToServer(id, "READ_MOVIE");

            movie = (Movie)clientConnection.getObjectReceivedMovie();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //movie = this.movieDAOI.getMovieById(id);
            if (movie == null) {
                 throw new NoSuchElementException("Filmul cu id-ul =" + id + " nu a fost gasit!");
            }
       // }catch(ObjectNotFoundException ex){System.out.println("\n\n**($^$%^$&&$^&");}
        //finally{
            return movie;
       // }
    }
    public Movie getMovieByName(String name) throws InterruptedException, IOException, ClassNotFoundException {
        Movie movie = null;
        // try {
        clientConnection.getMovieByNameSendToServer(name, "READ_MOVIE_BY_NAME");
        movie = (Movie)clientConnection.getObjectReceivedMovie();
       // movie = this.movieDAOI.getMovieByName(name);
        if (movie == null) {
            throw new NoSuchElementException("Filmul cu numele =" + name + " nu a fost gasit!");
        }
        // }catch(ObjectNotFoundException ex){System.out.println("\n\n**($^$%^$&&$^&");}
        //finally{
        return movie;
        // }
    }

    //@Transactional
    public void removeMovie(int id) {
        //this.movieDAOI.removeMovie(id);
        try {
            clientConnection.removeMovieSendToServer(id, "REMOVE_MOVIE");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
