package client.controller;


import client.bll.repository.*;
import client.bll.services.*;
import client.model.feature.FactoryRaport;
import client.model.feature.Raport;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import client.model.entity.Actor;
import client.model.entity.Movie;
import client.model.entity.User;
import org.hibernate.HibernateException;
import client.view.gui.AdministratorControllerI;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.zip.DataFormatException;

public class ControllerAdmin {
    private final AdministratorControllerI administratorControllerI;

   // private MovieServiceI movieServiceI;
    private MovieRepositoryI movieRepositoryI;
   // private ActorServiceI actorServiceI;
    private ActorRepositoryI actorRepositoryI;
    //private UserServiceI userServiceI;
    private UserRepositoryI userRepositoryI;

    public ControllerAdmin(AdministratorControllerI administratorControllerI/* , IAccountProvider accountProvider */) {

        this.administratorControllerI = administratorControllerI;
        //movieServiceI = new MovieService();
        movieRepositoryI = new MovieRepository();
        //actorServiceI = new ActorService();
        actorRepositoryI = new ActorRepository();
        //userServiceI = new UserService();
        userRepositoryI = new UserRepository();
    }

    ///controllerele pt teste
    public ControllerAdmin(AdministratorControllerI administratorControllerI, UserRepositoryI userRepositoryI) {
        this.administratorControllerI = administratorControllerI;
        this.userRepositoryI = userRepositoryI;
    }

    public ControllerAdmin(AdministratorControllerI administratorControllerI, MovieRepositoryI movieRepositoryI) {
        this.administratorControllerI = administratorControllerI;
        this.movieRepositoryI = movieRepositoryI;
    }

    public Movie addMovie(String name, String gendre, String info, int rate, String status) {
        Movie movie = null;
        try {
            if(!status.equalsIgnoreCase("to be released")
                    &&(!status.equalsIgnoreCase("released"))
                    &&(!status.equalsIgnoreCase("cancelled"))
                    &&(!status.equalsIgnoreCase("delayed"))){
                throw new DataFormatException("error");
            }
            movie = new Movie(name, gendre, info, rate, status);
            movieRepositoryI.addMovie(movie);
        } catch (DataFormatException ex) {
            JOptionPane.showMessageDialog(null, "status must be #to be released#" +
                    " #released# " +
                    "#cancelled# #delayed# ", "ERROR", JOptionPane.ERROR_MESSAGE);
        }catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, "filmul este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
            return movie;



    }

