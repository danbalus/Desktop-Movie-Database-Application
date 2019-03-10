package client.controller;

import client.bll.repository.*;
import client.model.entity.Review;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import client.model.constants.SingletonClass;
import client.model.entity.Movie;
import client.model.entity.User;
import client.bll.services.MovieServiceI;
import client.bll.services.UserService;
import client.bll.services.UserServiceI;
import client.view.gui.RegularUserControllerI;
import org.hibernate.HibernateException;
import org.springframework.util.CollectionUtils;

import javax.swing.*;
import java.io.IOException;
import java.util.*;

public class ControllerUser {
    private final RegularUserControllerI regularUserControllerI;
    private boolean genre, director, actor, rate;

    private MovieRepositoryI movieRepositoryI;
    private UserRepositoryI userRepositoryI ;
    private ReviewRepositoryI reviewRepositoryI;

   // private UserServiceI userServiceI;
    //private MovieServiceI movieServiceI ;

    public ControllerUser(RegularUserControllerI regularUserControllerI/* , IAccountProvider accountProvider */) {
        this.regularUserControllerI = regularUserControllerI;
        movieRepositoryI = new MovieRepository();
        userRepositoryI = new UserRepository();
        reviewRepositoryI = new ReviewRepository();
        //userServiceI = new UserService();

    }
    public ControllerUser(RegularUserControllerI regularUserControllerI,    MovieRepositoryI movieRepositoryI) {
        this.regularUserControllerI = regularUserControllerI;
        this.movieRepositoryI = movieRepositoryI;
    }

    public void chooseSearchMethode(boolean genre, boolean director, boolean actor, boolean rating) {

        this.genre =genre;
        this.director = director;
        this.actor = actor;
        this.rate = rating;

        if (genre){
            System.out.println("genre");
            searchByGenre(regularUserControllerI.getSearchMovieTextField());
        }
        if(director){
            System.out.println("director");
            searchByDirector(regularUserControllerI.getSearchMovieTextField());
        }
        if(actor){
            System.out.println("actor");
            searchByActor(regularUserControllerI.getSearchMovieTextField());
        }
        if(rating){
            System.out.println("rate");
            searchByRate(regularUserControllerI.getSearchMovieTextField());
        }
    }

