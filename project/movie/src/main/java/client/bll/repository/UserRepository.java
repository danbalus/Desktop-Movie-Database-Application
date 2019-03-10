package client.bll.repository;

import client.model.entity.Movie;
import client.model.entity.User;
import client.bll.services.MovieService;
import client.bll.services.MovieServiceI;
import client.bll.services.UserService;
import client.bll.services.UserServiceI;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UserRepository implements UserRepositoryI {

    private UserServiceI userServiceI;
    private MovieServiceI movieServiceI;

    public UserRepository(){
        userServiceI = new UserService();
        movieServiceI = new MovieService();
    }

    public void rateMovie(String idUser, String nameMovie, int rateMovie) throws InterruptedException, IOException, ClassNotFoundException {

        User user = userServiceI.getUserById(idUser);
        int idMovie = movieServiceI.getMovieByName(nameMovie).getId();
        if(user == null){
            throw new NullPointerException("Problema in demersul aplicatiei/ User deconectat");
        }
        Map<Integer, Integer> ratings = user.getRatings();
        ratings.put(idMovie, rateMovie);
        user.setRatings(ratings);

        userServiceI.updateUser(user);
    }



    @Override
    public User addUser(User user) throws IOException {
        userServiceI.addUser(user);
        return user;
    }

    @Override
    public synchronized User updateUser(User user) {
        try {
            userServiceI.updateUser(user);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> listUser() {
        List<User> userList = null;
        try {
            userList = userServiceI.listUser();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserById(String id) throws InterruptedException, IOException, ClassNotFoundException {
        User user = userServiceI.getUserById(id);
        return user;
    }

    @Override
    public void removeUser(String id) throws IOException {
        userServiceI.removeUser(id);
    }

}
