package client.bll.services;

import client.model.entity.User;

import java.io.IOException;
import java.util.List;

public interface UserServiceI {
    //public void setUserDAOI(UserDAOI userDAOI);
    //public void setCurrentSession(SessionFactory sessionFactory);
    public User addUser(User user) throws IOException;
    public User updateUser(User user) throws IOException, ClassNotFoundException, InterruptedException;
    public List<User> listUser() throws InterruptedException, IOException, ClassNotFoundException;
    public User getUserById(String id) throws InterruptedException, IOException, ClassNotFoundException;//by nickname
    public void removeUser(String id) throws IOException;
}