    public  List<Movie> searchByRate(String whatToFind) {
        List<Movie> movieList=null;
        try {
            movieList = movieRepositoryI.searchByRating(whatToFind);
            System.out.println(Arrays.asList(movieList));
        }
        catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Nu exista in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau userul este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }//finally {
            return movieList;
       // }

    }


    public  List<Movie> searchByGenre(String whatToFind ) {
        List<Movie> movieList = null;
        try {
            movieList = movieRepositoryI.searchByGenre(whatToFind);
            System.out.println(Arrays.asList(movieList));

        }
        catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Nu exista in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau userul este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        //finally {
            return movieList;
        //}
    }

    public  List<Movie> searchByDirector(String whatToFind) {
        List<Movie> movieList = null;
        try {
            movieList =new ArrayList<>();
            movieList = movieRepositoryI.searchByDirectors(whatToFind);
        }
        catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Nu exista in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau userul este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
       // finally {
            return movieList;
        //}
    }


    public List<Movie> searchByActor(String whatToFind) {
        List<Movie> movieList = null;
        try {
            movieList = movieRepositoryI.searchByActors(whatToFind);
            System.out.println(Arrays.asList(movieList));
        }
        catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "Nu exista in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau userul este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
       }//finally {
            return movieList;
       // }

    }

    public void rateMovie() {
        try {
            String name = regularUserControllerI.getRateNameMovieTextField();
            int rate = Integer.parseInt(regularUserControllerI.getRateRateMovieTextField());
            if(rate < 1 || rate > 10){
                throw new IllegalArgumentException("Nota trbeuie sa fie intre 1 si 10");
            }

            SingletonClass singletonClass = SingletonClass.getInstance();

            String idLogeerUser = singletonClass.id;//constants.getId();
            System.out.println("\n\n*********************" + idLogeerUser + "\n\n");
            userRepositoryI.rateMovie(idLogeerUser, name, rate);

        }catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Nota filmului trebuie sa fie intre 1 si 10", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Numele filmului Nu exista in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau userul este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void showAllSearch() throws InterruptedException, IOException, ClassNotFoundException {
        String whatToFind = regularUserControllerI.getSearchMovieTextField();

        ObservableList<Movie> data = FXCollections.observableArrayList();


        List<Movie> listMovie = new ArrayList<>();
        if(actor){
            System.out.println("actor show all search");
            listMovie  = movieRepositoryI.searchByActors(whatToFind);
        }
        if(director){
            System.out.println("director show all search");
            listMovie  = movieRepositoryI.searchByDirectors(whatToFind);
        }
        if (genre){
            System.out.println("genre show all search");
            listMovie  = movieRepositoryI.searchByGenre(whatToFind);
        }
        if(rate){
            System.out.println("rate show all search");
            listMovie  = movieRepositoryI.searchByRating(whatToFind);
        }


        System.out.println(Arrays.asList(listMovie));
        data.addAll(listMovie);

        regularUserControllerI.initSearch();
        regularUserControllerI.setDataSearchT(data);
    }
    /*public static boolean isNullOrEmpty( final Map< ?, ? > m ) {
        return m == null || m.isEmpty();
    }*/

    public void showAllRate() throws InterruptedException, IOException, ClassNotFoundException {
        ObservableList<Movie> data = FXCollections.observableArrayList();

        User user = new User();

        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");

        user = userRepositoryI.getUserById(idLogedUser);

        Map<Integer,Integer> ratings = user.getRatings();
        List<Movie> listMovie = new ArrayList<>();
        //ratings.put(99999,99999);
        if (!ratings.isEmpty()){
            //CollectionUtils.isEmpty()
            System.out.println("Controller: ratings are not empty");
            for (Map.Entry<Integer, Integer> pair : ratings.entrySet()) {
                int idMovie = pair.getKey();
                int rateMovie =  pair.getValue();
                String nameMovie = movieRepositoryI.getMovieById(idMovie).getName();
                Movie movie = new Movie(nameMovie, rateMovie);//smecherie, pun rate-ul userului la rate de la movie ca sa ma folosesc de aceeasi entitate :D :D
                listMovie.add(movie);
            }
        }


        System.out.println(Arrays.asList(listMovie));
        data.addAll(listMovie);

        regularUserControllerI.initRate();
        regularUserControllerI.setDataRateT(data);
    }


    public void addFavouriteMovie( String nameMovie) throws InterruptedException, IOException, ClassNotFoundException {
        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");

        try {

            User user = userRepositoryI.getUserById(idLogedUser);
            Movie movie = movieRepositoryI.getMovieByName(nameMovie);
            if (user == null) {
                throw new NullPointerException("Problema in demersul aplicatiei/ User deconectat");
            }
            List<Movie> favouriteMovies = user.getFavouriteMovieList();
            System.out.println("AICI");
            favouriteMovies.add(movie);
            System.out.println("AICI2");
            user.setFavouriteMovieList(favouriteMovies);
            System.out.println("AICI3");
            userRepositoryI.updateUser(user);
            System.out.println("AICI4");
        }catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "*Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }


    }

    public void removeFavouriteMovie(String nameMovie) throws InterruptedException, IOException, ClassNotFoundException {
        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");
        try {


        User user = userRepositoryI.getUserById(idLogedUser);
        Movie movie = movieRepositoryI.getMovieByName(nameMovie);

        if(user == null){
            throw new NullPointerException("Problema in demersul aplicatiei/ User deconectat");
        }

        List<Movie> favouriteMovies = user.getFavouriteMovieList();
        int idMovie = movie.getId();
        for (Iterator<Movie> iterator = favouriteMovies.iterator(); iterator.hasNext(); ) {
            Movie movie1 = iterator.next();
            if (movie1.getId() == (idMovie)) {
                iterator.remove();
                break;
            }
        }

       // favouriteMovies.remove(movie);
        user.setFavouriteMovieList(favouriteMovies);
        userRepositoryI.updateUser(user);
        }catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "*Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }



    public void showAllFavouriteMovies() throws InterruptedException, IOException, ClassNotFoundException {
        ObservableList<Movie> data = FXCollections.observableArrayList();

        User user = new User();

        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");

        user = userRepositoryI.getUserById(idLogedUser);

        List<Movie> favouritesList = user.getFavouriteMovieList();

        data.addAll(favouritesList);

        regularUserControllerI.initFavourite();
        regularUserControllerI.setDataFavouriteT(data);
    }

    public void addWhatchedMovie(String nameMovie) {
        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");

        try {

            User user = userRepositoryI.getUserById(idLogedUser);
            Movie movie = movieRepositoryI.getMovieByName(nameMovie);
            if (user == null) {
                throw new NullPointerException("Problema in demersul aplicatiei/ User deconectat");
            }
            List<Integer> whatchedMovies = user.getWatched();
            int movieId = movieRepositoryI.getMovieByName(nameMovie).getId();
            whatchedMovies.add(movieId);
            user.setWatched(whatchedMovies);
            userRepositoryI.updateUser(user);
        }catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "*Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void removeWhatchedMovie(String nameMovie) {
        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");
        try {


            User user = userRepositoryI.getUserById(idLogedUser);
            Movie movie = movieRepositoryI.getMovieByName(nameMovie);

            if(user == null){
                throw new NullPointerException("Problema in demersul aplicatiei/ User deconectat");
            }

            List<Integer> whatchedMovies = user.getWatched();
            int idMovieToDelete = movie.getId();
            for (Iterator<Integer> iterator = whatchedMovies.iterator(); iterator.hasNext(); ) {
                int  movieId = iterator.next();
                if (movieId == idMovieToDelete) {
                    iterator.remove();
                    break;
                }
            }

           //whatchedMovies.remove(idMovieToDelete);
            user.setWatched(whatchedMovies);
            userRepositoryI.updateUser(user);
        }catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "*Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void seeAllWhatchedMovies() throws InterruptedException, IOException, ClassNotFoundException {
        ObservableList<Movie> data = FXCollections.observableArrayList();

        User user = new User();

        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");

        user = userRepositoryI.getUserById(idLogedUser);

        List<Integer> idWatchedMovies = user.getWatched();
        Movie movie = null;
        List<Movie>  watchedMovies = new ArrayList<>();
        for (Integer idMovie: idWatchedMovies) {
            movie = movieRepositoryI.getMovieById(idMovie);
            if(movie != null){
                watchedMovies.add(movie);
            }
        }
        data.addAll(watchedMovies);

        regularUserControllerI.initWhatched();
        regularUserControllerI.setDataWhatchedT(data);
    }




    public void showAllSuggestionsMovie() {
        ObservableList<Movie> data = FXCollections.observableArrayList();

        try {

        User user = new User();
        SingletonClass singletonClass = SingletonClass.getInstance();
        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");
        user = userRepositoryI.getUserById(idLogedUser);


        List<Movie> allMovies = movieRepositoryI.listMovie();
        List<Integer> idWatchedMovies = user.getWatched();
        Movie movie = null;
        List<Movie>  watchedMovies = new ArrayList<>();
        for (Integer idMovie: idWatchedMovies) {
            movie = movieRepositoryI.getMovieById(idMovie);
            if(movie != null){
                watchedMovies.add(movie);
            }
        }
        int comedy = 0, drama= 1, thriller= 2, fantasy= 3, war= 4, action=5, adventure= 6,
                romance= 7, biography= 8, history= 9;
        int []genreArray = new int[10];
            List<Movie> suggestedMovies = new ArrayList<>();
        if(watchedMovies.isEmpty()){
            suggestedMovies = allMovies;
        } else {
            for (Movie movieWatched : watchedMovies) {
                if (movieWatched.getRating() >= 7) {
                    String genre = movieWatched.getGenre();
                    if (genre.equalsIgnoreCase("comedy")) {
                        genreArray[comedy]++;
                    } else if (genre.equalsIgnoreCase("drama")) {
                        genreArray[drama]++;
                    } else if (genre.equalsIgnoreCase("thriller")) {
                        genreArray[thriller]++;
                    } else if (genre.equalsIgnoreCase("fantasy")) {
                        genreArray[fantasy]++;
                    } else if (genre.equalsIgnoreCase("war")) {
                        genreArray[war]++;
                    } else if (genre.equalsIgnoreCase("action")) {
                        genreArray[action]++;
                    } else if (genre.equalsIgnoreCase("adventure")) {
                        genreArray[adventure]++;
                    } else if (genre.equalsIgnoreCase("romance")) {
                        genreArray[romance]++;
                    } else if (genre.equalsIgnoreCase("biography")) {
                        genreArray[biography]++;
                    } else if (genre.equalsIgnoreCase("history")) {
                        genreArray[history]++;
                    }
                }
            }
        }
        int max = -1;
        int index = -1;
        for(int i =0;i<10; i++){
            if(genreArray[i] > max){
                max = genreArray[i];
                index = i;
            }
        }
        String genre = "";
        if(index == 0){
            genre = "comedy";
        }else if(index == 1){
            genre = "drama";
        }else if(index == 2){
            genre = "thriller";
        }else if(index == 3){
            genre = "fantasy";
        }else if(index == 4){
            genre = "war";
        }else if(index == 5){
            genre = "action";
        }else if(index == 6){
            genre = "adventure";
        }else if(index == 7){
            genre = "romance";
        }else if(index == 8){
            genre = "biography";
        }else if(index == 9){
            genre = "history";
        }
        List<Movie> finalList = new ArrayList<>();
        for(Movie movie1: allMovies){
            if(movie1.getGenre().equalsIgnoreCase(genre)){
                finalList.add(movie1);
            }
        }

        data.addAll(finalList);

        regularUserControllerI.initSuggested();
        regularUserControllerI.setDataSuggestedT(data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showAllReviewsMovie() throws InterruptedException, IOException, ClassNotFoundException {
        ObservableList<Review> data = FXCollections.observableArrayList();

        User user = new User();

        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");

        user = userRepositoryI.getUserById(idLogedUser);

        List<Review>  reviewsMovies = new ArrayList<>();
        reviewsMovies = reviewRepositoryI.listReview();
        data.addAll(reviewsMovies);
        System.out.println("*******" + Arrays.asList(data));

        regularUserControllerI.initReviews();
        regularUserControllerI.setDataReviewT(data);

    }

    public Movie addReviewMovie(String text, String movieName) throws InterruptedException, IOException, ClassNotFoundException {
        SingletonClass singletonClass = SingletonClass.getInstance();

        String idLogedUser = singletonClass.id;//constants.getId();
        System.out.println("\n\n^^^^^^^^^^^^^^^" + idLogedUser + "\n\n");

        Movie movie = null;
        User user = null;
        Review review =null;
        try {

            movie = movieRepositoryI.getMovieByName(movieName);
            user = userRepositoryI.getUserById(idLogedUser);
        review = new Review();
            review.setUserReviewId(idLogedUser);
            review.setReview(text);
            review.setMovie(movie);

            if (user != null && movie != null) {
                boolean exist = false;
                int ok = 0;
                List<Review> reviewList = new ArrayList<>();
                if (!movie.getReviews().isEmpty()) {
                    reviewList = movie.getReviews();
                    for (Review i : reviewList) {
                        if (i.getUserReviewId().equals(idLogedUser)) {
                            exist = true;
                        }
                    }
                }
                if (!exist) {
                    reviewList.add(review);
                    reviewRepositoryI.addReview(review);
                    movie.setReviews(reviewList);
                    movieRepositoryI.updateMovie(movie);
                } else {
                    throw new UnsupportedOperationException("REVIEW-UL E DEJA IN FILM");
                }

            } else {
                throw new NullPointerException("fimul sau userul nu sunt in baza de date");
            }
        } catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(null, "reviewul este deja in film", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
          JOptionPane.showMessageDialog(null, "userul/ filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return movie;


    }

    public void likeReview(String idReview) {
        Review review = reviewRepositoryI.getReviewById(Integer.parseInt(idReview));
        int up = review.getLikeUp();
        up++;
        review.setLikeUp(up);
        reviewRepositoryI.updateReview(review);

    }

    public void unlikeReview(String idReview) {
        Review review = reviewRepositoryI.getReviewById(Integer.parseInt(idReview));
        int down = review.getLikeUDown();
        down--;
        review.setLikeUp(down);
        reviewRepositoryI.updateReview(review);
    }
}