    public Movie addActorToMovie(int idMovie, int idActor) {
        Movie movie = null;
        Actor actor = null;
        try {

            movie = movieRepositoryI.getMovieById(idMovie);
            actor = actorRepositoryI.getActorById(idActor);

            if (actor != null && movie != null) {
                boolean exist = false;
                int ok = 0;
                List<Actor> actorList = new ArrayList<>();
                if (!movie.getActors().isEmpty()) {
                    actorList = movie.getActors();
                    for (Actor i : actorList) {
                        if (i.getId() == idActor) {
                            exist = true;
                        }
                    }
                }
                if (!exist) {
                    actorList.add(actor);
                    movie.setActors(actorList);
                    movieRepositoryI.updateMovie(movie);
                } else {
                    throw new UnsupportedOperationException("ACTORUL E DEJA IN FILM");
                }

            } else {
                throw new NullPointerException("actorul sau filmul nu sunt in baza de date");
            }
        } catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(null, "actorul este deja in film", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "actorul/ filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
            return movie;



    }

    public void deleteActorFromMovie(int idMovie, int idActor) {
        Movie movie = null;
        Actor actor = null;

        try {
            movie = movieRepositoryI.getMovieById(idMovie);
            actor = actorRepositoryI.getActorById(idActor);

            if (actor != null && movie != null) {
                List<Actor> actorList = new ArrayList<>();
                actorList = movie.getActors();
                boolean exist = false;
                for (Iterator<Actor> iterator = actorList.iterator(); iterator.hasNext(); ) {
                    Actor actorIt = iterator.next();
                    if (actorIt.getId() == (idActor)) {
                        iterator.remove();
                        exist = true;
                        break;
                    }
                }
                movie.setActors(actorList);
                movieRepositoryI.updateMovie(movie);
                if (exist) {

                } else {
                    throw new UnsupportedOperationException("ACTORUL NU E IN FILM");
                }

            } else {
                throw new NullPointerException("actorul sau filmul nu sunt in baza de date");
            }
        } catch (UnsupportedOperationException ex) {
            JOptionPane.showMessageDialog(null, "actorul NU e in film", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, "actorul/ filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul/actorul nu  este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public Movie readMovie(int idMovie) {
        Movie movie = null;

       //try {
            movie = movieRepositoryI.getMovieById(idMovie);
            if (/*movie.equals(null) &&*/ movie == null) {
                throw new NoSuchElementException("Filmu nu a fost gasit!");
            } else {
                administratorControllerI.init();
                administratorControllerI.setReadData(movie);
            }


      //  } catch (NoSuchElementException ex) {
     //       JOptionPane.showMessageDialog(null, "Filmul cu id-ul =\" + idMovie + \" nu a fost gasit!", "ERROR", JOptionPane.ERROR_MESSAGE);
      //  } catch (Exception ex) {//ilegal arg ex
      //      JOptionPane.showMessageDialog(null, "Datele nu respecta formatul", "ERROR", JOptionPane.ERROR_MESSAGE);
      //  }


        return movie;

    }

    public Movie updateMovie(int id, String name, String genre, String info, int rate, String status) throws InterruptedException {
        Movie movie = null;

        try {
            if(!status.equalsIgnoreCase("to be released")
                    &&(!status.equalsIgnoreCase("released"))
                    &&(!status.equalsIgnoreCase("cancelled"))
                    &&(!status.equalsIgnoreCase("delayed"))){
                throw new DataFormatException("error");
            }
            movie = new Movie(id, name, genre, info, rate, status);
            movieRepositoryI.updateMovie(movie);
        }catch (DataFormatException ex) {
            JOptionPane.showMessageDialog(null, "status must be #to be released#" +
                    " #released# " +
                    "#cancelled# #delayed# ", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "id format gresit", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "date format gresit", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, "filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        return movie;
    }


    public void deleteMovie(int idMovie) {
        try {
            movieRepositoryI.removeMovie(idMovie);
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(null, "filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau filmul nu este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public User addUser(String name, String password, int typeAcc, String id, String email, int age) {
        User user = null;
        try {
            user = new User(name, password, typeAcc, id, email, age);
            userRepositoryI.addUser(user);
        } catch (HibernateException ex) {
            JOptionPane.showMessageDialog(null, "userul este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Datele introduse nu respecta parametrii sau userul este in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            return user;
        }


    }

    public User readUser(String idUser) {
        User user = null;
        try {

            user = userRepositoryI.getUserById(idUser);
            System.out.println(user.toString());
            if (user.equals(null) || user == null) {
                throw new NoSuchElementException("Filmu nu a fost gasit!");
            } else {

                administratorControllerI.initUser();
                administratorControllerI.setReadDataUser(user);
            }
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Userul cu id-ul =\" + idMovie + \" nu a fost gasit!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {//ilegal arg ex
            JOptionPane.showMessageDialog(null, "Datele nu respecta formatul sau userul nu e in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
            return user;


    }

    public User updateUser(String name, String password, int typeAcc, String id, String email, int age) {
        User user = null;
        try {

            user = new User(name, password, typeAcc, id, email, age);
            userRepositoryI.updateUser(user);
        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Userul cu id-ul =\" + idMovie + \" nu a fost gasit!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {//ilegal arg ex
            JOptionPane.showMessageDialog(null, "Datele nu respecta formatul sau userul nu e in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            return user;
        }

    }

    public void deleteUser(String idUser) {
        try {
            userRepositoryI.removeUser(idUser);

        } catch (NoSuchElementException ex) {
            JOptionPane.showMessageDialog(null, "Userul cu id-ul =\" + idMovie + \" nu a fost gasit!", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {//ilegal arg ex
            JOptionPane.showMessageDialog(null, "Datele nu respecta formatul sau userul nu e in baza de date", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }

    public String openDirectoryChoose() {
        //FileChooser fileChooser = new FileChooser();

        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("JavaFX Projects");
        File defaultDirectory = new File("C:/Users/DanB/Downloads/aaaa");
        chooser.setInitialDirectory(defaultDirectory);
        Stage primaryStage = new Stage();
        File selectedDirectory = chooser.showDialog(primaryStage);
        System.out.println(selectedDirectory.getAbsolutePath());
        return selectedDirectory.getAbsolutePath();
    }

    public void showAllMovies() throws InterruptedException, IOException, ClassNotFoundException {

        ObservableList<Movie> data = FXCollections.observableArrayList();
        List<Movie> listMovie = movieRepositoryI.listMovie();

        System.out.println(Arrays.asList(listMovie));
        data.addAll(listMovie);
        administratorControllerI.init();
        administratorControllerI.setDataT(data);
    }

    public void showAllUsers()  {
        ObservableList<User> data = FXCollections.observableArrayList();

        List<User> listUser = null;
        try {
            listUser = userRepositoryI.listUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(Arrays.asList(listUser));
        data.addAll(listUser);
        administratorControllerI.initUser();
        administratorControllerI.setDataUserT(data);

    }

    public void generatePDF(String namePDF) throws InterruptedException, IOException, ClassNotFoundException {
        String pashDirectory = openDirectoryChoose();
        String finalPath = pashDirectory + "\\" + namePDF + ".pdf";

        ArrayList<Movie> listMovie = new ArrayList<Movie>(movieRepositoryI.listMovie());

        System.out.println(Arrays.asList(listMovie));

        Raport raport = FactoryRaport.getRaport("pdf", finalPath);
        raport.start(listMovie);
    }

    public void generateTXT(String nameTXT) throws InterruptedException, IOException, ClassNotFoundException {
        String pashDirectory = openDirectoryChoose();

        String finalPath = pashDirectory + "\\" + nameTXT + ".txt";

        ArrayList<Movie> listMovie = new ArrayList<Movie>(movieRepositoryI.listMovie());

        System.out.println(Arrays.asList(listMovie));
        Raport raport = FactoryRaport.getRaport("txt", finalPath);
        raport.start(listMovie);

    }
}
