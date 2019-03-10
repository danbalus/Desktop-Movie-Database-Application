package client.bll.services;

import client.ClientConnection;
import client.model.constants.SingletonClass;
import client.model.entity.Movie;
import client.model.validators.*;
import server.dao.UserDAO;
import server.dao.UserDAOI;
import client.model.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class UserService implements UserServiceI {
    private UserDAOI userDAOI;
    private List<Validators<User>> validators;
    private SessionFactory sessionFactory;
    private Session session;
    private ClientConnection clientConnection;
    private  int  count = 0;
    private SingletonClass singletonClass;

    public UserService(){
        validators = new ArrayList<Validators<User>>();
        validators.add(new NicknameValidator());
        validators.add(new EmailValidator());
        validators.add(new AgeValidator());
        validators.add(new TypeAccountValidator());
        userDAOI = new UserDAO();
        singletonClass = SingletonClass.getInstance();

        clientConnection = singletonClass.clientConnection;
    }

    @Override
    public synchronized User addUser(User user) throws IOException {
        for (Validators<User> v : validators) {
            v.validate(user);
        }
        clientConnection.addUserSendToServer(user, "ADD_USER");

        //this.userDAOI.addUser(user);
        return user;
    }

    @Override
    public synchronized User updateUser(User user) {
        for (Validators<User> v : validators) {
            v.validate(user);
        }
        try {
            clientConnection.updateUserSendToServer(user, "UPDATE_USER");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //this.userDAOI.updateUser(user);
        return user;
    }

    @Override
    public List<User> listUser()   {
        try {
            clientConnection.getAllUsersSendToServer("GET_ALL_USERS");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        List<User> userList = clientConnection.getListOfUsersReceived();
        return userList;//return this.userDAOI.listUser();    }
    }

    @Override
    public synchronized User getUserById(String id)   {
        User user = null;
        try {
        clientConnection.getUserSendToServer(id, "READ_USER");

         user = (User)clientConnection.getObjectReceivedUser();
        //User user = this.userDAOI.getUserById(id);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (user == null) {
            throw new NoSuchElementException("Userul cu id-ul =" + id + " nu a fost gasit!");
        }
        return user;
    }

    @Override
    public void removeUser(String id) throws IOException {
        clientConnection.removeUserSendToServer(id, "REMOVE_USER");
       //this.userDAOI.removeUser(id);
    }

}
